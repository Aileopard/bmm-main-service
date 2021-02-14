package com.leo.zu.enums;

/**
 * @author leo-zu
 * @create 2021-02-11 15:04
 */
public enum Dict {
    /**
     * 0
     */
    ZERO("ZERO","0"),
    ONE("ONE","1"),
    TWO("TWO","2");



    /**
     * 控制名称
     */
    private String name;
    /**
     * 控制值
     */
    private String Value;

    Dict(String controlName, String controlValue) {
        this.name = controlName;
        this.Value = controlValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    /**
     * 通过name获取枚举
     * @param name
     * @return
     */
    public static Dict getEnum(String name) {
        for (Dict c : Dict.values()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }
}
