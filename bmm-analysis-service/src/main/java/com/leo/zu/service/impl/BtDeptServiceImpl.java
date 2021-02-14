package com.leo.zu.service.impl;

import com.leo.zu.dao.BtCorpDao;
import com.leo.zu.dao.BtDeptDao;
import com.leo.zu.entities.BtCorp;
import com.leo.zu.entities.BtDept;
import com.leo.zu.response.BtCorpBudgetResponse;
import com.leo.zu.service.BtCorpService;
import com.leo.zu.service.BtDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author leo-zu
 * @create 2021-01-31 9:14
 */
@Service
@Slf4j
public class BtDeptServiceImpl implements BtDeptService {

    @Resource
    private BtDeptDao btDeptDao;


    /**
     * 获取集团单位下，部门信息
     * @param imCustNo
     * @param corpCode
     * @return
     */
    @Override
    public List<BtDept> getDeptTree(String imCustNo, String corpCode) {
        // 用于保存结果
        List<BtDept> resultList = new ArrayList<>();

        // 查询单位下一级部门列表
        List<BtDept> btDeptList = btDeptDao.getDeptBydeptCode(imCustNo, corpCode,corpCode);
        for (BtDept btDept: btDeptList){
            // 获取一级部门下的科室信息
            List<BtDept> curDeptTree = getCurDeptTree(btDept);
            btDept.setBtDeptList(curDeptTree);
            resultList.add(btDept);
        }

        return resultList;
    }

    /**
     * 获取部门下的，部门信息（递归）
     * @param btDept
     * @return
     */
    @Override
    public List<BtDept> getCurDeptTree(BtDept btDept) {
        if (btDept == null) {
            return null;
        }
        // 用于保存结果
        List<BtDept> resultList = new ArrayList<>();
        List<BtDept> curDeptList = btDeptDao.getDeptBydeptCode(btDept.getImCustNo(), btDept.getCorpCode(),btDept.getDeptCode());
        for (BtDept entity : curDeptList){
            // 获取当前部门下的所有科室列表
            List<BtDept> curDeptTree = getCurDeptTree(entity);
            entity.setBtDeptList(curDeptList);
            resultList.add(entity);
        }
        return resultList;
    }


}
