//package com.ca.fire.util;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component("dataSourceManager")
//@ConfigurationProperties()
//public class DataSourceManager {
//	private static final Logger logger = LoggerFactory.getLogger(DataSourceManager.class);
//
//	@Getter
//	@Setter
//	public class DataSourceGroup {
//		private String key;
//		private DataSource masterDataSource;
//		private DataSource slaveDataSource;
//		private DataSource historyDataSource;
//	}
//
//	@Resource
//	private MasterDbInfoConfig masterDbInfoConfig;
//
//	@Resource
//	private SlaveDbInfoConfig slaveDbInfoConfig;
//
//	@Resource
//	private HistoryDbInfoConfig historyDbInfoConfig;
//
//	private List<DataSourceGroup> dataSourceGroups;
//	public List<DataSourceGroup> getDataSourceGroups() throws Exception {
//		if (dataSourceGroups == null) {
//			dataSourceGroups = buildDataSourceGroups();
//		}
//		return dataSourceGroups;
//	}
//
//	public List<DataSourceGroup> getTargetDataSource(String dbMaster) throws Exception {
//		List<DataSourceGroup> dsgs = this.getDataSourceGroups();
//		if (StringUtils.isEmpty(dbMaster)) {
//			return dsgs;
//		}
//
//		List<DataSourceGroup> rtnList = new ArrayList<DataSourceGroup>();
//		for (DataSourceGroup dsg : dsgs) {
//			if (dbMaster.equals(dsg.getKey())) {
//				rtnList.add(dsg);
//				break;
//			}
//		}
//		return rtnList;
//	}
//	private List<DataSourceGroup> buildDataSourceGroups() throws Exception {
//		List<DataSource> masterDss = masterDbInfoConfig.buildDataSource();
//		logger.error("masterDss size  {}" ,masterDss);
//		List<DataSource> slaveDss = null;
//		String[] slaveUrls = slaveDbInfoConfig.getAllUrls();
//		// 如果从库没有配置，则默认取主库
//		if (slaveUrls == null || slaveUrls.length <= 0) {
//			slaveDss = masterDss;
//		} else {
//			slaveDss = slaveDbInfoConfig.buildDataSource();
//			logger.error("slaveDss size  {}" ,slaveDss);
//		}
//		// 如果历史库没有配置，则默认取主库
//		List<DataSource> historyDss = null;
//		String[] historyUrls = historyDbInfoConfig.getAllUrls();
//		if (historyUrls == null || historyUrls.length <= 0) {
//			historyDss = masterDss;
//		} else {
//			historyDss = historyDbInfoConfig.buildDataSource();
//			logger.error("historyDss size  {}" ,historyDss);
//		}
//
//		// 校验配置是否合法
//		if (masterDss.size() != slaveDss.size()) {
//			String errorMsg = "master database and slave database is not matched.";
//			logger.error(errorMsg);
//			throw new Exception(errorMsg);
//		}
//
//		return buildDataSourceGroups(masterDbInfoConfig.getAllUrls(),
//				masterDss, slaveDss, historyDss);
//	}
//
//	private List<DataSourceGroup> buildDataSourceGroups(String[] masterUrls,
//                                                        List<DataSource> masterDss, List<DataSource> slaveDss, List<DataSource> historyDss) {
//		List<DataSourceGroup> dataSourceGroups = new ArrayList<>();
//		for (int idx=0, size=masterDss.size(); idx < size; idx ++) {
//			DataSourceGroup dsg = new DataSourceGroup();
//			dataSourceGroups.add(dsg);
//			String key = masterUrls[idx];
//			int fromIdx = key.indexOf("//");
//			if (fromIdx < 0) {
//				fromIdx = -2;
//			}
//			int toIdx = key.lastIndexOf("?");
//			if (toIdx < 0) {
//				toIdx = key.length();
//			}
//			dsg.setKey(key.substring(fromIdx + 2, toIdx));
//			dsg.setMasterDataSource(masterDss.get(idx));
//			dsg.setSlaveDataSource(slaveDss.get(idx));
//			// 历史库可以仅配置1个
//			int hidx = idx >= historyDss.size() ? 0 : idx;
//			dsg.setHistoryDataSource(historyDss.get(hidx));
//		}
//
//		return dataSourceGroups;
//	}
//}
