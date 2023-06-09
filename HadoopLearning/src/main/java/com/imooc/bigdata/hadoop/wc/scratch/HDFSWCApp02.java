package com.imooc.bigdata.hadoop.wc.scratch;


import com.imooc.bigdata.hadoop.hdfs.Constants;
import com.imooc.bigdata.hadoop.hdfs.ParamsUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 *1) 读取 HDFS 上的文件 ==> HDFS API
 *2) 业务处理（词频统计）: 对文件中的每一行数据都要进行业务处理(按照分隔符分割)
 *3) 将处理结果缓存起来
 *4) 将结果输出到 HDFS ==> HDFS API
 */
public class HDFSWCApp02 {

    public static void main(String[] args) throws Exception{

        Properties properties = ParamsUtils.getProperties();


//        1) 读取 HDFS 上的文件 ==> HDFS API
        Path input = new Path(properties.getProperty(Constants.INPUT_PATH));

//        获取要操作的 HDFS 文件系统
        FileSystem fs = FileSystem.get(new URI(properties.getProperty(Constants.HDFS_URI)), new Configuration(), "hadoop");
        RemoteIterator<LocatedFileStatus> iterator= fs.listFiles(input, false);

        WordCountMapper mapper = new WordCountMapper();
        ImoocContext context = new ImoocContext();

        while(iterator.hasNext()) {
            LocatedFileStatus file = iterator.next();
            FSDataInputStream in = fs.open(file.getPath());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line = "";
            while((line = reader.readLine()) != null) {
//              2) 业务处理：相当于框架 Map 的过程
                // TODO... 在业务逻辑完成后将结果写到Cache中去
                mapper.map(line, context);
            }

            reader.close();
            in.close();
        }

//      3) 将处理结果缓存起来

        Map<Object, Object> contextMap = context.getCacheMap();


//      4) 将结果输出到 HDFS ==> HDFS API 相当于 Reduce 的过程
        Path output = new Path(properties.getProperty(Constants.OUTPUT_PATH));
        FSDataOutputStream out = fs.create(output);
        

        // TODO... 将第三步缓存中的内容输出到 out 中去
        Set<Map.Entry<Object, Object>> entries = contextMap.entrySet();
        for(Map.Entry<Object, Object> entry : entries) {
            out.write((entry.getKey().toString() + "\t" + entry.getValue() + "\n").getBytes());

        }

        out.close();
        fs.close();

        System.out.println("完成了词频统计 Lijh");

    }
}
