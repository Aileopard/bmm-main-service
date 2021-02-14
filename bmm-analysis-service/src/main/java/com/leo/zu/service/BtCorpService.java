package com.leo.zu.service;

import com.leo.zu.entities.BtCorp;
import com.leo.zu.request.BtCorpBudgetRequest;
import com.leo.zu.response.BtCorpBudgetResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author leo-zu
 * @create 2021-01-31 9:13
 */
public interface BtCorpService {

    /**
     * 根据四位客户号获取单位/部门/科室信息
     * @param imCustNo
     * @return
     */
    public List<BtCorp> getCorpTreeByImCustNo(String imCustNo);

}
