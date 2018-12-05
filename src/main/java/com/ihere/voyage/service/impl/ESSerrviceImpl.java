package com.ihere.voyage.service.impl;

import com.ihere.voyage.dao.ESUserDao;
import com.ihere.voyage.entity.ESUserInfo;
import com.ihere.voyage.service.ESSerrvice;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.highlight.HighlightField;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author: fengshibo
 * @date: 2018/9/30 10:05
 * @description:
 */
@Service
public class ESSerrviceImpl implements ESSerrvice {
    @Autowired
    private ESUserDao esUserDao;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public ESUserInfo addUserInfo(ESUserInfo userInfo) {
        ESUserInfo save = esUserDao.save(userInfo);
        return save;
    }

    @Override
    public Integer addUserInfoBatch(List<ESUserInfo> userInfos) {
        List<IndexQuery> indexQueries=new ArrayList<>();
        IndexQuery indexQuery=null;
        for (ESUserInfo esUserInfo :
                userInfos) {
            indexQuery=new IndexQuery();
            indexQuery.setObject(esUserInfo);
            indexQueries.add(indexQuery);
            //esUserDao.save(esUserInfo);
        }
        elasticsearchTemplate.bulkIndex(indexQueries);
        return userInfos==null?0:userInfos.size();
    }

    @Override
    public Integer delUSerInfo(Long id) {
        esUserDao.delete(id);
        return null;
    }

    @Override
    public ESUserInfo findUserInfoById(Long id) {
        ESUserInfo one = esUserDao.findOne(id);
        return one;
    }

    @Override
    public List<ESUserInfo> findUserInfoByWord(String word) {
        String[] fileds = {"name", "description"};
        HighlightBuilder.Field field = new HighlightBuilder.Field("*").preTags("==").postTags("==").fragmentOffset(20).matchedFields(fileds);
        Pageable pageable = new PageRequest(0, 10,new Sort(Sort.Direction.DESC,"_score"));
        QueryBuilder matchQuery = QueryBuilders.multiMatchQuery(word, fileds).analyzer("ik_max_word");
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(matchQuery).withHighlightFields(field).build();
        Page<ESUserInfo> esUserInfos = elasticsearchTemplate.queryForPage(searchQuery, ESUserInfo.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                List<ESUserInfo> chunk = new ArrayList<>();
                for (SearchHit searchHit : searchResponse.getHits()) {
                    if (searchResponse.getHits().getHits().length <= 0) {
                        return null;
                    }
                    ESUserInfo user = new ESUserInfo();
                    user.setId(Long.valueOf(searchHit.getId()));
                    //name or description
                    HighlightField name = searchHit.getHighlightFields().get("name");
                    if (name != null) {
                        user.setName(name.fragments()[0].toString());
                    }
                    HighlightField memo = searchHit.getHighlightFields().get("description");
                    if (memo != null) {
                        user.setDescription(memo.fragments()[0].toString());
                    }
                    chunk.add(user);
                }
                if (chunk.size() > 0) {
                    return new AggregatedPageImpl(chunk);
                }
                return null;
            }
        });
        return esUserInfos.getContent();
    }
}
