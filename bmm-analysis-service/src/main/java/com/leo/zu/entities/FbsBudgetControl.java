package com.leo.zu.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 预算控制参数实体
 * @author leo-zu
 * @create 2021-02-11 14:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FbsBudgetControl {
    /**
     * 控制名称
     */
    private String controlName;
    /**
     * 控制类型
     */
    private String controlType;
    /**
     * 控制值
     */
    private String controlValue;
    /**
     * 控制描述
     */
    private String controlRmk;
    /**
     * 四位集团客户号
     */
    private String imCustNo;
}
