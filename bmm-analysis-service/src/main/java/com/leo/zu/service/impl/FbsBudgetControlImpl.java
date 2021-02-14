package com.leo.zu.service.impl;

import com.leo.zu.dao.FbsBudgetControlDao;
import com.leo.zu.entities.FbsBudgetControl;
import com.leo.zu.enums.FbsBudgetEnum;
import com.leo.zu.request.FbsBudgetControlRequest;
import com.leo.zu.service.FbsBudgetControlService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author leo-zu
 * @create 2021-02-11 14:29
 */
@Service
@Slf4j
public class FbsBudgetControlImpl implements FbsBudgetControlService {

    @Resource
    private FbsBudgetControlDao fbsBudgetControlDao;

    /**
     * 功能描述：保存预算参数信息
     * @param request
     * @return
     */
    @Override
    public boolean saveBudgetControlParam(FbsBudgetControlRequest request) {
        if(request != null){
            List<FbsBudgetControl> list = request.getFbsBudgetControlList();
            if (list != null && list.size() != 0) {
                try{
                    for (FbsBudgetControl entity : list){
                        entity.setImCustNo(request.getImCustNo());
                        // 查询数据库中是否存在该实体
                        FbsBudgetControl param = fbsBudgetControlDao.getBudgetControlParam(entity);
                        if (param != null){
                            // 存在，更新
                            param.setControlValue(entity.getControlValue());
                            fbsBudgetControlDao.updateBudgetControlParam(param);
                        }else {
                            // 不存在，添加
                            entity.setImCustNo(request.getImCustNo());
                            FbsBudgetEnum fbsBudgetEnum = FbsBudgetEnum.getFbsBudgetEnum(entity.getControlName());
                            if(fbsBudgetEnum != null) {
                                entity.setControlRmk(fbsBudgetEnum.getControlRmk());
                                entity.setControlType(fbsBudgetEnum.getControlType());
                                fbsBudgetControlDao.insertBudgetControlParam(entity);
                            }else {
                                log.info("无该预算参数");
                                return false;
                            }
                        }
                    }
                    return true;
                }catch (Exception e){
                    log.info("保存异常");
                    return false;
                }
            }
        }

        return false;
    }

    /**
     * 获取预算控制参数
     * @param imCustNo
     * @param controlName
     * @return
     */
    @Override
    public FbsBudgetControl getFbsBudgetControl(String imCustNo, String controlName) {
        FbsBudgetControl fbsBudgetControl = new FbsBudgetControl();
        FbsBudgetEnum fbsBudgetEnum = FbsBudgetEnum.getFbsBudgetEnum(controlName);
        fbsBudgetControl.setImCustNo(imCustNo);
        fbsBudgetControl.setControlName(controlName);
        fbsBudgetControl.setControlType(fbsBudgetEnum.getControlType());
        return fbsBudgetControlDao.getBudgetControlParam(fbsBudgetControl);
    }

    @Test
    public void test(){
        FbsBudgetEnum fbsBudgetEnum = FbsBudgetEnum.getFbsBudgetEnum("fbs_budget_compile");
    }

}
