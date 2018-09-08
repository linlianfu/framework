package cn.llf.framework.gateway.web.admin;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHits;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建者：   linlf
 * 创建时间： 2017/8/25
 * 描述：
 */
@Slf4j
@RestController
@RequestMapping("elasticsearch")
public class ElasticsearchAction {

//    @Autowired
    Client client;
    @GetMapping("listNews")
    public void listNews(){
        if (client == null){
            log.info("client初始化失败");
        }else {
            log.info(">>>>>client:{}",client.toString());
           SearchRequestBuilder responseBuilder  =  client.prepareSearch("calvin").setTypes("news_notice");
           SearchResponse response =  responseBuilder .execute().actionGet();

            SearchHits hits = response.getHits();
            for (int i = 0; i < hits.getHits().length;i++){
                String result = hits.getHits()[i].getSourceAsString();
                log.info(">>>>>查询结果"+result);
            }
        }
    }
}
