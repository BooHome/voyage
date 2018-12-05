package com.ihere.voyage.controller;

import com.ihere.voyage.entity.ESUserInfo;
import com.ihere.voyage.service.ESSerrvice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: fengshibo
 * @date: 2018/9/30 10:21
 * @description:
 */
@Controller
@RequestMapping("es")
@Api(tags = "elasticsearch数据接口")
public class ESController {

    @Autowired
    private ESSerrvice esSerrvic;

    @GetMapping(value = "search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "搜索", notes = "搜索")
    @ResponseBody
    public List<ESUserInfo> getRoute(String search) {
        long startedTime = System.currentTimeMillis();
        List<ESUserInfo> userInfoByWord = esSerrvic.findUserInfoByWord(search);
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间："+(endTime-startedTime)+"毫秒");
        return userInfoByWord;
    }

    @PostMapping(value = "add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "新增", notes = "新增")
    @ResponseBody
    public ESUserInfo add(ESUserInfo add) {
        return esSerrvic.addUserInfo(add);
    }

    @PostMapping(value = "del", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "删除", notes = "删除")
    @ResponseBody
    public void del(Long del) {
        esSerrvic.delUSerInfo(del);
    }

    @GetMapping(value = "find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据ID查询", notes = "根据ID查询")
    @ResponseBody
    public ESUserInfo find(@PathVariable Long id) {
        ESUserInfo userInfoById = esSerrvic.findUserInfoById(id);
        return userInfoById;
    }

    @PostMapping(value = "adds", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "批量新增", notes = "批量新增")
    @ResponseBody
    public Integer adds() {
        List<ESUserInfo> esUserInfos=new ArrayList<>();
        ESUserInfo esUserInfo=null;
        long num=1;
        File file=new File("C:\\Users\\亲亲小保\\Desktop\\任务\\斗罗大陆.txt");//创建文件对象
        String encoding="GBK";//设置读取文件的编码格式
        if(file.isFile()&&file.exists()){//判断文件是否存在
            try {
                FileInputStream fisr=new FileInputStream(file);
                //FileInputStream创建文件输入流,FileInputStream类是以字节读取文件的
                InputStreamReader isr=new InputStreamReader(fisr,encoding);//封装文件输入流，并设置编码方式
                //InputStreamReader是字节流转向字符流的桥梁，读取文本文件当然可以用字节流，但是使用字符流会更加的方便
                /*如果处理的是文本文件的话，下面两条语句几乎相同
                 * InputStreamReader in=new InputStreamReader(new FileInputStream(file));
                 * FileReader fin=new FileReader(file);
                 */
                BufferedReader br=new BufferedReader(isr);
                //BufferedInputStream将InputStreamReader中的数据存入缓冲区,它不改变FileInputSteam中数据的类型
                //BufferedInputStream是将多个输入的数据放入一个缓冲区中以便一次性操作
                String txt=null;
                while((txt=br.readLine())!=null){//按行读取文件，每次读取一行
                    esUserInfo=new ESUserInfo();
                    esUserInfo.setId(num++);
                    esUserInfo.setAge(11);
                    esUserInfo.setCreatedDate(new Date());
                    esUserInfo.setName(txt);
                    esUserInfo.setDescription(txt);
                    esUserInfos.add(esUserInfo);
                }
                fisr.close();
                isr.close();
                br.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        long startedTime = System.currentTimeMillis();
        Integer integer = esSerrvic.addUserInfoBatch(esUserInfos);
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间："+(endTime-startedTime)+"毫秒");
        return integer;
    }


}
