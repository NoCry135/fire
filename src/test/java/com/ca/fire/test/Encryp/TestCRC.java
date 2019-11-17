package com.ca.fire.test.Encryp;

import java.util.zip.CRC32;

public class TestCRC {

    public static void main(String[] args) {
        CRC32 crc32 = new CRC32();
        crc32.update("707-10-87-OT108719042500000010".getBytes());
        System.out.println(crc32.getValue());
        crc32.update("707-10-87-OT108719042500000009".getBytes());
        System.out.println(crc32.getValue());

    }
}
