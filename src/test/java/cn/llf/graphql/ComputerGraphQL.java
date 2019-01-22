package cn.llf.graphql;

import cn.llf.graphql.dto.ComputerDTO;
import cn.llf.graphql.dto.CpuDTO;
import cn.llf.graphql.dto.MemoryDTO;
import graphql.GraphQL;
import graphql.Scalars;
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

/**
 * @author eleven
 * @date 2019/1/22
 * @description
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

        Map<String, Object> result = graphQL.execute("{computer{name,cpu{name,cache},memoryList{name,size}}}")
                .getData();

        // 打印返回结果
        System.out.println(result);


    }

}
