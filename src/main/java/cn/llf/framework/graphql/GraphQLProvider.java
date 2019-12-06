package cn.llf.framework.graphql;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import lombok.Data;

/**
 * @author eleven
 * @date 2019/12/6
 * @description
 */
@Data
public class GraphQLProvider {


    private GraphQLSchema _schema;



    private GraphQL graphQL;


    public GraphQL getGraphQL(){
        return graphQL = GraphQL.newGraphQL(_schema).build();
    }


}
