package com.leo.zu.dao;

import com.leo.zu.entities.BtCorp;
import com.leo.zu.entities.FbsBudgetCorp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author leo-zu
 * @create 2021-01-31 21:26
 */
@Mapper
public interface FbsBudgetCorpDao {
    /**
     * 新增预算单位信息
     * @param fbsBudgetCorp
     */
    public void saveFbsBudgetCorpInfo(FbsBudgetCorp fbsBudgetCorp);

    /**
     * 更新预算单位信息
     * @param fbsBudgetCorp
     */
    public void updateFbsBudgetCorpInfo(FbsBudgetCorp fbsBudgetCorp);

    /**
     * 获取集团下预算单位信息
     * @param imCustNo
     * @return
     */
    public List<FbsBudgetCorp> getFbsBudgetCorpByImCustNo(String imCustNo);

    /**
     * 获取预算单位信息
     * @param fbsBudgetCorp
     * @return
     */
    public FbsBudgetCorp getFbsBudgetCorp(FbsBudgetCorp fbsBudgetCorp);

    /**
     * 获取集团下受控预算单位信息
     * @param imCustNo
     * @param budgetFlag
     * @return
     */
    public List<FbsBudgetCorp> getFbsBudgetCorpOfControl(@Param("imCustNo")String imCustNo, @Param("budgetFlag")String budgetFlag);
}
