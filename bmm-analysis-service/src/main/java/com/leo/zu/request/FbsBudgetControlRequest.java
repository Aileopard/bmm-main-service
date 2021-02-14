package com.leo.zu.request;

import com.leo.zu.entities.FbsBudgetControl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 预算参数请求list
 * @author leo-zu
 * @create 2021-02-11 14:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FbsBudgetControlRequest extends BaseRequest{

    String controlValue;
    /**
     * 预算参数请求，其中只保存参数名称和参数值
     */
    List<FbsBudgetControl> fbsBudgetControlList;
}
