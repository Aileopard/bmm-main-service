package com.leo.zu.service.impl;

import com.leo.zu.dao.FbsBudgetCorpDao;
import com.leo.zu.dao.FbsBudgetDeptDao;
import com.leo.zu.entities.BtCorp;
import com.leo.zu.entities.FbsBudgetControl;
import com.leo.zu.entities.FbsBudgetCorp;
import com.leo.zu.entities.FbsBudgetDept;
import com.leo.zu.enums.Dict;
import com.leo.zu.enums.FbsBudgetEnum;
import com.leo.zu.request.BtCorpBudgetRequest;
import com.leo.zu.request.FbsBudgetCorpRequest;
import com.leo.zu.response.BtCorpBudgetResponse;
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
 * @author leo-zu
 * @create 2021-01-31 21:39
 */
@Service
@Slf4j
public class FbsBudgetCorpImpl implements FbsBudgetCorpService {
    /**
     * 预算单位dao
     */
    @Resource
    private FbsBudgetCorpDao fbsBudgetCorpDao;
    /**
     * 预算控制参数service
     */
    @Resource
    private FbsBudgetControlService fbsBcService;
    /**
     * 单位service
     */
    @Resource
    private BtCorpService btCorpService;
    /**
     * 预算部门service
     */
    @Resource
    private FbsBudgetDeptService fbsBudgetDeptService;

    /**
     * 预算部门dao
     */
    @Resource
    private FbsBudgetDeptDao fbsBudgetDeptDao;


    /**
     * 保存预算单位信息
     * @param request
     * @return
     */
    @Override
    public boolean saveFbsBudgetCorpInfo(FbsBudgetCorpRequest request) {
        try{
            // 保存预算单位，这里要注意更新数据库中原来受控的单位信息，不受控了
            List<FbsBudgetCorp> fbsBudgetCorps = fbsBudgetCorpDao.getFbsBudgetCorpByImCustNo(request.getImCustNo());
            List<FbsBudgetCorp> corpList = request.getCorpList();
            if (corpList != null && corpList.size() != 0){
                // 将前端传来的受控预算单位list，转换为map
                Map<String, FbsBudgetCorp> corpMap = new HashMap<>(corpList.size());
                for (FbsBudgetCorp entity : corpList){
                    corpMap.put(entity.getCorpCode(),entity);
                }

                // 遍历数据库中所有预算单位
                for (FbsBudgetCorp entity : fbsBudgetCorps){
                    FbsBudgetCorp fbsBudgetCorp = corpMap.get(entity.getCorpCode());
                    // 判断当前单位是否受控
                    if (fbsBudgetCorp != null){
                        // 受控
                        entity.setBudgetFlag(Dict.ONE.getValue());
                    }else {
                        // 不受控
                        entity.setBudgetFlag(Dict.ZERO.getValue());
                    }
                    // 更新预算单位信息
                    fbsBudgetCorpDao.updateFbsBudgetCorpInfo(entity);
                }
            }

            // 保存预算部门、科室，与上面同理
            // 获取数据库中集团下所有部门科室信息list
            List<FbsBudgetDept> fbsBudgetDepts = fbsBudgetDeptDao.getFbsBudgetDeptByImCustNo(request.getImCustNo());
            List<FbsBudgetDept> deptList = request.getDeptList();
            if (deptList != null && deptList.size() != 0){
                // 将前端传来的受控部门信息list，转换为map
                Map<String, FbsBudgetDept> deptMap = new HashMap<>(corpList.size());
                for (FbsBudgetDept entity : deptList){
                    String key = entity.getImCustNo() + entity.getCorpCode() + entity.getParentCode() + entity.getDeptCode();
                    deptMap.put(key,entity);
                }

                // 遍历集团下所有部门科室list
                for (FbsBudgetDept entity : fbsBudgetDepts){
                    String key = entity.getImCustNo() + entity.getCorpCode() + entity.getParentCode() + entity.getDeptCode();
                    FbsBudgetDept budgetDept = deptMap.get(key);
                    if (budgetDept != null){
                        // 受控
                        entity.setBudgetFlag(Dict.ONE.getValue());
                    }else {
                        // 不受控
                        entity.setBudgetFlag(Dict.ZERO.getValue());
                    }
                    // 更新预算部门科室信息
                    fbsBudgetDeptDao.updateFbsBudgetDeptInfo(entity);
                }
            }
            return true;
        }catch (Exception e){
            log.info("保存预算单位、部门信息失败");
            return false;
        }
    }


    /**
     * 获取集团下的预算单位/部门/科室树
     * @param request
     * @return
     */
    @Override
    public BtCorpBudgetResponse getBudgetCorpTree(BtCorpBudgetRequest request) {
        if (request == null){
            return null;
        }

        String imCustNo = request.getImCustNo();
        BtCorpBudgetResponse response = new BtCorpBudgetResponse();
        // 保存集团下预算单位、部门、科室列表
        List<FbsBudgetCorp> resultCorpList = new ArrayList<>();
        // 保存集团下已受控单位、部门、科室列表
        List<String> resultCheckedNode = new ArrayList<>();

        // 获取编制方式
        FbsBudgetControl fbsBudgetControl = fbsBcService.getFbsBudgetControl(imCustNo,
                FbsBudgetEnum.FBS_COMPILE_FLAG.getControlName());
        if (fbsBudgetControl == null){
            log.info("请先设置编制方式");
            return response;
        }

        // 根据四位客户号获取集团下的所有单位信息
        List<BtCorp> btCorpList = btCorpService.getCorpTreeByImCustNo(request.getImCustNo());
        log.info("整个集团的单位部门科室树："+btCorpList.toString());
        if (btCorpList == null && btCorpList.size()==0){
            log.info("该集团无单位信息，请先加挂");
            return response;
        }

        // 获取所有预算单位
        List<FbsBudgetCorp> budgetCorpOfControl = fbsBudgetCorpDao.getFbsBudgetCorpByImCustNo(imCustNo);
        // 将预算单位list转换为map
        Map<String, FbsBudgetCorp> budgetCorpMap = new HashMap<>(btCorpList.size());
        for (FbsBudgetCorp entity : budgetCorpOfControl){
            budgetCorpMap.put(entity.getCorpCode(), entity);
        }

        // 根据编制方式获取预算单位信息
        int controlValue = Integer.parseInt(fbsBudgetControl.getControlValue());
        for (BtCorp entity: btCorpList){
            // 判断数据库中是否有该单位
            FbsBudgetCorp controlCorp = budgetCorpMap.get(entity.getCorpCode());
            if (controlCorp == null){
                // 未入库
                controlCorp = initBudgetCorp(entity);
            }
            // 设置前端展示的名称
            String displayNode = "(" + controlCorp.getCorpCode() + ")" + controlCorp.getCorpName();
            controlCorp.setDisplayNode(displayNode);

            // 如果编制方式为单位，并且受控（叶子节点），则添加
            if(controlValue == 0 && Dict.ONE.getValue().equals(controlCorp.getBudgetFlag())){
                resultCheckedNode.add(controlCorp.getDisplayNode());
            }

            // 获取该单位下的部门和科室信息
            List<FbsBudgetDept> budgetDeptTrees = fbsBudgetDeptService.getBudgetDeptTree(controlValue, entity,resultCheckedNode);
            controlCorp.setDeptList(budgetDeptTrees);
            resultCorpList.add(controlCorp);
        }

        response.setCorpList(resultCorpList);
        response.setCheckNode(resultCheckedNode);
        return response;
    }


    /**
     * 初始化预算单位
     * @param entity
     * @return
     */
    private FbsBudgetCorp initBudgetCorp(BtCorp entity){
        FbsBudgetCorp fbsBudgetCorp = new FbsBudgetCorp();
        fbsBudgetCorp.setCorpCode(entity.getCorpCode());
        fbsBudgetCorp.setCorpName(entity.getCorpName());
        fbsBudgetCorp.setImCustNo(entity.getImCustNo());
        fbsBudgetCorp.setBudgetFlag("0");
        fbsBudgetCorpDao.saveFbsBudgetCorpInfo(fbsBudgetCorp);
        return fbsBudgetCorp;
    }


    /**
     * 获取集团下的受控预算单位/部门/科室树
     * @param request
     * @return
     */
    @Override
    public BtCorpBudgetResponse getBudgetCorpTreeOfControl(BtCorpBudgetRequest request) {
        if (request == null){
            return null;
        }

        String imCustNo = request.getImCustNo();
        BtCorpBudgetResponse response = new BtCorpBudgetResponse();

        // 获取编制方式
        FbsBudgetControl fbsBudgetControl = fbsBcService.getFbsBudgetControl(imCustNo,
                FbsBudgetEnum.FBS_COMPILE_FLAG.getControlName());
        if (fbsBudgetControl == null){
            log.info("请先设置编制方式");
            return response;
        }

        // 根据四位客户号获取集团下的所有单位信息
        List<BtCorp> btCorpList = btCorpService.getCorpTreeByImCustNo(request.getImCustNo());
        log.info("整个集团的单位部门科室树："+btCorpList.toString());
        if (btCorpList == null && btCorpList.size()==0){
            log.info("该集团无单位信息，请先加挂");
            return response;
        }

        // 保存受控预算单位
        List<FbsBudgetCorp> resultCorpList = new ArrayList<>();

        // 获取数据库中集团下所有受控预算单位信息
        List<FbsBudgetCorp> budgetCorpList = fbsBudgetCorpDao.getFbsBudgetCorpOfControl(imCustNo, Dict.ONE.getValue());
        // 将受控预算list转换为map
        Map<String, FbsBudgetCorp> corpMap = new HashMap<>(budgetCorpList.size());
        for (FbsBudgetCorp entity : budgetCorpList){
            corpMap.put(entity.getCorpCode(), entity);
        }

        // 遍历所有单位，筛选出受控的预算单位
        for (BtCorp entity : btCorpList){
            FbsBudgetCorp budgetCorp = corpMap.get(entity.getCorpCode());
            if (budgetCorp != null){
                // 设置前端展示的名称
                String displayNode = "(" + budgetCorp.getCorpCode() + ")" + budgetCorp.getCorpName();
                budgetCorp.setDisplayNode(displayNode);

                int controlValue = Integer.parseInt(fbsBudgetControl.getControlValue());
                // 获取该受控单位下的所有受控部门、科室信息
                List<FbsBudgetDept> depts = fbsBudgetDeptService.getBudgetDeptTreeOfControl(controlValue, entity);
                budgetCorp.setDeptList(depts);

                budgetCorp.setDisabledNode(false);
                if (budgetCorp.getDeptList() != null && controlValue - 1 > 0){
                    // 当预算单位有下级预算部门，且编制方式为部门或科室时
                    budgetCorp.setDisabledNode(true);
                }

                resultCorpList.add(budgetCorp);
            }
        }

        response.setCorpList(resultCorpList);

        return response;
    }
}
