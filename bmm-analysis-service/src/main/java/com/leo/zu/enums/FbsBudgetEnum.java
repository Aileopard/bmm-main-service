package com.leo.zu.enums;

import lombok.Data;

/**
 * @author leo-zu
 * @create 2021-02-11 15:04
 */
public enum FbsBudgetEnum {
    /**
     * 编制方式
     */
    FBS_COMPILE_FLAG("FBS_COMPILE_FLAG", "2", "0", "编制方式 0-单位 1-部门 2-科室");


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

    FbsBudgetEnum(String controlName, String controlType, String controlValue, String controlRmk) {
        this.controlName = controlName;
        this.controlType = controlType;
        this.controlValue = controlValue;
        this.controlRmk = controlRmk;
    }

    public String getControlName() {
        return controlName;
    }

    public void setControlName(String controlName) {
        this.controlName = controlName;
    }

    public String getControlType() {
        return controlType;
    }

    public void setControlType(String controlType) {
        this.controlType = controlType;
    }

    public String getControlValue() {
        return controlValue;
    }

    public void setControlValue(String controlValue) {
        this.controlValue = controlValue;
    }

    public String getControlRmk() {
        return controlRmk;
    }

    public void setControlRmk(String controlRmk) {
        this.controlRmk = controlRmk;
    }

    /**
     * 通过controlName获取枚举
     * @param controlName
     * @return
     */
    public static FbsBudgetEnum getFbsBudgetEnum(String controlName) {
        for (FbsBudgetEnum c : FbsBudgetEnum.values()) {
            if (c.getControlName().equals(controlName)) {
                return c;
            }
        }
        return null;
    }
}
