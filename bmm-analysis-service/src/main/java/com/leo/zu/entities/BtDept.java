package com.leo.zu.entities;

import lombok.*;

import java.util.List;

/**
 * 部门实体
 * @author leo-zu
 * @create 2021-01-31 8:59
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BtDept {

    /**
     * 部门code
     */
    String deptCode;

    /**
     * 部门名称
     */
    String deptName;

    /**
     * 上级code
     */
    String parentCode;

    /**
     * 显示code
     */
    String CorpCode;

    /**
     * 四位客户号
     */
    String imCustNo;
    /**
     * 部门下的科室列表
     */
    List<BtDept> btDeptList;
}
