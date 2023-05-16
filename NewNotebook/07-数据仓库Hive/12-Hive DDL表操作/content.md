创建表

```
CREATE [TEMPORARY] [EXTERNAL] TABLE [IF NOT EXISTS] [db_name.]table_name
  [(col_name data_type [column_constraint_specification] [COMMENT col_comment], ... [constraint_specification])]
  [COMMENT table_comment]
  [PARTITIONED BY (col_name data_type [COMMENT col_comment], ...)]
  [CLUSTERED BY (col_name, col_name, ...) [SORTED BY (col_name [ASC|DESC], ...)] INTO num_buckets BUCKETS]
  [SKEWED BY (col_name, col_name, ...)
     ON ((col_value, col_value, ...), (col_value, col_value, ...), ...)
     [STORED AS DIRECTORIES]
  [
   [ROW FORMAT row_format] 
   [STORED AS file_format]
     | STORED BY 'storage.handler.class.name' [WITH SERDEPROPERTIES (...)]  -- (Note: Available in Hive 0.6.0 and later)
  ]
  [LOCATION hdfs_path]
  [TBLPROPERTIES (property_name=property_value, ...)]  
  [AS select_statement];
  
  
  
# 创建表 EXAMPLE
# FIELDS TERMINATED BY char 指定字段之间的分隔符
# ROW FORMAT 后面跟上要设置的格式
CREATE TABLE table_name (
	field_name field_type,
	···，
	field_name field_type
) ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t';

CREATE TABLE friends (
	name STRING,
	age INT,
	height INT
) ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t';

# 查看表结构
DESC table_name;

# 查看详细的表结构
DESC EXTENDED table_name;
DESC FORMATTED table_name;

# 加载数据
LOAD DATA LOCAL INPATH 'local_path' OVERWRITE INTO TABLE table_name;

# 修改表明
ALTER TABLE table_name RENAME TO new_table_name;
```

