package com.leo.zu.request;

import com.leo.zu.entities.BtCorp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author leo-zu
 * @create 2021-01-31 20:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BtCorpBudgetRequest extends BaseRequest{

    /**
     * 预算单位列表
     */
    List<BtCorp> corpList;
}
