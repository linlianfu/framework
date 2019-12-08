package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.graphql.GraphQLProvider;
import graphql.ExecutionResult;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLInputObjectField;
import graphql.schema.GraphQLInputObjectType;
import graphql.schema.GraphQLInputType;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLScalarType;
import graphql.schema.GraphQLType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
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
     * 127.0.0.1:8080/web/admin/platform/graphql?query={userInfo(id:"111111111"){id,name,age}userInfoList(arg:{pageNo:1111,pageSize:4,userName:"eleven"}){userId,name,identity,sex,phone,region,unitName}}
     *
     * 后面的graphql为：
     * query{
     *     userInfo(id:"2222222"){
     *         id,
     *         name,
     *         age
     *     }
     *     userInfoList(arg:{pageNo:1111,pageSize:4,userName:"eleven"}){
     *         userId,
     *         name,
     *         identity,
     *         sex,
     *         phone,
     *         region,
     *         unitName
     *     }
     * }
     * @param query
     * @return
     */
    @GetMapping()
    public ExecutionResult graphql(String query){

        return _provider.getGraphQL().execute(query);

    }

    /**
     * 获取描述文档
     *
     * url：127.0.0.1:8080/web/admin/platform/graphql/desc
     * result:{userInfo(id:String//用户id){id,name,age}userInfoList(arg:{pageNo:Int,//分页参数pageSize:Int,//分页参数userName:String,//用户名称}){userId,name,identity,sex,phone,region,unitName}}
     *
     * @return
     */
    @GetMapping("desc")
    public String desc(){
        StringBuilder builder = new StringBuilder("{");
        GraphQLObjectType queryType = _provider.get_schema().getQueryType();
        List<GraphQLFieldDefinition> queryFieldDefinitionList = queryType.getFieldDefinitions();
        for (GraphQLFieldDefinition item : queryFieldDefinitionList) {
            builder.append(item.getName());
            appendArgument(builder,item);
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

    /**
     * 参数拼接
     * @param builder
     * @param fieldDefinition
     */
    private void appendArgument(StringBuilder builder,GraphQLFieldDefinition fieldDefinition){
        List<GraphQLArgument> arguments = fieldDefinition.getArguments();
        if (CollectionUtils.isNotEmpty(arguments)){
            builder.append("(");
            for (GraphQLArgument argument : arguments) {
                GraphQLInputType type = argument.getType();
                String name = argument.getName();
                builder.append(name);
                if (type instanceof  GraphQLScalarType){
                    //基础数据类型
                    GraphQLScalarType basicType = (GraphQLScalarType) type;
                    builder.append(":").append(basicType.getName()).append("//").append(argument.getDescription());
                }else if (type instanceof GraphQLInputObjectType){
                    //对象类型
                    GraphQLInputObjectType objectType = (GraphQLInputObjectType) type;
                    builder.append(":{");
                    List<GraphQLInputObjectField> fieldDefinitions = objectType.getFieldDefinitions();
                    for (GraphQLInputObjectField definition : fieldDefinitions) {
                        String fieldName = definition.getName();
                        builder.append(fieldName)
                                .append(":")
                                .append(definition.getType().getName())
                                .append(",")
                                .append("//")
                                .append(definition.getDescription());
                    }
                    builder.append("}");
                }
            }
            builder.append(")");
        }
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
