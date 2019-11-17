package com.ca.fire.dao;

import com.ca.fire.domain.bean.OrderMain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMainDao {

    Integer insert(OrderMain orderMain);

    Integer update(OrderMain orderMain);

    Integer updateByPrimaryKey(Long id);

    Integer deleteById(Long id);

    OrderMain selectById(Long id);

    OrderMain selectOrderMainByCondition(OrderMain condition);

    List<OrderMain> queryOrderMainList(OrderMain condition);

    List<OrderMain> queryOrderMainPage(OrderMain condition);

    /**
     * 批量插入方法
     */
    Integer batchInsert(@Param("list") List<OrderMain> orderMainList);

    /**
     * 批量删除方法
     */
    Integer batchDelete(@Param("list") List<Long> idList);

    /**
     * 批量插入或更新方法（不存在就插入，否则更新）
     */
    Integer batchInsertOrUpdate(@Param("list") List<OrderMain> orderMainList);

    /**
     * 物理删除
     */
    Integer deletePhysics(@Param("id") Long id);

}
