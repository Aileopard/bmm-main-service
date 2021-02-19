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
public class FbsBudgetDept {
    /**
     * 部门code
     */
    String deptCode;

    /**
     * 部门名称
     */
    String deptName;

    /**
     * 是否挂预算，0-否，1-是
     */
    String budgetFlag;

    /**
     * 四位客户号
     */
    String imCustNo;

    /**
     * 上级部门code
     */
    private String parentCode;

    /**
     * 所属单位code
     */
    private String corpCode;

    /**
     * 科室列表
     */
    List<FbsBudgetDept> deptList;

    /**
     * 展示，所属单位：corpCode + corpName
     */
    private String displayCorp;

    /**
     * 展示，所属部门：parentCode + parentDeptCode
     */
    private String displayParentDept;

    /**
     * 当前预算部门展示Node：deptCode+deptName
     */
    String displayNode;

    /**
     * 是否可选
     */
    Boolean disabledNode;
}
