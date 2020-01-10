//package com.ca.fire.jvmMonitor.profiler.config;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.ca.fire.jvmMonitor.profiler.util.CacheUtil;
//import org.apache.http.client.HttpClient;
//import org.apache.log4j.FileAppender;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * Created on 2019/12/1
// */
//public class DynamicConfigManager {
//
//    private long bizLogFlushPeriod = 10000L;
//    private long tpLogFlushPeriod = 10000L;
//    private ConcurrentHashMap<String, KeyMeta> keys;
//    private ConcurrentHashMap<Long, Set<String>> key4Period;
//    private boolean isDisable = false;
//    private String currentV = "init";
//    private LogCollectorType cType = LogCollectorType.TCP;
//    private static final String CONFIG_MANAGER_API_BASE_URL = ;
//    private static final String PROFILER_CONFIG_API_URI = "/profiler";
//    private static final String TRANSFER_ADDRESS_API_URI = "/getproxyip";
//    private static final String API_STATE_DISABLED = "disabled";
//    private static final String API_STATE_CURRENT = "current";
//    private static final String API_STATE_FAIL = "fail";
//    private static final String INIT_APP_CONFIG_VERSION = "init";
//    private static final String UNKONW_APP_NAME = "unknown";
//    private static final String LOCAL_CACHE_FILE = CacheUtil.UMP_PREFFIX + "/config/" + CacheUtil.APP_NAME;
//    private HttpClient httpClient = new HttpClient(CONFIG_MANAGER_API_BASE_URL, 5000);
//    private FileAppender fileAppender = new FileAppender();
////    private NetAppender netAppender = new NetAppender(this.fileAppender);
//    private static final DynamicConfigManager manager = new DynamicConfigManager();
//
//    private DynamicConfigManager() {
//        this.keys = new ConcurrentHashMap();
//        this.key4Period = new ConcurrentHashMap();
//        this.key4Period.put(Long.valueOf(-2L), new HashSet());
//        loadLocalCache();
//        if (!"unknown".equals(CacheUtil.APP_NAME)) {
//            Timer fetchCfgTimer = new Timer("umpCfgFetcher", true);
//            fetchCfgTimer.scheduleAtFixedRate(new FetchConfigTask(), 0L, 15000L);
//        }
//        this.fileAppender.start();
////        this.netAppender.start();
//    }
//
//    public void registerAutoKey(String key, String appName) {
//        KeyMeta meta = getKeyMeta(key);
//        if (appName.equals(CacheUtil.APP_NAME)) {
//            meta.setAutoKeyAppName("");
//        } else {
//            meta.setAutoKeyAppName(appName);
//        }
//    }
//
//    public Map<Long, Set<String>> getKey4Period() {
//        return this.key4Period;
//    }
//
//    private KeyMeta getKeyMeta(String key) {
//        if (!this.keys.containsKey(key)) {
//            KeyMeta meta = new KeyMeta();
//            if (null != this.keys.putIfAbsent(key, meta)) {
//                meta = (KeyMeta) this.keys.get(key);
//            }
//            Long period = Long.valueOf(meta.getPeriod());
//            Set<String> set;
//            if (!this.key4Period.containsKey(period)) {
//                Set<String> set = new HashSet();
//                if (null != this.key4Period.putIfAbsent(period, set)) {
//                    set = (Set) this.key4Period.get(period);
//                }
//            } else {
//                set = (Set) this.key4Period.get(period);
//            }
//            set.add(key);
//        }
//        return (KeyMeta) this.keys.get(key);
//    }
//
//    public String isAutoKeyAndReturnAppName(String key) {
//        return getKeyMeta(key).getAutoKeyAppName();
//    }
//
//    public static DynamicConfigManager getInstance() {
//        return manager;
//    }
//
//    public long getBizLogFlushPeriod() {
//        return this.bizLogFlushPeriod;
//    }
//
//    public long getDefaultTpLogFlushPeriod() {
//        return this.tpLogFlushPeriod;
//    }
//
//    public boolean isDisable() {
//        return this.isDisable;
//    }
//
//    public long getPeriod4Key(String key) {
//        KeyMeta meta = getKeyMeta(key);
//        if (meta.getPeriod() != -2L) {
//            return meta.getPeriod();
//        }
//        return getDefaultTpLogFlushPeriod();
//    }
//
//    public void setCollectorType(LogCollectorType type) {
//        this.cType = type;
//    }
//
//    public LogAppender getAppender() {
//        if ((LogCollectorType.TCP == this.cType) && (this.netAppender.isAvailable() == true)) {
//            return this.netAppender;
//        }
//        return this.fileAppender;
//    }
//
//    public void applyConfiguration(DynamicConfig c) {
//        if ((c == null) || (c.getState() == null)) {
//            return;
//        }
//        if (c.getState().equals("disabled")) {
//            this.isDisable = true;
//        } else {
//            this.isDisable = false;
//        }
//        if (c.getSendType().equals("tcp")) {
//            this.cType = LogCollectorType.TCP;
//        } else {
//            this.cType = LogCollectorType.FIlE;
//        }
//        this.tpLogFlushPeriod = c.getPeriod();
//        this.currentV = c.getVersion();
//        ConcurrentHashMap<Long, Set<String>> tempKey4Period = new ConcurrentHashMap();
//        ConcurrentHashMap<String, KeyMeta> tempKeys = new ConcurrentHashMap();
//        Iterator localIterator1;
//        if ((c.getConfig() != null) && (c.getConfig().size() != 0)) {
//            for (localIterator1 = c.getConfig().iterator(); localIterator1.hasNext(); ) {
//                item = (KeyConfig) localIterator1.next();
//                tempKey4Period.putIfAbsent(Long.valueOf(item.getPeriod()), new HashSet(item.getKeys().size()));
//                for (String key : item.getKeys()) {
//                    KeyMeta meta = new KeyMeta();
//                    meta.setPeriod(item.getPeriod());
//                    tempKeys.put(key, meta);
//                    ((Set) tempKey4Period.get(Long.valueOf(meta.getPeriod()))).add(key);
//                }
//            }
//        }
//        KeyConfig item;
//        this.key4Period = tempKey4Period;
//        this.keys = tempKeys;
//    }
//
//    private void loadLocalCache() {
//        FileReader fileReader = null;
//        BufferedReader br = null;
//        StringBuilder sb = new StringBuilder(1024);
//        try {
//            fileReader = new FileReader(LOCAL_CACHE_FILE);
//            br = new BufferedReader(fileReader);
//            String str = null;
//            while ((str = br.readLine()) != null) {
//                sb.append(str);
//            }
//            DynamicConfig config = (DynamicConfig) JSONObject.parseObject(sb.toString(), DynamicConfig.class);
//            applyConfiguration(config);
//            return;
//        } catch (Exception e) {
//            return;
//        } finally {
//            if (fileReader != null) {
//                try {
//                    fileReader.close();
//                } catch (IOException localIOException2) {
//                }
//            }
//        }
//    }
//
//    public void updateLocalCache(DynamicConfig c) {
//        File cache = new File(LOCAL_CACHE_FILE);
//        if (!cache.exists()) {
//            File parent = new File(cache.getParent());
//            parent.mkdirs();
//        }
//        FileWriter fileWriter = null;
//        try {
//            fileWriter = new FileWriter(LOCAL_CACHE_FILE);
//            fileWriter.write(JSON.toJSONString(c));
//            fileWriter.flush();
//            return;
//        } catch (Exception localException) {
//        } finally {
//            if (fileWriter != null) {
//                try {
//                    fileWriter.close();
//                } catch (IOException localIOException2) {
//                }
//            }
//        }
//    }
//
//    public List<String> fetchProxyIps() {
//        return fetchProxyIps(null);
//    }
//
//    public List<String> fetchProxyIps(String curHost) {
//        StringBuilder uri = new StringBuilder("/getproxyip");
//        try {
//            if (curHost != null) {
//                uri.append("?ip=").append(URLEncoder.encode(curHost, "utf-8"));
//            }
//            String body = this.httpClient.doGet(uri.toString());
//            JSONObject jsonObject = JSONObject.parseObject(body);
//            (List) JSONObject.parseObject(jsonObject.getString("data"), new TypeReference() {
//            }, new Feature[0]);
//        } catch (Exception e) {
//        }
//        return null;
//    }
//
//    private static String getConfigManagerApiBaseUrl() {
//        if ((null != System.getProperty("umpCenter")) && (!"".equals(System.getProperty("umpCenter")))) {
//            return "http://" + System.getProperty("umpCenter") + "/api/v1";
//        }
//        if ((null != System.getenv("umpCenter")) && (!"".equals(System.getenv("umpCenter")))) {
//            return "http://" + System.getenv("umpCenter") + "/api/v1";
//        }
//        return "http://config.ump.jd.local/api/v1";
//    }
//
//    class FetchConfigTask
//            extends TimerTask {
//        FetchConfigTask() {
//        }
//
//        public void run() {
//            try {
//                StringBuilder uri = new StringBuilder("/profiler");
//                uri.append("/").append(CacheUtil.APP_NAME);
//                if (!DynamicConfigManager.this.currentV.equals("init")) {
//                    uri.append("/").append(DynamicConfigManager.this.currentV);
//                }
//                String body = DynamicConfigManager.this.httpClient.doGet(uri.toString());
//                if ((body == null) || ("".equals(body))) {
//                    return;
//                }
//                DynamicConfig config = (DynamicConfig) JSONObject.parseObject(body, DynamicConfig.class);
//                if ((config.getState().equals("current")) ||
//                        (config.getState().equals("fail"))) {
//                    return;
//                }
//                DynamicConfigManager.this.applyConfiguration(config);
//                DynamicConfigManager.this.updateLocalCache(config);
//
//                List<String> ips = DynamicConfigManager.this.fetchProxyIps(DynamicConfigManager.this.netAppender.getCurrentHost());
//                if ((ips != null) && (ips.size() != 0)) {
//                    DynamicConfigManager.this.netAppender.rebalance(ips);
//                }
//            } catch (Exception localException) {
//            }
//        }
//    }
//
//    public static enum LogCollectorType {
//        TCP, FIlE;
//
//        private LogCollectorType() {
//        }
//    }
//
//    class KeyMeta {
//        private long period = -2L;
//        private String autoKeyAppName;
//
//        KeyMeta() {
//        }
//
//        public long getPeriod() {
//            return this.period;
//        }
//
//        public void setPeriod(long period) {
//            this.period = period;
//        }
//
//        public String getAutoKeyAppName() {
//            return this.autoKeyAppName;
//        }
//
//        public void setAutoKeyAppName(String autoKeyAppName) {
//            this.autoKeyAppName = autoKeyAppName;
//        }
//    }
//}
