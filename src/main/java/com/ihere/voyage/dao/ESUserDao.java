package com.ihere.voyage.dao;

import com.ihere.voyage.entity.ESUserInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;


/**
 * @author: fengshibo
 * @date: 2018/9/30 09:57
 * @description:
 */
@Component
public interface ESUserDao  extends ElasticsearchRepository<ESUserInfo, Long> {

}
