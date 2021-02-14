package com.leo.zu.service.impl;

import com.leo.zu.dao.BtCorpDao;
import com.leo.zu.dao.FbsBudgetControlDao;
import com.leo.zu.entities.BtCorp;
import com.leo.zu.entities.BtDept;
import com.leo.zu.response.BtCorpBudgetResponse;
import com.leo.zu.service.BtCorpService;
import com.leo.zu.service.BtDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leo-zu
 * @create 2021-01-31 9:14
 */
@Service
@Slf4j
public class BtCorpServiceImpl implements BtCorpService {

    @Resource
    private BtCorpDao btCorpDao;

    @Resource
    private FbsBudgetControlDao fbsBudgetControlDao;

    @Resource
    private BtDeptService btDeptService;

    /**
     * 获取集团下单位/部门/科室列表（树）
     * @param imCustNo
     * @return
     */
    @Override
    public List<BtCorp> getCorpTreeByImCustNo(String imCustNo) {
        // 用于保存结果
        List<BtCorp> btCorpList = new ArrayList<>();

        // 获取集团下的所有单位信息
        List<BtCorp> corpList = btCorpDao.getCorpBycorpCode(imCustNo);

        // 获取每个单位下的部门信息
        for (BtCorp btCorp : corpList){
            List<BtDept> deptList = btDeptService.getDeptTree(btCorp.getImCustNo(), btCorp.getCorpCode());
            btCorp.setBtDeptList(deptList);
            btCorpList.add(btCorp);
        }

        return btCorpList;
    }

}
