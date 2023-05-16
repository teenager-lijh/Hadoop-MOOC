package com.imooc.bigdata.project.utils;

import com.imooc.bigdata.hadoop.project.utils.IPParser;
import org.junit.Test;

/**
 * @author: blueberry
 * @date: 2023-05-04 19:17
 */

public class IPTest {

    @Test
    public void TestIP() {
        IPParser.RegionInfo regionInfo = IPParser.getInstance().analyseIp("123.116.10.97");

        System.out.println(regionInfo.getCountry());

    }

}
