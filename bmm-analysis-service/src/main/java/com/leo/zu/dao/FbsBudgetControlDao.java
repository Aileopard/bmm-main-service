package com.leo.zu.dao;

import com.leo.zu.entities.FbsBudgetControl;
import com.leo.zu.request.FbsBudgetControlRequest;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预算服务dao
 * @author leo-zu
 * @create 2021-02-11 14:31
 */
@Mapper
public interface FbsBudgetControlDao {
    /**
     * 保存预算参数信息
     * @param request
     * @return
     */
    public FbsBudgetControl getBudgetControlParam(FbsBudgetControl request);

    /**
     * 保存预算参数信息
     * @param request
     * @return
     */
    public int insertBudgetControlParam(FbsBudgetControl request);

    /**
     * 保存预算参数信息
     * @param request
     * @return
     */
    public int updateBudgetControlParam(FbsBudgetControl request);

}
