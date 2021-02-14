package com.leo.zu.dao;

import com.leo.zu.entities.FbsBudgetCorp;
import com.leo.zu.entities.FbsBudgetDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author leo-zu
 * @create 2021-01-31 21:26
 */
@Mapper
public interface FbsBudgetDeptDao {
    /**
     * 新增预算部门信息
     * @param fbsBudgetdept
     */
    public void saveFbsBudgetDeptInfo(FbsBudgetDept fbsBudgetdept);

    /**
     * 更新预算部门信息
     * @param fbsBudgetdept
     */
    public void updateFbsBudgetDeptInfo(FbsBudgetDept fbsBudgetdept);

    /**
     * 获取集团下预算部门信息
     * @param imCustNo
     * @return
     */
    public List<FbsBudgetDept> getFbsBudgetDeptByImCustNo(@Param("imCustNo")String imCustNo);


    /**
     * 获取单位下预算部门信息
     * @param imCustNo
     * @param corpCode
     * @return
     */
    public List<FbsBudgetDept> getFbsBudgetDeptByCorpCode(@Param("imCustNo")String imCustNo, @Param("corpCode")String corpCode);

    /**
     * 获取预算部门信息
     * @param fbsBudgetDept
     * @return
     */
    FbsBudgetCorp getFbsBudgetDept(FbsBudgetDept fbsBudgetDept);
}
