package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.graphql.GraphQLProvider;
import graphql.ExecutionResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author eleven
 * @date 2019/12/6
 * @description
 */

@RestController
@RequestMapping("/platform/graphql")
public class GraphQLAction {


    @Resource(name = "platformGraphQLProvider")
    GraphQLProvider _provider;


    /**
     * 请求路径格式位：127.0.0.1:8080/web/admin/platform/graphql?query=query{userInfoPage{name}}
     *
     * 后面的graphql位：
     * query{
     *     userInfoPage{
     *         name
     *     }
     * }
     * @param query
     * @return
     */
    @GetMapping()
    public ExecutionResult graphql(String query){

        return _provider.getGraphQL().execute(query);

    }
}
