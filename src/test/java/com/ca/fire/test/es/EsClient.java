//package com.ca.fire.test.es;
//
//import org.apache.log4j.Logger;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.TransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.nio.ByteBuffer;
//import java.nio.CharBuffer;
//import java.nio.charset.StandardCharsets;
//import java.util.Arrays;
//import java.util.Base64;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * @date 2019-10-15
// */
//public class EsClient {
//    private static Logger logger = Logger.getLogger(EsClient.class);
//    private static Map<String, EsClient> esClientMap = new ConcurrentHashMap<>();
//    private static Object lock = new Object();
//
//    private TransportClient client;
//    private String clusterName;
//    private String securityKey = "request.headers.Authorization";
//    private String user;
//    private String password;
//    private String ipsPort;
//
//    private EsClient(String clusterName, String ipsPort, String user, String password) {
//        this.clusterName = clusterName;
//        this.ipsPort = ipsPort;
//        this.user = user;
//        this.password = password;
//        logger.error("EsClient.EsClient() " + ipsPort);
//        logger.error("EsClient.EsClient() " + user);
//    }
//
//    /**
//     * 单例模式，对外开放的获取实例的方法
//     *
//     * @param clusterName
//     * @param ipsPort
//     * @param user
//     * @param password
//     * @return
//     */
//    public static EsClient GetInstance(String clusterName, String ipsPort, String user, String password) {
//        String key = ipsPort + clusterName;
//        if (!esClientMap.containsKey(key)) {
//            synchronized (lock) {
//                if (!esClientMap.containsKey(key)) {
//                    esClientMap.put(key, new EsClient(clusterName, ipsPort, user, password));
//                }
//            }
//        }
//        return esClientMap.get(key);
//    }
//
//    /**
//     * 获取 TransportClient
//     *
//     * @return
//     * @throws UnknownHostException
//     */
//    public TransportClient getClient() throws UnknownHostException {
//        if (client != null) {
//            return client;
//        }
//        synchronized (EsClient.class) {
//            if (client == null) {
//                System.setProperty("es.set.netty.runtime.available.processors", "false");
//                Settings settings = Settings.builder().put("cluster.name", clusterName)
//                        .put("client.transport.sniff", false)
//                        .put(securityKey, basicAuthHeaderValue(user, password))
//                        .build();
//
//                TransportClient tempClient = new PreBuiltTransportClient(settings);
//                String[] oneInstance = ipsPort.split(",");
//                for (String item : oneInstance) {
//                    String[] ipPort = item.split(":");
//                    tempClient.addTransportAddresses(new TransportAddress(InetAddress.getByName(ipPort[0]), Integer.parseInt(ipPort[1])));
//                }
//
//                client = tempClient;
//            }
//            return client;
//        }
//    }
//
//    /**
//     * 基础的base64生成
//     *
//     * @param username 用户名
//     * @param passwd   密码
//     * @return
//     */
//    private static String basicAuthHeaderValue(String username, String passwd) {
//        CharBuffer chars = CharBuffer.allocate(username.length() + passwd.length() + 1);
//        byte[] charBytes = null;
//        try {
//            chars.put(username).put(':').put(passwd.toCharArray());
//            charBytes = toUtf8Bytes(chars.array());
//
//            String basicToken = Base64.getEncoder().encodeToString(charBytes);
//            return "Basic " + basicToken;
//        } finally {
//            Arrays.fill(chars.array(), (char) 0);
//            if (charBytes != null) {
//                Arrays.fill(charBytes, (byte) 0);
//            }
//        }
//    }
//
//    public static byte[] toUtf8Bytes(char[] chars) {
//        CharBuffer charBuffer = CharBuffer.wrap(chars);
//        ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(charBuffer);
//        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit());
//        Arrays.fill(byteBuffer.array(), (byte) 0);
//        return bytes;
//    }
//
//    public String getClusterName() {
//        return clusterName;
//    }
//
//    public void setClusterName(String clusterName) {
//        this.clusterName = clusterName;
//    }
//
//    public String getSecurityKey() {
//        return securityKey;
//    }
//
//    public void setSecurityKey(String securityKey) {
//        this.securityKey = securityKey;
//    }
//
//    public String getUser() {
//        return user;
//    }
//
//    public void setUser(String user) {
//        this.user = user;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getIpsPort() {
//        return ipsPort;
//    }
//
//    public void setIpsPort(String ipsPort) {
//        this.ipsPort = ipsPort;
//    }
//}
