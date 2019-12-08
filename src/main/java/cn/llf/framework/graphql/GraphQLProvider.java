package cn.llf.framework.graphql;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import lombok.Data;
import lombok.Getter;

/**
 * @author eleven
 * @date 2019/12/6
 * @description
 */
@Data
public class GraphQLProvider {


    @Getter
    private GraphQLSchema _schema;



    private GraphQL graphQL;


    public GraphQL getGraphQL(){
        return graphQL = GraphQL.newGraphQL(_schema).build();
    }


}
