## HDFS 配置

配置 DataNode 的相关内容 && 配置 NameNode 的相关内容

1. core-site.xml: etc/hadoop/core-site.xml

   `fs.defaultFS` 配置 hadoop hdfs 文件系统所在的节点（主节点），这里存储了一个大文件分布在哪些节点的信息

```xml
<configuration>
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://localhost:8020</value>
    </property>
</configuration>
```



2. hdfs-site.xml: etc/hadoop/hdfs-site.xml
   `dfs.replication` 配置副本系数，一个文件需要被冗余备份 几份
   `hadoop.tmp.dir` 配置 hadoop hdfs 产生的临时文件的存放目录
   `dfs.namenode.secondary.http-address` 配置 `secondary name node` 的启动位置

```xml
<configuration>
    <property>
        <name>dfs.replication</name>
        <value>1</value>
    </property>
    
    <!-- 这段应该配置在 core-site.xml 中， 
		 不然在运行 $HADOOP_HOME/share/hadoop/mapreduce/pi example 
		 的时候会出现 File Do Not Found 的 error
    <property>
        <name>hadoop.tmp.dir</name>
        <value>一个具体的路径</value>
    </property>
    -->
    
    <property>
        <name>dfs.namenode.secondary.http-address</name>
        <value>hadoop000:50070</value>
    </property>

</configuration>
```



3. salves: etc/hadoop/salves
   配置从节点的 ip 地址 或 域名



4. masters: etc/hadoop/salves
   配置主节点的 ip 地址 或 域名 （不确定这个配置文件是不是起到了效果，CSDN 看到的）



## 格式化 HDFS

1. 第一次启动 HDFS 的时候，格式化文件系统
   `hdfs namenode -format`   （format the DFS filesystem）



## 启动 HDFS

1. sbin/start-dfs.sh
2. jps 查看 Java 虚拟机运行的进程



## 停止 HDFS

sbin/stop-dfs.sh