package com.ihere.voyage.service;

import com.ihere.voyage.entity.ESUserInfo;

import java.util.List;

/**
 * @author: fengshibo
 * @date: 2018/9/30 10:03
 * @description:
 */
public interface ESSerrvice {

    ESUserInfo addUserInfo(ESUserInfo userInfo);

    Integer addUserInfoBatch(List<ESUserInfo> userInfos);

    Integer delUSerInfo(Long id);

    ESUserInfo findUserInfoById(Long id);

    List<ESUserInfo> findUserInfoByWord(String word);
}
