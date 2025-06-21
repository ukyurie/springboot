package org.example.accident;

import org.example.accident.Entity.Accident_Cause;
import org.example.accident.Entity.Cause;
import org.example.accident.Mapper.Accident_CauseMapper;
import org.example.accident.Mapper.CauseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;
import java.util.List;

@SpringBootTest
class MapperTest {

    @Qualifier("causeMapper")
    @Autowired
    private CauseMapper causeMapper;

    @Test
    void contextLoads() {
        Cause cause = new Cause();
        cause.setCauseBasicType("1");
        cause.setCauseDirectType("2");
        cause.setCauseIndirectType("3");
        cause.setCauseDescription("4");
        int row=causeMapper.insert(cause);
        System.out.println(row);
        System.out.println(cause);
    }

}
