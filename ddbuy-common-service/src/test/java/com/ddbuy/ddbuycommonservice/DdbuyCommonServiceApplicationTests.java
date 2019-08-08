package com.ddbuy.ddbuycommonservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import service.SolrSerivice;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DdbuyCommonServiceApplicationTests {
    @Autowired
    private SolrSerivice solrSerivice;
    @Test
    public void contextLoads() {
        System.out.println(solrSerivice.importIndex());
    }

}
