package com.leo.zu.service;

import com.leo.zu.entities.FbsBudgetControl;
import com.leo.zu.request.FbsBudgetControlRequest;

/**
 * 预算参数服务类
 * @author leo-zu
 * @create 2021-02-11 14:26
 */
public interface FbsBudgetControlService {
    /**
     * 保存预算参数信息
     * @param request
     * @return
     */
    public boolean saveBudgetControlParam(FbsBudgetControlRequest request);

    /**
     * 获取预算控制参数
     * @param imCustNo
     * @param controlName
     * @return
     */
    public FbsBudgetControl getFbsBudgetControl(String imCustNo, String controlName);
}
