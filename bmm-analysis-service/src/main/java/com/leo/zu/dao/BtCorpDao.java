package com.leo.zu.dao;

import com.leo.zu.entities.BtCorp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 单位dao
 * @author leo-zu
 * @create 2021-01-31 9:07
 */
@Mapper
public interface BtCorpDao {

    /**
     * 通过imCustNo获取下级子单位/部门
     * @param imCustNo
     * @return
     */
    public List<BtCorp> getCorpBycorpCode(@Param("imCustNo") String imCustNo);

}
