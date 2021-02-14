package com.leo.zu.controller;

import com.leo.zu.entities.BtCorp;
import com.leo.zu.entities.CommonResult;
import com.leo.zu.entities.FbsBudgetControl;
import com.leo.zu.entities.FbsBudgetCorp;
import com.leo.zu.enums.FbsBudgetEnum;
import com.leo.zu.request.BtCorpBudgetRequest;
import com.leo.zu.request.FbsBudgetControlRequest;
import com.leo.zu.request.FbsBudgetCorpRequest;
import com.leo.zu.response.BtCorpBudgetResponse;
import com.leo.zu.service.BtCorpService;
import com.leo.zu.service.FbsBudgetControlService;
import com.leo.zu.service.FbsBudgetCorpService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author leo-zu
 * @create 2021-01-31 9:19
 */
@RestController
@Slf4j
public class BudgetController {
    /**
     * 预算参数服务
     */
    @Resource
    private FbsBudgetControlService fbsBudgetControlService;
    /**
     * 单位服务
     */
    @Resource
    private BtCorpService btCorpService;

    /**
     * 预算单位服务
     */
    @Resource
    private FbsBudgetCorpService fbsBudgetCorpService;

    /**
     * 获取集团下预算单位/部门/科室列表(树)
     * @param request
     * @return
     */
    @PostMapping("/getBudgetCorpTree")
    @ApiOperation(value = "获取集团下单位部门信息",httpMethod = "POST")
    public CommonResult getBudgetCorpTree(@RequestBody BtCorpBudgetRequest request){
        log.info("收到前端传来的客户号imCustNo："+request.getImCustNo());

        BtCorpBudgetResponse bcbr  = fbsBudgetCorpService.getBudgetCorpTree(request);
        bcbr.setImCustNo(request.getImCustNo());

        log.info("**** 开始登录 ****" + bcbr.toString());
        if(bcbr != null){
            return new CommonResult(200,"查询成功",bcbr);
        }else{
            return new CommonResult(400,"查询失败",null);
        }
    }


    @PostMapping("/saveBudgetCorpInfo")
    @ApiOperation(value = "保存集团下单位部门信息",httpMethod = "POST")
    public CommonResult saveBudgetCorpInfo(@RequestBody FbsBudgetCorpRequest request){
        if (request != null){
            log.info("收到前端传来的需上报预算的部门信息："+request.toString());
            boolean flag = fbsBudgetCorpService.saveFbsBudgetCorpInfo(request);
            if(flag){
                return new CommonResult(200,"保存成功",null);
            }
            return new CommonResult(400,"保存失败",null);
        } else {
            return new CommonResult(402,"信息为空，请选中后再提交",null);
        }
    }

    @PostMapping("/saveBudgetControlParam")
    @ApiOperation(value = "保存预算参数",httpMethod = "POST")
    public CommonResult saveBudgetControlParam(@RequestBody FbsBudgetControlRequest request){
        if (request != null) {
            log.info("收到前端传来的预算参数信息："+request.toString());
            boolean flag = fbsBudgetControlService.saveBudgetControlParam(request);
            if(flag){
                return new CommonResult(200,"保存成功",null);
            }
            return new CommonResult(400,"保存失败",null);
        } else {
            return new CommonResult(402,"信息为空，请选中后再提交",null);
        }
    }
}
