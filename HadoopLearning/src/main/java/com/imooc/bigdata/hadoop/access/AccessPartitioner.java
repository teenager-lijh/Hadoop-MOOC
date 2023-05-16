package com.imooc.bigdata.hadoop.access;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author: blueberry
 * @date: 2023-04-26 21:19
 */

public class AccessPartitioner extends Partitioner<Text, Access> {
    @Override
    public int getPartition(Text phone, Access access, int numReduceTasks) {

        if(phone.toString().startsWith("13")) {
            return 0;
        } else if(phone.toString().startsWith("15")) {
            return 1;
        } else {
            return 2;
        }
    }
}
