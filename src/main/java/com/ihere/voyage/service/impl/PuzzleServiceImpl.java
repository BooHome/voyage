package com.ihere.voyage.service.impl;

import com.ihere.voyage.pojo.bo.InitResult;
import com.ihere.voyage.service.PuzzleService;
import com.ihere.voyage.util.NumberUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: fengshibo
 * @date: 2018/9/26 17:59
 * @description:
 */
@Service
public class PuzzleServiceImpl implements PuzzleService {
    @Override
    public List<Integer> getRoute(String arrStr) {
        String[] split = arrStr.split("");
        int[][] arr = new int[3][3];
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(split[k]);
                k += 1;
            }
        }
        return NumberUtil.getRoute(arr);
    }

    @Override
    public InitResult getInitRandomArr(Integer userId) {
        InitResult initResult = new InitResult();
        int[][] ints = NumberUtil.initRandomArr();
        int status = 0;
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints.length; j++) {
                status = status * 10 + ints[i][j];
            }
        }
        initResult.setInts(ints);
        String result = "";
        initResult.setIntsStr((result = (status + "").trim()).length() == 8 ? "0" + result : result);
        return initResult;
    }
}
