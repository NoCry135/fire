package com.ca.fire.test.string;

import java.util.*;

/**
 * Created on 2020/1/13
 */
public class TestStringBuilder {


    public static void main(String[] args) {
        HashMap<String, BoxNoAndSerialNoList> boxMap = new HashMap<String, BoxNoAndSerialNoList>();
        List<SerialReceiving> serials = getSerialReceivingList();
        System.out.println("start");
        long millis = System.currentTimeMillis();
        for (SerialReceiving temp : serials) {
            BoxNoAndSerialNoList data = boxMap.get((temp.getParentCode() == null ? "-1" : temp.getParentCode()));
            if (data == null) {
                data = new BoxNoAndSerialNoList();
                data.setBoxNo(temp.getParentCode() == null ? "-1" : temp.getParentCode());
                data.setSerialNoStr(new StringBuilder(temp.getSerial()));
                boxMap.put(data.getBoxNo(), data);
            } else {
                data.setSerialNoStr(data.getSerialNoStr().append(",").append(temp.getSerial()));
            }
//            #end:27822
//            data.setSerialNo(data.getSerialNoStr().toString());
        }

        Set<Map.Entry<String, BoxNoAndSerialNoList>> entries = boxMap.entrySet();
        for (Map.Entry<String, BoxNoAndSerialNoList> entry : entries) {
            BoxNoAndSerialNoList value = entry.getValue();
            value.setSerialNo(value.getSerialNoStr().toString());
        }
        long end = System.currentTimeMillis();
        System.out.println("end:" + (end - millis));

    }

    private static List<SerialReceiving> getSerialReceivingList() {
        List<SerialReceiving> serials = new ArrayList<>();
        for (int i = 0; i < 600000; i++) {
            SerialReceiving serialReceiving = new SerialReceiving();
            serialReceiving.setGoodsNo("EMG4418102944204");
            serialReceiving.setSerial(Math.random() + "");
            serials.add(serialReceiving);
        }
        return serials;
    }
}
