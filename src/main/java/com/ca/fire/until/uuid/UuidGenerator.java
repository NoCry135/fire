package com.ca.fire.until.uuid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 */
public class UuidGenerator {

    private final static Logger log = LoggerFactory.getLogger(UuidGenerator.class);
    @Resource
    private SequenceAllotter sequenceAllotter;

    /**
     * 缓存流水id
     */
    static int indexMyUuid = 0;

    /**
     * 缓存流水号
     */
    static String MyUuid = "";

    /**
     * 缓存批次号
     */
    static String MyBatchNo = "";

    private static String localServerIdentity;

    public void setSequenceAllotter(SequenceAllotter sequenceAllotter) {
        this.sequenceAllotter = sequenceAllotter;
    }

    /**
     * 根据业务类型获取流水号和批次号
     *
     * @param type
     * @return
     */
    public MyUuidCommon getMyUUid(String type) {
        MyUuidCommon uuid = new MyUuidCommon();
        try {

            String Myuuid = "";
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");
            String dateString = formatter.format(currentTime);

            SimpleDateFormat formattermillsecond = new SimpleDateFormat("yyMMddHHmmssSSSSSS");
            String dateStringmillsecond = formattermillsecond.format(currentTime);

            if (UuidGenerator.MyUuid.length() < 12) {
                UuidGenerator.indexMyUuid += 1;
            } else {
                String leftvalue = UuidGenerator.MyUuid.substring(0, 12);
                if (dateString.equals(leftvalue))
                    UuidGenerator.indexMyUuid += 1;
                else
                    UuidGenerator.indexMyUuid = 1;
            }

            while (type.length() < 3)
                type = "0" + type;

            String liushui = String.format("%06d", UuidGenerator.indexMyUuid);

            // 服务器唯一示例号
            //根据mac地址+业务类型
            String serverid = "";
            try {
                serverid = getServerId(type);
            } catch (Exception ex) {
                log.error("获取序列号流水错误:" + ex.getMessage(), ex);
                serverid = dateStringmillsecond.substring(12, 18);
            }


            String batchno = dateString.substring(2, 10);
            if (StringUtils.isBlank(UuidGenerator.MyBatchNo) || UuidGenerator.MyBatchNo.length() < 19) {
                batchno = dateString.substring(0, 10);
            } else {
                String batchnotemp = UuidGenerator.MyBatchNo.substring(2, 10);
                if (Integer.parseInt(batchno) - Integer.parseInt(batchnotemp) > 2) {
                    batchno = dateString.substring(0, 10);
                } else {
                    batchno = dateString.substring(0, 2) + batchnotemp;
                }
            }

            Myuuid = dateString + type + serverid + liushui;
            batchno = batchno + type + serverid;
            Myuuid = Myuuid.length() > 50 ? Myuuid.substring(0, 50) : Myuuid;
            batchno = batchno.length() > 50 ? batchno.substring(0, 50) : batchno;
            UuidGenerator.MyUuid = Myuuid;
            UuidGenerator.MyBatchNo = batchno;
            uuid.setMyUuid(Myuuid);
            uuid.setBatchNo(batchno);

        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
        return uuid;
    }

    /**
     * 根据业务类型获取唯一id
     *
     * @param type
     * @return
     */
    String getServerId(String type) {
        //从本地服务器获取mac地址
        String mac;
        if (StringUtils.isNotBlank(localServerIdentity)) {
            mac = localServerIdentity;
        } else {
            mac = LocalNetUtils.getMacAddress();
            if (StringUtils.isBlank(mac)) {
                mac = LocalNetUtils.getHostName();
            }
            if (StringUtils.isBlank(mac)) {
                throw new IllegalStateException("Can not get mac!");
            }
            localServerIdentity = mac;
        }
        //根据mac和业务类型type获取唯一的serverid
        String serverid = sequenceAllotter.allot(StringUtils.join(mac, "_", type)).toString();

        while (serverid.length() < 6)
            serverid = "0" + serverid;
        return serverid;
    }

}
