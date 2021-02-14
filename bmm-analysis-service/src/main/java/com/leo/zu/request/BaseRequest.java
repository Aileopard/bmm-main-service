package com.leo.zu.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求父类
 * @author leo-zu
 * @create 2021-02-13 14:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseRequest {
    /**
     * 四位集团号
     */
    String imCustNo;
}
