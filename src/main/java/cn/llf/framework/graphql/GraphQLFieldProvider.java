package cn.llf.framework.graphql;

import graphql.schema.GraphQLFieldDefinition;

import java.util.List;

/**
 * @author eleven
 * @date 2019/12/6
 * @description
 */
public interface GraphQLFieldProvider {


    /**
     * 需要返回的字段定义
     * @return
     */
    List<GraphQLFieldDefinition> listGraphQLFieldDefinition();
}
