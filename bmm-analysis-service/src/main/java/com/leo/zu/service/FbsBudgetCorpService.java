package com.leo.zu.service;

import com.leo.zu.entities.BtCorp;
import com.leo.zu.entities.FbsBudgetCorp;
import com.leo.zu.request.BaseRequest;
import com.leo.zu.request.BtCorpBudgetRequest;
import com.leo.zu.request.FbsBudgetCorpRequest;
import com.leo.zu.response.BtCorpBudgetResponse;
import com.leo.zu.response.FbsNeedCompBudResponse;

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
     * 获取所有预算单位/部门/科室信息（树）
     * @param request
     * @return
     */
    public BtCorpBudgetResponse getBudgetCorpTree(BaseRequest request);

    /**
     * 取所有受控的预算单位/部门/科室信息（树）
     * @param request
     * @return
     */
    public BtCorpBudgetResponse getBudgetCorpTreeOfControl(BaseRequest request);

    /**
     * 获取需要编制的预算单位
     * @param request
     * @return
     */
    public FbsNeedCompBudResponse getNeedCompBudgetCorp(BaseRequest request);
}
