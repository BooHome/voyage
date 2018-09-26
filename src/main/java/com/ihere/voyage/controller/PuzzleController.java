package com.ihere.voyage.controller;

import com.ihere.voyage.service.PuzzleService;
import com.ihere.voyage.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Random;

/**
 * @author: fengshibo
 * @date: 2018/9/26 18:00
 * @description:
 */
@Controller
@RequestMapping("puzzle")
public class PuzzleController {

    @Autowired
    private PuzzleService puzzleService;

    @GetMapping("solve")
    @ResponseBody
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

    @GetMapping("init")
    @ResponseBody
    public InitResult getInitRandomArr(String arr) {
        InitResult initResult = new InitResult();
        int[][] ints = NumberUtil.initRandomArr();
        int status = 0;
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints.length; j++) {
                status = status * 10 + ints[i][j];
            }
        }
        initResult.setInts(ints);
        initResult.setIntsStr(status+"");
        return initResult;
    }

    private class InitResult {
        private int[][] ints;
        private String intsStr;

        public int[][] getInts() {
            return ints;
        }

        public void setInts(int[][] ints) {
            this.ints = ints;
        }

        public String getIntsStr() {
            return intsStr;
        }

        public void setIntsStr(String intsStr) {
            this.intsStr = intsStr;
        }
    }
}
