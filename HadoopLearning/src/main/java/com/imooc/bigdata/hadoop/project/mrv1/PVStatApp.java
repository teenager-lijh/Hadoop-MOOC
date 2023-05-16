package com.imooc.bigdata.hadoop.project.mrv1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import javax.management.ObjectName;
import java.io.IOException;

/**
 * 第一个版本 - 浏览量的统计 （需求 1）
 *
 * @author: blueberry
 * @date: 2023-05-04 10:40
 *
 */

public class PVStatApp {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        /**
         * 1. 申请 Job 实例
         * 2. 设置 Driver 的 Class
         * 3. 设置 Mapper 和 Reducer Class
         * 4. 设置 Mapper && Reducer 的 Output Key && Value
         * 5. 设置 Input && Output Path
         * 6. 提交 Job ==> arg:verbose = true
         */

        // 1. 申请 Job 实例
        Job job = Job.getInstance(new Configuration());

        // 2. 设置 Mapper 和 Reducer Class
        job.setJarByClass(PVStatApp.class);

        // 3. 设置 Mapper 和 Reducer Class
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);

        // 4. 设置 Mapper && Reducer 的 Output Key && Value
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        //5. 设置 Input && Output Path
        FileInputFormat.setInputPaths(job, new Path("access/input/raw"));
        FileOutputFormat.setOutputPath(job, new Path("output/v1/PVStat"));

        job.waitForCompletion(true);

    }

    static class MyMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

        private Text KEY = new Text("key");
        private LongWritable ONE = new LongWritable(1);

        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, LongWritable>.Context context) throws IOException, InterruptedException {
            context.write(KEY, ONE);
        }
    }

    static class MyReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
        @Override
        protected void reduce(Text key, Iterable<LongWritable> values, Reducer<Text, LongWritable, Text, LongWritable>.Context context) throws IOException, InterruptedException {

            int count = 0;

            for(LongWritable v : values) {
                ++ count;
            }

            context.write(new Text("key"), new LongWritable(count));

        }
    }
}
