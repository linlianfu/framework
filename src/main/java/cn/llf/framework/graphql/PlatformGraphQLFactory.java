package cn.llf.framework.graphql;

import cn.llf.framework.model.mybatis.UserInfoPO;
import cn.llf.framework.services.user.south.IUserManagerService;
import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author eleven
 * @date 2019/12/6
 * @description
 */
@Service
public class PlatformGraphQLFactory implements GraphQLFieldProvider {

    @Autowired
    protected IUserManagerService userManagerService;



    @Override
    public List<GraphQLFieldDefinition> listGraphQLFieldDefinition(){
        return Collections.singletonList(definitionUserInfo());
    }


    public GraphQLFieldDefinition definitionUserInfo(){
        UserInfoPO memory = userManagerService.memory();

        return GraphQLFieldDefinition.newFieldDefinition().name("userInfoPage")
                .type(
                        GraphQLObjectType.newObject().name("userInfoPo")
                        .field(GraphQLFieldDefinition.newFieldDefinition()
                                .name("name").type(Scalars.GraphQLString).build())
                        .field(GraphQLFieldDefinition.newFieldDefinition()
                                .name("age").type(Scalars.GraphQLInt).build())
                ).dataFetcher(environment ->  memory).build();

    }
}
