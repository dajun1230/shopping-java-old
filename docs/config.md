# 项目搭建文档
## mysql配置
```bash
docker run -d --name shopping-data \
-p 3306:3306 \
-v /Users/xiaoyang/Documents//shopping-mall/shopping-data:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=123456 \
mysql:5.7

# 进入mysql容器 （容器名：shopping-data）
docker exec -it shopping-data bash

# 进入mysql (用户名：root 密码：123456)
mysql -uroot -p123456

# 退出
exit
```
### 表
```mysql
# 用户表
CREATE TABLE t_user (
	uid INT AUTO_INCREMENT COMMENT '用户ID',
	username VARCHAR(20) NOT NULL UNIQUE COMMENT '用户名',
	password CHAR(32) NOT NULL COMMENT '用户密码',
	salt CHAR(36) COMMENT '盐值',
	phone VARCHAR(20) COMMENT '电话号码',
	email VARCHAR(30) COMMENT '电子邮箱',
	gender INT COMMENT '性别：0-女，1-男',
	avatar VARCHAR(50) COMMENT '头像',
	is_delete INT COMMENT '是否删除：0-未删除，1-已删除',
	created_user VARCHAR(20) COMMENT '日志-创建人',
	created_time DATETIME COMMENT '日志-创建时间',
	modified_user VARCHAR(20) COMMENT '日志-最后修改执行人',
	modified_time DATETIME COMMENT '日志-最后修改时间',
	PRIMARY KEY (uid)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

# 地址表
CREATE TABLE t_address (
   aid INT AUTO_INCREMENT COMMENT '收货地址ID',
   uid INT COMMENT '归属的用户ID',
   name VARCHAR(20) COMMENT '收货人姓名',
   province_name CHAR(15) COMMENT '省_名称',
   province_code VARCHAR(6) COMMENT '省_行政代号',
   city_name CHAR(15) COMMENT '市_名称',
   city_code VARCHAR(6) COMMENT '市_行政代号',
   area_name CHAR(15) COMMENT '区_名称',
   area_code VARCHAR(6) COMMENT '区_行政代号',
   gender INT COMMENT '性别：0-女，1-男',
   zip CHAR(6) COMMENT '邮政编码',
   address VARCHAR(50) COMMENT '详细地址',
   phone VARCHAR(20) COMMENT '手机',
   tel VARCHAR(20) COMMENT '固定电话',
   tag VARCHAR(6) COMMENT '标签',
   is_default INT COMMENT '是否默认：0-不默认，1-默认',
   created_user VARCHAR(20) COMMENT '创建人',
   created_time DATETIME COMMENT '创建时间',
   modified_user VARCHAR(20) COMMENT '修改人',
   modified_time DATETIME COMMENT '修改时间',
   PRIMARY KEY (aid)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
```
## yaml配置
```yaml
spring:
  datasource:
    url: jdbc:mysql://47.108.167.244:3306/shopping-data?useUnicode=true&characterEncoding=UTF-8
    username: root
    password: 123456
    driver-class-name: com.mysql.jdbc.Driver
```