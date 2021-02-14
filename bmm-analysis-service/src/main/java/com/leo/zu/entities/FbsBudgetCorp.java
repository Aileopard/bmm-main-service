package com.leo.zu.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author leo-zu
 * @create 2021-01-31 21:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FbsBudgetCorp {
    /**
     * 单位id
     */
    String corpCode;

    /**
     * 单位名称
     */
    String corpName;

    /**
     * 是否挂预算，0-否，1-是
     */
    String budgetFlag;

    /**
     * 四位客户号
     */
    String imCustNo;

    /**
     * 展示Node：corpCode+corpName
     */
    String displayNode;

    /**
     * 部门列表
     */
    List<FbsBudgetDept> deptList;
}
