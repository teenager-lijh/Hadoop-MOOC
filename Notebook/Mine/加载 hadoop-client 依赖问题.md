# 课程使用的 cdh 版本

    课程使用的 cdh 版本是 2.6.0

# hadoop-client 依赖的仓库

    <repositories>
        <repository>
        <id>cloudera</id>
        <url>https://repository.cloudera.com/artifactory/cloudera-repos</url>
        </repository>
    </repositories>


# 使用 cdh 版本的 hadoop-client 不能解决时，直接使用 apache 的 hadoop-client

    Note: 
        1 删除 setting.xml 中的所有镜像配置
        2 删除 pom.xml 中的 仓库（repositories） 配置
        3 使用下边的 依赖配置
    
    <dependency>
      <groupId>org.apache.hadoop</groupId>
      <artifactId>hadoop-client</artifactId>
      <version>2.7.1</version>
    </dependency>


# IDEA 代理配置

    在 IDEA 的 settings 中搜索 proxy 设置自动代理
    
    参考网址：
    https://jingyan.baidu.com/article/b24f6c82732d6486bfe5da17.html
    
    配置截图：

    使用 check connection 输入一个外网网址可以检测当前的代理是否成功，比如输入：
    
    http://www.google.com