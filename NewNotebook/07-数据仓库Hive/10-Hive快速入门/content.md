创建数据库

```
create database test_db;
use test_db;
```



创建表，行元素之间使用 \t 进行分割

```
create table helloworld(id int, name string) ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t';
```



helloworld 文件的内容

```
1	lijh
2	wangzijing
3	zhangchun
```



加载本地文件的数据到 helloworld 表中

```
load data local inpath '<path>' overwrite into table helloworld;
```



使用一条 SQL 语句就可以启动一个 map-reduce 作业

```
select count(1) from helloworld;
```









