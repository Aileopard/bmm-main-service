package com.leo.zu.request;

import com.leo.zu.entities.FbsBudgetControl;
import com.leo.zu.entities.FbsBudgetCorp;
import com.leo.zu.entities.FbsBudgetDept;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 预算单位请求实体
 * @author leo-zu
 * @create 2021-02-11 14:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FbsBudgetCorpRequest extends BaseRequest{

    /**
     * 预算单位列表
     */
    List<FbsBudgetCorp> corpList;

    /**
     * 预算部门列表
     */
    List<FbsBudgetDept> deptList;
}
