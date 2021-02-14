package com.leo.zu.dao;

import com.leo.zu.entities.BtCorp;
import com.leo.zu.entities.BtDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 单位dao
 * @author leo-zu
 * @create 2021-01-31 9:07
 */
@Mapper
public interface BtDeptDao {

    /**
     * 通过parentCode获取下级子单位/部门
     * @param imCustNo
     * @param corpCode
     * @param parentCode
     * @return
     */
    public List<BtDept> getDeptBydeptCode(@Param("imCustNo") String imCustNo,@Param("corpCode") String corpCode, @Param("parentCode") String parentCode);

}
