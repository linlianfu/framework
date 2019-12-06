package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.gateway.web.admin.dto.User;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

/**
 * @author eleven
 * @date 2019/12/1
 * @description
 */
@Component
public class UserQuery implements GraphQLQueryResolver {


    public User getUserById(String id){
        User user = new User();
        user.setId(UUID.randomUUID().toString().replaceAll("-",""));
        user.setUsername("eleven");
        user.setAge(20);
        user.setPassword("123456");
        return user;
    }

    public GraphQLSchema schema(){
        String schema = "type Query {hello: String}";

        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schema);

        RuntimeWiring runtimeWiring = newRuntimeWiring()
                .type("Query", builder -> builder.dataFetcher("hello", new StaticDataFetcher("Xiangbin")))
                .build();

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
    }
}
