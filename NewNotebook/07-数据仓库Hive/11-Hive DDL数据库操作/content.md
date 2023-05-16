## Hive 数据抽象

Database	HDFS的一个目录
	table		HDFS的一个目录
		data（如果没有分区表，那就是存放在 data 文件）
				partition	分区表	HDFS的一个目录
					data（如果没有桶，那就是存放在 data 文件）
					bucket	分桶	HDFS的一个文件



创建数据库：

```
CREATE [REMOTE] (DATABASE|SCHEMA) [IF NOT EXISTS] database_name
  [COMMENT database_comment]
  [LOCATION hdfs_path]
  [MANAGEDLOCATION hdfs_path]
  [WITH DBPROPERTIES (property_name=property_value, ...)];
  

# 创建数据库 hive
CREATE DATABASE hive;

# hive 的默认HDFS存储路径
/user/hive/warehouse

# 当不存在数据库 hive 的时候才创建
CREATE DATABASE IF NOT EXISTS hive;

# 通过 LOCATION 指定创建在 HDFS 的那个路径上
CREATE DATABASE IF NOT EXISTS hive LOCATION '/test/path';

# 附加一些属性 ; 属性信息使用键值对给出，属性名可以任意给
CREATE DATABASE IF NOT EXISTS test_properties WITH DBPROPERTIES('creator'='blueberry');

# 查看数据库的信息
DESC DATABASE [database_name];

# 查看数据库详细的信息
DESC DATABASE EXTENDED [database_name];

# 取值，获取 key 的取值
set key; 

# 赋值，为 key 赋值为 value
set key=value;

# 获取 hive 的 客户端(cli) 的 print.current.db 的属性
# print.current.db : 打印当前所在的数据库
set hive.cli.print.current.db;

# 赋值 hive.cli.print.current.db
set hive.cli.print.current.db=true;

# 清空控制台
!clear;




```



删除数据库

```
DROP (DATABASE|SCHEMA) [IF EXISTS] database_name [RESTRICT|CASCADE];

# 删除数据库 db_name (数据库数据需要为空)
DROP DATABASE db_name;

# 删除数据库 db_name (可以有数据或表，不建议使用)
DROP DATABASE db_name CASCADE;
```



列出数据库

```
# 列出名字是以 hive 开头的数据库有哪些
SHOW DATABASES like 'hive*';
```

