package com.leo.zu.service;

import com.leo.zu.entities.BtCorp;
import com.leo.zu.entities.FbsBudgetCorp;
import com.leo.zu.entities.FbsBudgetDept;
import com.leo.zu.request.BtCorpBudgetRequest;

import java.util.List;

/**
 * 预算部门service
 * @author leo-zu
 * @create 2021-01-31 21:38
 */
public interface FbsBudgetDeptService {

    /**
     * 获取预算单位下的预算部门列表（树）
     * @param controlValue 层数
     * @param btCorp
     * @param checkedNode 受控预算单位、部门
     * @return
     */
    public List<FbsBudgetDept> getBudgetDeptTree(int controlValue, BtCorp btCorp, List<String> checkedNode);

    /**
     * 获取受控预算单位下的受控部门列表（树）
     * @param controlValue 层数
     * @param btCorp 受控预算单位
     * @return
     */
    public List<FbsBudgetDept> getBudgetDeptTreeOfControl(int controlValue, BtCorp btCorp);
}
