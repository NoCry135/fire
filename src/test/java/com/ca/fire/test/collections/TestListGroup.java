package com.ca.fire.test.collections;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestListGroup {

    private List<Goods> goodsList = new ArrayList<>();

    @Before
    public void before() {
        Goods goods1 = new Goods("A", "-1", "书", 1);
        Goods goods2 = new Goods("B", "1", "书", 1);
        Goods goods3 = new Goods("B", "2", "书", 1);
        Goods goods4 = new Goods("B", "1", "书", 1);
        Goods goods5 = new Goods("C", "1", "书", 1);
        Goods goods6 = new Goods("C", "2", "书", 1);
//        Goods goods7 = new Goods("D", "-1", "书", 1);
        goodsList.add(goods1);
        goodsList.add(goods2);
        goodsList.add(goods3);
        goodsList.add(goods4);
        goodsList.add(goods5);
        goodsList.add(goods6);
//        goodsList.add(goods7);

    }

    @Test
    public void test01() {
        List<Goods> goodsWithLot = new ArrayList<>();
        List<Goods> goodsWithoutLot = new ArrayList<>();

        for (Goods goods : goodsList) {
            if ("-1".equals(goods.getLotNo())) {
                goodsWithoutLot.add(goods);
            } else {
                goodsWithLot.add(goods);
            }
        }

        List<List<Goods>> superList = new ArrayList<>();
        List<Goods> subList = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(goodsWithLot)) {
            Set<String> goodsNos = new HashSet<>();
            Set<String> goodsNoLotNos = new HashSet<>();

            for (Goods goods : goodsWithLot) {
                String key = goods.getGoodsNo() + "_" + goods.getLotNo();
                if (goodsNos.contains(goods.getGoodsNo())) {
                    if (goodsNoLotNos.contains(key)) {
                        subList.add(goods);
                    } else {
                        goodsNoLotNos.add(key);
                        List<Goods> subList1 = new ArrayList<>();
                        subList1.add(goods);
                        superList.add(subList1);
                    }
                } else {
                    subList.add(goods);
                    goodsNoLotNos.add(key);

                    goodsNos.add(goods.getGoodsNo());
                }
            }
            superList.add(subList);
            System.out.println(JSON.toJSONString(superList));
            System.out.println(JSON.toJSONString(superList));
        }

    }
}
