package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.graphql.GraphQLProvider;
import graphql.ExecutionResult;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author eleven
 * @date 2019/12/6
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/platform/graphql")
public class GraphQLAction {


    @Resource(name = "platformGraphQLProvider")
    GraphQLProvider _provider;


    /**
     * 请求路径格式位：
     * 127.0.0.1:8080/web/admin/platform/graphql?query={userInfo(id:"2222222"){id,name,age}userInfoList(arg:{pageSize:5}){userId,name,identity}}
     *
     * 后面的graphql为：
     * query{
     *     userInfo(id:"2222222"){
     *         id,
     *         name,
     *         age
     *     }
     *     userInfoList(arg:{pageSize:5}){
     *         userId,
     *         name,
     *         identity
     *     }
     * }
     * @param query
     * @return
     */
    @GetMapping()
    public ExecutionResult graphql(String query){

        return _provider.getGraphQL().execute(query);

    }

    @GetMapping("desc")
    public String desc(){
        StringBuilder builder = new StringBuilder("{");
        GraphQLObjectType queryType = _provider.get_schema().getQueryType();
        List<GraphQLFieldDefinition> queryFieldDefinitionList = queryType.getFieldDefinitions();
        for (GraphQLFieldDefinition item : queryFieldDefinitionList) {
            builder.append(item.getName()).append("");
            GraphQLOutputType type = item.getType();
            if (type instanceof  GraphQLObjectType){
                appendGraphQLObjectType(builder, (GraphQLObjectType) type);
            }else if(type instanceof GraphQLList){
                GraphQLList listType = (GraphQLList)type;
                List<GraphQLType> childrenList = listType.getChildren();
                for (GraphQLType children : childrenList) {
                    if (children instanceof  GraphQLObjectType){
                        appendGraphQLObjectType(builder, (GraphQLObjectType) children);
                    }
                }
            }else {
                log.warn(">> 未适配的outputObjectType:{}",type.getClass().getName());
            }

        }

        builder.append("}");
        return builder.toString();
    }


    private void appendGraphQLObjectType(StringBuilder builder,GraphQLObjectType objectType){
        builder.append("{");
        List<GraphQLFieldDefinition> outputFieldDefinitionsList = objectType.getFieldDefinitions();
        for (GraphQLFieldDefinition outputFieldDefinition : outputFieldDefinitionsList) {
            builder.append(outputFieldDefinition.getName()).append(",");
        }
        builder.delete(builder.length() - 1,builder.length());
        builder.append("}");
    }
}
