package com.leo.zu.service;

import com.leo.zu.entities.BtCorp;
import com.leo.zu.entities.FbsBudgetCorp;
import com.leo.zu.request.BtCorpBudgetRequest;
import com.leo.zu.request.FbsBudgetCorpRequest;
import com.leo.zu.response.BtCorpBudgetResponse;

import java.util.List;

/**
 * @author leo-zu
 * @create 2021-01-31 21:38
 */
public interface FbsBudgetCorpService {
    /**
     * 保存上报预算信息
     * @param request
     * @return
     */
    public boolean saveFbsBudgetCorpInfo(FbsBudgetCorpRequest request);


    /**
     * 获取所有预算单位/部门/科室信息
     * @param request
     * @return
     */
    public BtCorpBudgetResponse getBudgetCorpTree(BtCorpBudgetRequest request);

}
