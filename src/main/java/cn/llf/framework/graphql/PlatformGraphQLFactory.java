package cn.llf.framework.graphql;

import cn.llf.framework.services.user.dto.GraphQLUserForm;
import cn.llf.framework.services.user.south.IUserManagerService;
import com.alibaba.fastjson.JSON;
import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLInputObjectField;
import graphql.schema.GraphQLInputObjectType;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
        return Arrays.asList(definitionUserInfo(),definitionGraphQLListUserInfo());
    }


    private GraphQLFieldDefinition definitionUserInfo(){
        return GraphQLFieldDefinition.newFieldDefinition().name("userInfo")
                .type(
                        GraphQLObjectType.newObject().name("UserInfoPO")
                                .field(GraphQLFieldDefinition.newFieldDefinition().name("id").type(Scalars.GraphQLString))
                                .field(GraphQLFieldDefinition.newFieldDefinition().name("name").type(Scalars.GraphQLString))
                                .field(GraphQLFieldDefinition.newFieldDefinition().name("age").type(Scalars.GraphQLInt))
                )
                .argument(GraphQLArgument.newArgument().name("id").type(Scalars.GraphQLString).description("用户id"))
                .description("根据用户id获取用户详情")
                .dataFetcher(environment ->  userManagerService.memory(environment.getArgument("id")))
                .build();
    }


    private GraphQLFieldDefinition definitionGraphQLListUserInfo(){
        GraphQLObjectType userInfoOutputObjectTypeList = GraphQLObjectType.newObject().name("UserInfo")
                .field(GraphQLFieldDefinition.newFieldDefinition().name("userId").type(Scalars.GraphQLString))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("name").type(Scalars.GraphQLString).description("用户名"))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("identity").type(Scalars.GraphQLString))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("sex").type(Scalars.GraphQLInt))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("phone").type(Scalars.GraphQLString))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("region").type(Scalars.GraphQLString))
                .field(GraphQLFieldDefinition.newFieldDefinition().name("unitName").type(Scalars.GraphQLString))
                .build();

        GraphQLInputObjectType.Builder userFormInputObjectBuilder = GraphQLInputObjectType.newInputObject().name("GraphQLUserForm")
                .field(GraphQLInputObjectField.newInputObjectField().name("pageNo").type(Scalars.GraphQLInt).description("分页参数").build())
                .field(GraphQLInputObjectField.newInputObjectField().name("pageSize").type(Scalars.GraphQLInt).description("分页参数").build())
                .field(GraphQLInputObjectField.newInputObjectField().name("userName").type(Scalars.GraphQLString).description("用户名称").build());

        GraphQLArgument.Builder userFormArgBuilder = GraphQLArgument.newArgument().name("arg")
                .type(userFormInputObjectBuilder.build());

        return GraphQLFieldDefinition.newFieldDefinition().name("userInfoList")
                .type(new GraphQLList(userInfoOutputObjectTypeList))
                .argument(userFormArgBuilder)
                .description("模拟graphQL返回集合数据")
                .dataFetcher(environment -> {
                    GraphQLUserForm form = JSON.parseObject(JSON.toJSONString(environment.getArgument("arg")),GraphQLUserForm.class);
                   return userManagerService.graphQLListUserInfo(form);
                })
                .build();
    }
}
