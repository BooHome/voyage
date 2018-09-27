package com.ihere.voyage.controller;

import com.ihere.voyage.pojo.bo.InitResult;
import com.ihere.voyage.service.PuzzleService;
import com.ihere.voyage.util.NumberUtil;
import com.ihere.voyage.util.file.ImageCutUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author: fengshibo
 * @date: 2018/9/26 18:00
 * @description:
 */
@Controller
@RequestMapping("puzzle")
@Api(tags = "Puzzle数据接口")
public class PuzzleController {

    @Autowired
    private PuzzleService puzzleService;

    @GetMapping(value = "solve", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取路径", notes = "获取路径")
    @ResponseBody
    public List<Integer> getRoute(String arrStr) {
        return puzzleService.getRoute(arrStr);
    }

    @GetMapping(value = "init/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取初始化数据", notes = "获取初始化数据")
    @ResponseBody
    public InitResult getInitRandomArr(@PathVariable Integer userId) {
        return puzzleService.getInitRandomArr(userId);
    }

    @PostMapping(value = "initFile/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "上传文件并获取初始化数据", notes = "上传文件并获取初始化数据")
    @ResponseBody
    public InitResult setFile(@ApiParam(required = false, value = "原始图像") MultipartFile file, @PathVariable Integer userId) throws IOException {
        InitResult initResult = puzzleService.getInitRandomArr(userId);
        String fileName = ImageCutUtil.splitImage(file.getInputStream());
        initResult.setFileName(fileName);
        return initResult;
    }

    @GetMapping("main")
    public String toPuzzle() {
        return "puzzle";
    }
}
