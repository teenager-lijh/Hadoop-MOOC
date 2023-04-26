package com.imooc.bigdata.hadoop.wc.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//        String[] words = value.toString().split("\t");
//
//        for(String word : words) {
//            context.write(new Text(word), new IntWritable(1));
//        }
//

        // 把value对应的行数据按照指定的分隔符拆开
        String[] words = value.toString().split("\t");


        for(String word : words) {
            // (hello,1)  (world,1)
            context.write(new Text(word.toLowerCase()), new IntWritable(1));
        }
    }
}
