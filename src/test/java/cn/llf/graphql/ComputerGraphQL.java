package cn.llf.graphql;

import cn.llf.graphql.dto.ComputerDTO;
import cn.llf.graphql.dto.CpuDTO;
import cn.llf.graphql.dto.MemoryDTO;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author eleven
 * @date 2019/1/22
 * @description
 *GraphQLQueryResolver
 *
 * http://www.zhaiqianfeng.com/2017/06/learn-graphql-action-by-java.html
 *
 * query查询及mutation动作：https://github.com/zhaiqianfeng/GraphQL-Demo/blob/master/java/src/main/java/com/zqf/advance/GraphQL_argument.java
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:common.xml")
public class ComputerGraphQL {

    @Test
    public void log(){
        log.info("1111");
    }


    @Test
    public void buildGraphQL(){
        CpuDTO cpu = new CpuDTO();
        cpu.setName("I7");
        cpu.setCache("19M");

        MemoryDTO m1 = new MemoryDTO();
        m1.setName("海盗船");
        m1.setSize("8G");

        MemoryDTO m2 = new MemoryDTO();
        m2.setName("海盗船");
        m2.setSize("8G");

        List<MemoryDTO> memoryList = new ArrayList<MemoryDTO>();
        memoryList.add(m1);
        memoryList.add(m2);

        ComputerDTO computer = new ComputerDTO();
        computer.setName("组装机");
        computer.setCpu(cpu);
        computer.setMemoryList(memoryList);

        // 定义GraphQL类型
        GraphQLObjectType cpuType = GraphQLObjectType.newObject().name("cpuType")
                .field(GraphQLFieldDefinition.newFieldDefinition().name("name").type(Scalars.GraphQLString).build())
                .field(GraphQLFieldDefinition.newFieldDefinition().name("cache").type(Scalars.GraphQLString).build())
                .build();

        GraphQLObjectType memoryType = GraphQLObjectType.newObject().name("memoryType")
                .field(GraphQLFieldDefinition.newFieldDefinition().name("name").type(Scalars.GraphQLString).build())
                .field(GraphQLFieldDefinition.newFieldDefinition().name("size").type(Scalars.GraphQLString).build())
                .build();

        GraphQLObjectType computerType = GraphQLObjectType.newObject().name("computerType")
                .field(GraphQLFieldDefinition.newFieldDefinition().name("name").type(Scalars.GraphQLString).build())
                .field(GraphQLFieldDefinition.newFieldDefinition().name("cpu").type(cpuType).build())
                .field(GraphQLFieldDefinition.newFieldDefinition().name("memoryList").type(new GraphQLList(memoryType)).build())
                .build();

        // 关联返回类型与返回数据
        GraphQLObjectType queryType = GraphQLObjectType.newObject().name("computerQuery")
                .field(GraphQLFieldDefinition.newFieldDefinition().type(computerType).name("computer").dataFetcher(evn -> computer).build())
                .build();

        GraphQLSchema schema = GraphQLSchema.newSchema().query(queryType).build();

        GraphQL graphQL = GraphQL.newGraphQL(schema).build();

        ExecutionResult execute = graphQL.execute("query{computer{name,cpu{name,cache}}}");
        Map<String, Object> dataMap = execute.getData();
        List<GraphQLError> errors = execute.getErrors();

        // 打印返回结果
        System.out.println("成功数据："+dataMap);
        System.out.println("失败数据："+errors);
    }


    @Test
    public void buildGraphQLQueryWithArgument(){
        CpuDTO cpuType1 = new CpuDTO();
        cpuType1.setId(UUID.randomUUID().toString());
        cpuType1.setName("I7");
        cpuType1.setCache("19M");
        cpuType1.setCpuType(1);
        CpuDTO cpuType2 = new CpuDTO();
        cpuType2.setName("I7");
        cpuType2.setCache("19M");
        cpuType2.setCpuType(2);
        CpuDTO cpuType3 = new CpuDTO();
        cpuType3.setName("I7");
        cpuType3.setCache("19M");
        cpuType3.setCpuType(3);

        GraphQLObjectType basicOutPutType = GraphQLObjectType.newObject().name("basicField")
                .field(GraphQLFieldDefinition.newFieldDefinition().name("id").type(Scalars.GraphQLString).build())
                .field(GraphQLFieldDefinition.newFieldDefinition().name("name").type(Scalars.GraphQLString).build())
                .field(GraphQLFieldDefinition.newFieldDefinition().name("cpuType").type(Scalars.GraphQLString).build())
                .field(GraphQLFieldDefinition.newFieldDefinition().name("cache").type(Scalars.GraphQLString).build()).build();

        GraphQLObjectType cpuDtoObjectType = GraphQLObjectType.newObject().name("getCpuDto")
                .field(GraphQLFieldDefinition.newFieldDefinition().name("cpuDto").type(basicOutPutType)
                        .argument(GraphQLArgument.newArgument().name("id").type(Scalars.GraphQLString).build())
                        .dataFetcher(environment -> {
                            String id = environment.getArgument("id");
                            log.info("参数id:{}",id);
                            return cpuType1;
                        })
                ).build();

        GraphQLSchema schema = GraphQLSchema.newSchema().query(cpuDtoObjectType).build();
        GraphQL graphQL = GraphQL.newGraphQL(schema).build();

        String schemeQuery = "{cpuDto(id:\"111\"){id,name,cache,cpuType}}";

        ExecutionResult execute = graphQL.execute(schemeQuery);
        Map<String,Object> map = execute.getData();
        List<GraphQLError> errors = execute.getErrors();
        log.info("success:{}",map);
        log.error("error:{}",errors);
    }

}
