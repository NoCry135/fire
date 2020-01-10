package com.ca.fire.test.string;

import com.ca.fire.domain.bean.User;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TestString {

    public static void main(String[] args) {
        Integer a = 12345;
        Integer b = 12345;
        Integer c = 12346;
        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(a > c);
        System.out.println(a < c);
        System.out.println(a > b);
        System.out.println(a > b);
        new ConcurrentHashMap<>();
    }


    @Test
    public void testStartWith1() {
        String str = "emg153562325904ts2L1OUT02-2";

        String s1 = new String("EMG153562325904ts2L1OUT02");

        String s2 = new String("153562325904ts2L1OUT02-3");
        String s3 = new String("1535623259002-3");
        String s4 = new String("1535623259002");
        List<String> list = new ArrayList<>();
        list.add(str);
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        for (String goodsNo : list) {

            if (goodsNo.startsWith("EMG") || goodsNo.startsWith("emg")) {
                System.out.println("我是特殊类型的商品:" + goodsNo);
                continue;
            }
            try {
                long parseLong = Long.parseLong(goodsNo);
            } catch (NumberFormatException e) {
                System.out.println("我是特殊类型的商品:" + goodsNo);
                continue;
            }


            System.out.println("我是普通的商品:" + goodsNo);

        }


    }

    private Boolean isERPGoodsNo(String goodsNo) {
        if (goodsNo.startsWith("EMG") || goodsNo.startsWith("emg")) {
            return Boolean.FALSE;
        }
        try {
            long parseLong = Long.parseLong(goodsNo);
        } catch (NumberFormatException e) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Test
    public void testStartWith() {
        String str = "emg153562325904ts2L1OUT02-2";

        String s1 = new String("EMG153562325904ts2L1OUT02");

        String s2 = new String("153562325904ts2L1OUT02-3");
        List<String> list = new ArrayList<>();
        list.add(str);
        list.add(s1);
        list.add(s2);
        for (String goodsNo : list) {

            if (goodsNo.startsWith("EMG") || goodsNo.startsWith("emg")) {
                System.out.println("我是特殊类型的商品:" + goodsNo);
            } else {
                System.out.println("我是普通的商品:" + goodsNo);
            }
        }


    }

    @Test
    public void testHashCode() {
        int i = System.identityHashCode(new User());
        System.out.println(i);
        int i1 = Runtime.getRuntime().availableProcessors();
        System.out.println(i1);
    }


    @Test
    public void testSub() {
        String str = "153562325904ts2L1OUT02-2";
        System.out.println(str.substring(11));
        System.out.println("2222 ".substring(0, 2));
        String s1 = new String("153562325904ts2L1OUT02");
        String s2 = new String("153562325904ts2L1OUT02-3");
        String s3 = new String("153562325904ts2L1OUT0-2-5");
        String s4 = new String("153562325904ts2L1O-UT0");

        System.out.println(modifyBillNo(str));
        System.out.println(modifyBillNo(s1));
        System.out.println(modifyBillNo(s2));
        System.out.println(modifyBillNo(s3));
        System.out.println(modifyBillNo(s4));

    }

    public String modifyBillNo(String str) {
        int length = str.length();
        if (str.substring((length - 2), (length - 1)).equals("-")) {
            String substring = str.substring(0, (length - 2));
//            System.out.println(substring);
            return substring;
        } else {
            return str;
        }

    }

    @Test
    public void test01() {
        String str = "locate.783954774221532592026564-1";
        System.out.println(str.length());
        System.out.println(str.substring(0, 10));
        System.out.println(str);

    }

    @Test
    public void test02() {
        String str = "153562325904ts2L1OUT02-2222";
        boolean l1OUT = str.contains("ts");

        System.out.println(l1OUT);
        System.out.println(str.substring(0, 10));
        System.out.println(str);

    }

    @Test
    public void test05() {
        Object str = "153562325904ts2L1OUT02-333";
        Class<?> clazz = str.getClass();

        Field[] declaredFields = clazz.getDeclaredFields();

        System.out.println(str);

    }

    @Test
    public void test06() {
        String str = "153562325904ts2L1OUT02";

        String s1 = new String("153562325904ts2L1OUT02");
        String s2 = new String("153562325904ts2L1OUT02");

        System.out.println(str.hashCode());
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s2.equals(s1));
        System.out.println(str.equals(s1));
        System.out.println(str == s1);
        System.out.println(s2 == s1);

    }

    @Test
    public void test07() {
        System.out.println("x:" + getx());
        System.out.println("x2:" + getx2());
        System.out.println("x3:" + getx3());

    }

    @Test
    public void test08() {
        System.out.println("bb13-95-010-04".equals("BB13-95-010-04"));
        System.out.println("bb13-95-010-04".equalsIgnoreCase("BB13-95-010-04"));

    }

    private int getx() {
        int x;

        try {
            x = 1;
            int a = x / 0;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;

        } finally {
            x = 3;
        }

    }

    private int getx2() {
        int x;

        try {
            x = 1;
            int a = x / 0;
        } catch (Exception e) {
            x = 2;
            return x;

        } finally {
            x = 3;
        }
        return x;
    }


    private int getx3() {
        int x;

        try {
            x = 1;
            int a = x / 0;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;

        } finally {
            x = 3;
            return x;
        }

    }

    @Test
    public void test09() {
        String str = "81678383417#\n" +
                "82046656650#\n" +
                "81678383417#\n" +
                "82046656650#\n" +
                "81682407706#\n" +
                "81681961268#\n" +
                "81687199000#\n" +
                "82046794375#\n" +
                "82043906283#\n" +
                "81678631391#\n" +
                "81676687772#\n" +
                "82038692288#\n" +
                "81674851634#\n" +
                "81681961268#\n" +
                "81687199000#\n" +
                "82046794375#\n" +
                "82043906283#\n" +
                "81678631391#\n" +
                "81676687772#\n" +
                "81682407706#\n" +
                "82038692288#\n" +
                "81674851634#\n" +
                "81580805872#\n" +
                "81672670547#\n" +
                "81672670547#\n" +
                "81671895764#\n" +
                "81669547871#\n" +
                "81627517204#\n" +
                "82034340100#\n" +
                "81671895764#\n" +
                "81627517204#\n" +
                "81669547871#\n" +
                "82034340100#\n" +
                "82007025573#\n" +
                "82030747654#\n" +
                "81996229474#\n" +
                "81963853410#\n" +
                "81940921514#\n" +
                "81939241504#\n" +
                "82019317092#\n" +
                "82014853989#\n" +
                "82005771746#\n" +
                "81649795728#\n" +
                "81643086654#\n" +
                "81643964380#\n" +
                "81614526555#\n" +
                "81987039406#\n" +
                "81984856867#\n" +
                "81627517204#\n" +
                "81982657775#\n" +
                "81619585311#\n" +
                "81974233664#\n" +
                "81972745967#\n" +
                "82028222406#\n" +
                "82019526662#\n" +
                "82016272974#\n" +
                "82014258440#\n" +
                "82010426882#\n" +
                "82008677386#\n" +
                "82010081923#\n" +
                "82010466180#\n" +
                "82006064643#\n" +
                "81642481113#\n" +
                "82006540525#\n" +
                "81932466538#\n" +
                "81993065665#\n" +
                "81980841604#\n" +
                "82012484334#\n" +
                "82000621646#\n" +
                "81996366086#\n" +
                "81647039349#\n" +
                "81642875935#\n" +
                "81997141391#\n" +
                "81634809233#\n" +
                "81632257595#\n" +
                "81640033113#\n" +
                "82000899403#\n" +
                "81649928186#\n" +
                "81999855278#\n" +
                "81642033341#\n" +
                "81642641968#\n" +
                "81627769307#\n" +
                "81999193807#\n" +
                "81642016307#\n" +
                "81637924510#\n" +
                "81994804609#\n" +
                "81995988580#\n" +
                "81641033178#\n" +
                "81634577170#\n" +
                "82028221539#\n" +
                "81997696552#\n" +
                "82030747654#\n" +
                "81995852302#\n" +
                "82028221539#\n" +
                "81581309597#\n" +
                "82028222406#\n" +
                "81580805872#\n" +
                "82019317092#\n" +
                "82019526662#\n" +
                "81940921514#\n" +
                "81581309597#\n" +
                "81939241504#\n" +
                "81932466538#\n" +
                "82012484334#\n" +
                "82010466180#\n" +
                "82014853989#\n" +
                "81649795728#\n" +
                "82010426882#\n" +
                "82008677386#\n" +
                "82006540525#\n" +
                "82010081923#\n" +
                "82007025573#\n" +
                "82006064643#\n" +
                "81642481113#\n" +
                "82005771746#\n" +
                "82016272974#\n" +
                "81643086654#\n" +
                "81643964380#\n" +
                "82014258440#\n" +
                "82000621646#\n" +
                "81996229474#\n" +
                "81993065665#\n" +
                "81614526555#\n" +
                "81982657775#\n" +
                "81987039406#\n" +
                "81627517204#\n" +
                "81984856867#\n" +
                "81619585311#\n" +
                "81980841604#\n" +
                "81974233664#\n" +
                "81972745967#\n" +
                "81963853410#\n" +
                "81647039349#\n" +
                "81632257595#\n" +
                "81640033113#\n" +
                "82000899403#\n" +
                "81642875935#\n" +
                "81649928186#\n" +
                "81999855278#\n" +
                "81642033341#\n" +
                "81997696552#\n" +
                "81642641968#\n" +
                "81999193807#\n" +
                "81627769307#\n" +
                "81642016307#\n" +
                "81637924510#\n" +
                "81997141391#\n" +
                "81622875897#\n" +
                "81994804609#\n" +
                "81995988580#\n" +
                "81996366086#\n" +
                "81995852302#\n" +
                "81641033178#\n" +
                "81632305108#\n" +
                "81987682113#\n" +
                "81987485954#\n" +
                "81625896830#\n" +
                "81637347448#\n" +
                "81985829100#\n" +
                "81983891488#\n" +
                "81985733548#\n" +
                "81628764031#\n" +
                "81633477752#\n" +
                "81633232598#\n" +
                "81637267866#\n" +
                "81639988824#\n" +
                "81634577170#\n" +
                "81634809233#\n" +
                "81639988824#\n" +
                "81637267866#\n" +
                "81633232598#\n" +
                "81632305108#\n" +
                "81637347448#\n" +
                "81987682113#\n" +
                "81987485954#\n" +
                "81628764031#\n" +
                "81622875897#\n" +
                "81625896830#\n" +
                "81633477752#\n" +
                "81528913426#\n" +
                "81984099428#\n" +
                "81981800771#\n" +
                "81982469422#\n" +
                "81979384330#\n" +
                "81985829100#\n" +
                "81983891488#\n" +
                "81985733548#\n" +
                "81984099428#\n" +
                "81981800771#\n" +
                "81980317952#\n" +
                "81978410279#\n" +
                "81982469422#\n" +
                "81979384330#\n" +
                "81979904103#\n" +
                "81980317952#\n" +
                "81978410279#\n" +
                "81627534298#\n" +
                "81616303742#\n" +
                "81978574019#\n" +
                "81616542590#\n" +
                "81979904103#\n" +
                "81528913426#\n" +
                "81978574019#\n" +
                "81616542590#\n" +
                "81616303742#\n" +
                "\n";

        String trim = str.trim();
        List<String> list = Lists.newArrayList();
        String[] split = trim.split("#");
        for (int i = 0; i < split.length; i++) {
            String trim1 = split[i].trim();
            if (StringUtils.isNotBlank(trim1)) {
                list.add(trim1);
            }

        }
        System.out.println(list.size());
        System.out.println(list);
        HashMap<String, Integer> map = Maps.newHashMap();
        for (String s : list) {
            Integer integer = map.get(s);
            if (integer != null && integer > 0) {
                map.put(s, 2);
            } else {
                map.put(s, 1);

            }
        }
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            if (entry.getValue() == 1) {
                System.out.println("出现一次的订单:" + entry.getKey());
            }
        }

    }

}
