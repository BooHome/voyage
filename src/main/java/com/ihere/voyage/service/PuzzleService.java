package com.ihere.voyage.service;

import com.ihere.voyage.controller.PuzzleController;
import com.ihere.voyage.pojo.bo.InitResult;

import java.util.List;

/**
 * @author: fengshibo
 * @date: 2018/9/26 17:58
 * @description:
 */
public interface PuzzleService {
    /**
     * 获取路径
     * @param arrStr
     * @return
     */
    List<Integer> getRoute(String arrStr);

    /**
     * 获取初始化数据
     * @param userId
     * @return
     */
    InitResult getInitRandomArr(Integer userId);
}
