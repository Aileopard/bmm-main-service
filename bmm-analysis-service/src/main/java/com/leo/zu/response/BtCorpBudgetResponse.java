package com.leo.zu.response;

import com.leo.zu.entities.BtCorp;
import com.leo.zu.entities.FbsBudgetCorp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 单位-预算
 * @author leo-zu
 * @create 2021-01-31 8:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BtCorpBudgetResponse {

    /**
     * 四位客户号
     */
    String imCustNo;

    /**
     * 集团下预算单位列表
     */
    List<FbsBudgetCorp> corpList;

    /**
     * 已受控预算单位、部门、科室
     */
    List<String> checkNode;


}
