package cn.llf.framework.graphql;

import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author eleven
 * @date 2019/12/6
 * @description
 */
public class GraphQLProviderFactoryBean implements FactoryBean<GraphQLProvider>,InitializingBean{


    private GraphQLProvider _provider;

    @Autowired
    public GraphQLFieldProvider fieldProvider;


    @Override
    public void afterPropertiesSet() throws Exception {
        _provider = new GraphQLProvider();
        GraphQLObjectType.Builder builder = GraphQLObjectType.newObject().name("query");

        List<GraphQLFieldDefinition> graphQLFieldDefinitionList = fieldProvider.listGraphQLFieldDefinition();

        if (CollectionUtils.isNotEmpty(graphQLFieldDefinitionList)){
            builder.fields(graphQLFieldDefinitionList);
        }

//        RuntimeWiring.newRuntimeWiring().directive("only", new OnlySchemaDirectiveWiring());
        GraphQLSchema schema = GraphQLSchema.newSchema().query(builder).build();
        _provider.set_schema(schema);

    }

    @Override
    public GraphQLProvider getObject() throws Exception {
        return _provider;
    }


    @Override
    public Class<?> getObjectType() {
        return GraphQLProvider.class;
    }


    @Override
    public boolean isSingleton() {
        return true;
    }
}
