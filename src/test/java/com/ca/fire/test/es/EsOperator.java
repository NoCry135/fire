//package com.ca.fire.test.es;
//
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.apache.tomcat.jdbc.pool.DataSourceProxy;
//import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsResponse;
//import org.elasticsearch.action.bulk.BulkRequestBuilder;
//import org.elasticsearch.action.bulk.BulkResponse;
//import org.elasticsearch.action.search.SearchRequestBuilder;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.search.SearchType;
//import org.elasticsearch.client.IndicesAdminClient;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.unit.TimeValue;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.index.reindex.BulkByScrollResponse;
//import org.elasticsearch.index.reindex.DeleteByQueryAction;
//import org.elasticsearch.search.SearchHit;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.sql.DataSource;
//import javax.validation.constraints.Max;
//import javax.validation.constraints.Min;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * @date 2019-10-15
// */
//@NoArgsConstructor
//@Component("esOperator")
//@ConfigurationProperties
//public class EsOperator {
//    private static final Logger logger = LoggerFactory.getLogger(EsOperator.class);
//
//    /**
//     * 默认日期格式
//     */
//    private final static int DATE_TYPE_FORMAT_DEFAULT = 1;
//
//    /**
//     * strict_date_optional_time||epoch_millis(如：1551321412000)
//     */
//    private final static String DATE_TYPE_FORMAT_DEFAULT_CONTENT = "epoch_millis";
//
//    /**
//     * 用户自定义日期格式
//     */
//    private final static int DATE_TYPE_FORMAT_STRING = 2;
//
//    /**
//     * yyyy-MM-dd HH:mm:ss(如：2019-01-01 09:00:01)
//     */
//    private final static String DATE_TYPE_FORMAT_STRING_CONTENT = "yyyy-MM-dd";
//
//    /**
//     * ISO日期格式
//     */
//    private final static int DATE_TYPE_FORMAT_ISO_STRING = 3;
//
//    /**
//     * dateOptionalTime(如：2019-01-01T09:00:01.000Z)
//     */
//    private final static String DATE_TYPE_FORMAT_ISO_STRING_CONTENT = "dateOptionalTime";
//
//    /**
//     * 日期名称
//     */
//    private final static String FIELD_DATE_STRING = "date";
//
//    /**
//     * 类型名称
//     */
//    private final static String FIELD_TYPE_STRING = "type";
//
//    /**
//     * 格式化名称
//     */
//    private final static String FIELD_FORMAT_STRING = "format";
//
//    @Getter
//    @Setter
//    @Value("${es.batch.size}")
//    @Max(value = 10000)
//    @Min(value = 1)
//    private Integer batchSize = 2000;
//
//    @Getter
//    @Setter
//    @Value("${es.batch.size.ratio}")
//    private Double batchRatio = 1.0;
//
//    @Getter
//    @Setter
//    @Value("${es.batch.repeat}")
//    @Max(value = 5)
//    @Min(value = 1)
//    private Integer repeat = 1;
//
//    /**
//     * 按照主键删除数据
//     *
//     * @param dataSource 数据源
//     * @param task       表名
//     * @return
//     * @throws Exception
//     */
//    public long deleteByTask(DataSource dataSource, TaskInfo task) throws Exception {
//        TransportClient client = getTransportClient(dataSource, task);
//        int deletedTotalCount = 0;
//        String tableName = task.getArchiveMainTblName();
//        String index = null;
//        String type = null;
//        String[] arr = tableName.split("\\.");
//        if (arr != null && arr.length == 2) {
//            index = arr[0];
//            type = arr[1];
//        }
//
//        int dateType = getDateFormatType(client, index, type, task.getTsColumn().trim());
//
//        for (int i = 0; i < repeat; i++) {
//            SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTimeout(TimeValue.timeValueMillis(3000));
//            searchRequestBuilder.setTypes(type).setSearchType(SearchType.QUERY_THEN_FETCH);
//            searchRequestBuilder.setQuery(getQueryBuilder(task, dateType));
//            searchRequestBuilder.setFrom(0);
//            searchRequestBuilder.setSize(getBatchSize());
//
//            SearchResponse response = searchRequestBuilder.execute().actionGet();
//            List<String> idList = new ArrayList<>();
//            for (SearchHit hit : response.getHits()) {
//                idList.add(hit.getId());
//            }
//            try {
//                BulkByScrollResponse res = DeleteByQueryAction.INSTANCE
//                        .newRequestBuilder(client)
//                        .filter(QueryBuilders.termsQuery("_id", idList.toArray()))
//                        .source(index)//设置索引名称
//                        .get();
//                //被删除文档数目
//                deletedTotalCount += res.getDeleted();
//            } catch (Exception e) {
//                //ES2.x版本的执行下面的删除逻辑
//                if (idList != null && idList.size() > 0) {
//                    BulkRequestBuilder bulkRequest = client.prepareBulk();
//                    for (String s : idList) {
//                        bulkRequest.add(client.prepareDelete(index, type, s).request());
//                    }
//                    BulkResponse bulkResponse = bulkRequest.execute().actionGet();
//                    if (!bulkResponse.hasFailures()) {
//                        deletedTotalCount += bulkResponse.getItems().length;
//                    }
//                }
//            }
//            //休眠1秒
//            Thread.sleep(1000);
//        }
//        logger.info("当前表：" + index + "." + type + ",单次最大删除量为：" + batchSize + ",循环次数：" + repeat + "，本次删除的文档总量为：" + deletedTotalCount);
//        return deletedTotalCount;
//    }
//
//    /**
//     * 获取TransportClient
//     *
//     * @param dataSource
//     * @param task
//     * @return
//     */
//    private TransportClient getTransportClient(DataSource dataSource, TaskInfo task) throws Exception {
//        String[] esAddress = task.getDbMaster().split("\\/");
//        String ipsPort = esAddress[0];
//        String clusterName = esAddress[1];
//        DataSourceProxy dataSourceProxy = (DataSourceProxy) dataSource;
//        String username = dataSourceProxy.getUsername();
//        String password = dataSourceProxy.getPoolProperties().getPassword();
//
//        EsClient esClient = EsClient.GetInstance(clusterName, ipsPort, username, password);
//        TransportClient client = esClient.getClient();
//        return client;
//    }
//
//    /**
//     * 获取日期格式类型（1：默认long型，2：yyyy-MM-dd HH:mm:ss 3:dateOptionalTime）
//     *
//     * @param client
//     * @param index
//     * @param type
//     * @param tsColumn
//     * @return
//     * @throws Exception
//     */
//    private int getDateFormatType(TransportClient client, String index, String type, String tsColumn) throws Exception {
//        IndicesAdminClient indicesAdminClient = client.admin().indices();
//        GetMappingsResponse gmr = indicesAdminClient.prepareGetMappings(index).setTypes(type).get();
//        try {
//            Map<String, Object> mappingMap = gmr.getMappings().get(index).get(type).sourceAsMap();
//            Map<String, Object> propertiesMap = (Map<String, Object>) mappingMap.get("properties");
//            Map<String, Object> fieldsMap = (Map<String, Object>) propertiesMap.get(tsColumn);
//
//            if (fieldsMap != null) {
//                logger.error("获取的fieldsMap值为:" + fieldsMap + ",tsColumn为：" + tsColumn);
//                String fieldType = (String) fieldsMap.get(FIELD_TYPE_STRING);
//                if (fieldType.equals(FIELD_DATE_STRING)) {
//                    if (fieldsMap.get(FIELD_FORMAT_STRING) == null) {
//                        return DATE_TYPE_FORMAT_DEFAULT;
//                    } else {
//                        String fieldTypeFormat = (String) fieldsMap.get(FIELD_FORMAT_STRING);
//                        logger.error("获取的format值为:" + fieldTypeFormat);
//                        if (fieldTypeFormat.contains(DATE_TYPE_FORMAT_DEFAULT_CONTENT)) {
//                            return DATE_TYPE_FORMAT_DEFAULT;
//                        } else if (fieldTypeFormat.contains(DATE_TYPE_FORMAT_STRING_CONTENT)) {
//                            return DATE_TYPE_FORMAT_STRING;
//                        } else if (fieldTypeFormat.contains(DATE_TYPE_FORMAT_ISO_STRING_CONTENT)) {
//                            return DATE_TYPE_FORMAT_ISO_STRING;
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            logger.error("getDateFormatType throw Exception:", e);
//            throw e;
//        }
//        return DATE_TYPE_FORMAT_DEFAULT;
//    }
//
//
//    /**
//     * 构建查询条件
//     *
//     * @param task
//     * @return
//     */
//    private QueryBuilder getQueryBuilder(TaskInfo task, int dateType) throws Exception {
//        BoolQueryBuilder fb = QueryBuilders.boolQuery();
//        if (task.getSafeDays() != null && task.getSafeDays() > 0) {
//            String firstSafeDate = getFirstSafeDate(task.getSafeDays());
//            String timeFiled = task.getTsColumn();
//            if (dateType == DATE_TYPE_FORMAT_DEFAULT) {
//                long firstSafeDateLong = getLongTime(firstSafeDate);
//                fb.filter(QueryBuilders.rangeQuery(timeFiled).to(firstSafeDateLong));
//            } else if (dateType == DATE_TYPE_FORMAT_STRING) {
//                fb.filter(QueryBuilders.rangeQuery(timeFiled).to(firstSafeDate));
//            } else if (dateType == DATE_TYPE_FORMAT_ISO_STRING) {
//                String isoDate = getISODate(firstSafeDate);
//                fb.filter(QueryBuilders.rangeQuery(timeFiled).to(isoDate));
//            }
//
//            //附加其他条件(目前仅支持and和=表达式,不能带括号，如:"a=1 and b=2")
//            String extWhere = task.getExeCondition();
//            if (!StringUtils.isEmpty(extWhere)) {
//                Map<String, Object> whereMap = getWhereMap(extWhere);
//                for (Map.Entry<String, Object> entry : whereMap.entrySet()) {
//                    fb.filter(QueryBuilders.termQuery(entry.getKey(), entry.getValue()));
//                }
//            }
//        }
//
//        return fb;
//    }
//
//    /**
//     * 获取条件的map
//     *
//     * @param extWhere
//     * @return
//     */
//    private Map<String, Object> getWhereMap(String extWhere) {
//        Map<String, Object> resultMap = new HashMap<>(16);
//        if (!StringUtils.isEmpty(extWhere)) {
//            //排除不支持的复杂查询
//            if (extWhere.contains(" or ")
//                    || extWhere.contains(">")
//                    || extWhere.contains("<")
//                    || extWhere.contains("!=")) {
//                return new HashMap<>(16);
//            }
//
//            String[] arr1 = extWhere.trim().split("and");
//            if (arr1 != null && arr1.length > 0) {
//                for (int i = 0; i < arr1.length; i++) {
//                    String[] arr2 = arr1[i].trim().split("\\=");
//                    if (arr2 != null && arr2.length == 2) {
//                        resultMap.put(arr2[0].trim(), arr2[1].trim());
//                    }
//                }
//            }
//        }
//        return resultMap;
//    }
//
//
//    /**
//     * 获取安全日期
//     *
//     * @param safeDay
//     * @return
//     * @throws Exception
//     */
//    private String getFirstSafeDate(int safeDay) {
//        // 获取当前时间
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_MONTH, -safeDay);
//        String beforeDatetime = sdf.format(calendar.getTime());
//        return beforeDatetime;
//    }
//
//    /**
//     * 转换为ISO日期格式
//     *
//     * @param date
//     * @return
//     */
//    private String getISODate(String date) throws Exception {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date dateTime = simpleDateFormat.parse(date);
//        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//        df.setTimeZone(tz);
//        String nowAsISO = df.format(dateTime);
//        return nowAsISO;
//    }
//
//    /**
//     * 获取时间戳
//     *
//     * @param dateTime
//     * @return
//     * @throws ParseException
//     */
//    private long getLongTime(String dateTime) throws ParseException {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return simpleDateFormat.parse(dateTime).getTime();
//    }
//
//    /**
//     * 根据设置的单个批次处理的数据量和比率区分白天晚上的数据量
//     *
//     * @return
//     */
//    private int getBatchSize() {
//        if (batchRatio > 1.0) {
//            return batchSize;
//        }
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("HH");
//        String time = sdf.format(date);
//        int hour = Integer.parseInt(time);
//        if (hour >= 0 && hour < 8) {
//            return batchSize;
//        } else {
//            Double r = batchSize * batchRatio;
//            return r.intValue();
//        }
//    }
//
//}
