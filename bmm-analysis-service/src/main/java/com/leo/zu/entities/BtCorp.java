package com.leo.zu.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author leo-zu
 * @create 2021-01-31 8:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BtCorp {

    /**
     * 单位code
     */
    String corpCode;

    /**
     * 单位名称
     */
    String corpName;

    /**
     * 显示code
     */
    String displayCode;

    /**
     * 四位客户号
     */
    String imCustNo;
    /**
     * 单位下部门、科室信息
     */
    List<BtDept> btDeptList;
}
