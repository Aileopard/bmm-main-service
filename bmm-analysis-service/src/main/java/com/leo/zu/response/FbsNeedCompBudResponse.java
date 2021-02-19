package com.leo.zu.response;

import com.leo.zu.entities.FbsBudgetCorp;
import com.leo.zu.entities.FbsBudgetDept;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author leo-zu
 * @create 2021-02-16 9:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FbsNeedCompBudResponse {
    /**
     * 四位客户号
     */
    private String imCustNo;
    /**
     * 需要编制预算单位
     */
    private List<FbsBudgetCorp> needCompCorpList;

    /**
     * 需要编制的预算部门、科室
     */
    private List<FbsBudgetDept> needCompDeptList;

    /**
     * 编制方式
     */
    int compileFlag;
}
