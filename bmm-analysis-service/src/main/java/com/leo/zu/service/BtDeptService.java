package com.leo.zu.service;

import com.leo.zu.entities.BtDept;
import com.leo.zu.response.BtCorpBudgetResponse;

import java.util.List;

/**
 * @author leo-zu
 * @create 2021-01-31 9:13
 */
public interface BtDeptService {

    /**
     * 根据四位客户号获取部门信息
     * @param imCustNo
     * @param corpCode
     * @return
     */
    public List<BtDept> getDeptTree(String imCustNo, String corpCode);

    /**
     * 获取部门下的科室列表
     * @param btDept
     * @return
     */
    public List<BtDept> getCurDeptTree(BtDept btDept);
}
