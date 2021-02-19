package com.leo.zu.service.impl;

import com.leo.zu.dao.FbsBudgetCorpDao;
import com.leo.zu.dao.FbsBudgetDeptDao;
import com.leo.zu.entities.*;
import com.leo.zu.enums.Dict;
import com.leo.zu.enums.FbsBudgetEnum;
import com.leo.zu.request.BtCorpBudgetRequest;
import com.leo.zu.service.BtCorpService;
import com.leo.zu.service.FbsBudgetControlService;
import com.leo.zu.service.FbsBudgetCorpService;
import com.leo.zu.service.FbsBudgetDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预算部门service实现类
 * @author leo-zu
 * @create 2021-01-31 21:39
 */
@Service
@Slf4j
public class FbsBudgetDeptImpl implements FbsBudgetDeptService {
    private static int ONE = 1;
    private static int TWO = 2;

    /**
     * 预算单位dao
     */
    @Resource
    private FbsBudgetDeptDao fbsBudgetDeptDao;

    /**
     * 初始化预算部门
     * @param entity
     * @return
     */
    private FbsBudgetDept initBudgetDept(BtDept entity){
        FbsBudgetDept fbsBudgetDept = new FbsBudgetDept();
        fbsBudgetDept.setDeptCode(entity.getDeptCode());
        fbsBudgetDept.setDeptName(entity.getDeptName());
        fbsBudgetDept.setImCustNo(entity.getImCustNo());
        fbsBudgetDept.setParentCode(entity.getParentCode());
        fbsBudgetDept.setCorpCode(entity.getCorpCode());
        fbsBudgetDept.setBudgetFlag("0");
        fbsBudgetDeptDao.saveFbsBudgetDeptInfo(fbsBudgetDept);
        return fbsBudgetDept;
    }

    /**
     * 功能描述 获取单位下的所有部门科室信息（树）
     * @param controlValue 层数
     * @param btCorp
     * @return
     */
    @Override
    public List<FbsBudgetDept> getBudgetDeptTree(int controlValue, BtCorp btCorp, List<String> checkedNode) {
        // 保存结果
        List<FbsBudgetDept> resultDeptTree = new ArrayList<>();
        if(controlValue == 0){
            return resultDeptTree;
        }
        // controlValue：1-部门 2-科室
        if (controlValue == ONE || controlValue == TWO){
            // 获取单位下所有部门信息
            List<BtDept> btDeptList = btCorp.getBtDeptList();

            // 获取数据库中该单位下所有已入库的部门、科室列表
            List<FbsBudgetDept> deptOfControl = fbsBudgetDeptDao.getFbsBudgetDeptByCorpCode(btCorp.getImCustNo(),btCorp.getCorpCode());
            // 将部门列表转换为map
            Map<String, FbsBudgetDept> deptMap = new HashMap<>(btDeptList.size());
            for (FbsBudgetDept entity : deptOfControl){
                String key = entity.getImCustNo()+entity.getCorpCode()+entity.getParentCode()+entity.getDeptCode();
                deptMap.put(key, entity);
            }

            // 遍历单位下的一级部门
            for (BtDept entity : btDeptList){
                String key = entity.getImCustNo()+entity.getCorpCode()+entity.getParentCode()+entity.getDeptCode();
                FbsBudgetDept budgetDept = deptMap.get(key);
                // 如果数据库中没有该预算部门，则初始化入库
                if (budgetDept == null){
                    budgetDept = initBudgetDept(entity);
                }

                String displayNode = "(" + budgetDept.getDeptCode() + ")" + budgetDept.getDeptName();
                budgetDept.setDisplayNode(displayNode);
                // 如果该部门受控且为叶子节点，则添加到list
                if (controlValue == 1 && Dict.ONE.getValue().equals(budgetDept.getBudgetFlag())){
                    checkedNode.add(displayNode);
                }

                // 获取当前部门的下级科室列表
                List<FbsBudgetDept> deptTrees = getBudgetDeptTrees(controlValue - 1, entity, deptMap, checkedNode);
                budgetDept.setDeptList(deptTrees);
                resultDeptTree.add(budgetDept);
            }
        }
        return resultDeptTree;
    }


    /**
     * 功能描述 获取当前部门下的所有科室信息（树）
     * @param controlValue 层数
     * @param btDept 当前部门
     * @param deptMap 当前集团下所有已经入库的预算部门、科室信息
     * @return
     */
    public List<FbsBudgetDept> getBudgetDeptTrees(int controlValue, BtDept btDept, Map<String, FbsBudgetDept> deptMap, List<String> checkedNode) {
        // 保存结果
        List<FbsBudgetDept> resultDeptTree = new ArrayList<>();
        if (controlValue == 0){
            return resultDeptTree;
        }

        // 获取部门下所有科室信息
        List<BtDept> deptList = btDept.getBtDeptList();

        // 遍历所有部门下科室列表
        for (BtDept entity: deptList){
            String key = entity.getImCustNo()+entity.getCorpCode()+entity.getParentCode()+entity.getDeptCode();
            FbsBudgetDept budgetDept = deptMap.get(key);
            // 如果数据库中不存在该预算科室，则初始化入库
            if (budgetDept == null){
                budgetDept = initBudgetDept(entity);
            }

            String displayNode = "(" + budgetDept.getDeptCode() + ")" + budgetDept.getDeptName();
            budgetDept.setDisplayNode(displayNode);

            // 如果该部门受控且为叶子节点，则添加到list
            if (controlValue == 1 && Dict.ONE.getValue().equals(budgetDept.getBudgetFlag())){
                checkedNode.add(displayNode);
            }

            // 获取下级当前科室下的所有下级科室信息
            List<FbsBudgetDept> deptTrees = getBudgetDeptTrees(controlValue - 1, entity, deptMap, checkedNode);
            budgetDept.setDeptList(deptTrees);
            resultDeptTree.add(budgetDept);
        }
        return resultDeptTree;
    }


    /**
     * 获取受控预算单位下的受控部门列表（树）
     * @param controlValue 层数
     * @param btCorp 受控预算单位
     * @return
     */
    @Override
    public List<FbsBudgetDept> getBudgetDeptTreeOfControl(int controlValue, BtCorp btCorp) {
        if (controlValue == 0){
            return null;
        }

        // 保存受控部门list
        List<FbsBudgetDept> resultDepts = new ArrayList<>();

        // 获取单位下的部门列表
        List<BtDept> deptList = btCorp.getBtDeptList();

        // 获取单位下所有受控预算部门列表
        List<FbsBudgetDept> fbsDepts = fbsBudgetDeptDao.getFbsBudgetDeptofCtrlBycorp(btCorp.getImCustNo(), btCorp.getCorpCode(), Dict.ONE.getValue());
        // 将受控预算部门list转换为map
        Map<String, FbsBudgetDept> deptMap = new HashMap<>(fbsDepts.size());
        for (FbsBudgetDept entity : fbsDepts){
            String key = entity.getImCustNo() + entity.getCorpCode() + entity.getParentCode() + entity.getDeptCode();
            deptMap.put(key, entity);
        }

        // 遍历所有部门列表，筛选出受控的预算部门
        for (BtDept entity : deptList){
            String key = entity.getImCustNo() + entity.getCorpCode() + entity.getParentCode() + entity.getDeptCode();
            FbsBudgetDept budgetDept = deptMap.get(key);
            if (budgetDept != null){
                // 用于前端显示
                String displayNode = "(" + budgetDept.getDeptCode() + ")" + budgetDept.getDeptName();
                budgetDept.setDisplayNode(displayNode);

                // 受控，获取当前受控部门下的科室list
                List<FbsBudgetDept> depts = getBudgetDeptTreeOfControls(controlValue - 1, entity,deptMap);
                budgetDept.setDeptList(depts);

                budgetDept.setDisabledNode(false);
                if (controlValue -1 >= 0 && budgetDept.getDeptList() != null){
                    // 当当前编制方式还有下级时，且下级科室不为空
                    budgetDept.setDisabledNode(true);
                }

                resultDepts.add(budgetDept);
            }
        }

        return resultDepts;
    }

    /**
     * 获取受控预算部门下的受控科室列表（树）
     * @param controlValue 层数
     * @param btDept 受控预算部门
     * @return
     */
    private List<FbsBudgetDept> getBudgetDeptTreeOfControls(int controlValue, BtDept btDept,Map<String, FbsBudgetDept> deptMap) {
        if (controlValue == 0){
            return null;
        }

        // 保存受控部门list
        List<FbsBudgetDept> resultDepts = new ArrayList<>();

        // 获取单位下的部门列表
        List<BtDept> deptList = btDept.getBtDeptList();
        // 遍历所有部门列表，筛选出受控的预算部门
        for (BtDept entity : deptList){
            String key = entity.getImCustNo() + entity.getCorpCode() + entity.getParentCode() + entity.getDeptCode();
            FbsBudgetDept budgetDept = deptMap.get(key);
            if (budgetDept != null){
                // 用于前端显示
                String displayNode = "(" + budgetDept.getDeptCode() + ")" + budgetDept.getDeptName();
                budgetDept.setDisplayNode(displayNode);

                // 受控，获取当前受控部门下的科室list
                List<FbsBudgetDept> depts = getBudgetDeptTreeOfControls(controlValue - 1, entity,deptMap);
                budgetDept.setDeptList(depts);

                budgetDept.setDisabledNode(false
                );
                if (controlValue -1 >= 0 && budgetDept.getDeptList() != null){
                    // 当当前编制方式还有下级时，且下级科室不为空
                    budgetDept.setDisabledNode(true);
                }

                resultDepts.add(budgetDept);
            }
        }

        return resultDepts;
    }
}
