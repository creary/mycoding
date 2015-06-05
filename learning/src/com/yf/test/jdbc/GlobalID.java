package com.yf.test.jdbc;

public class GlobalID {
	/**
	 * 生成全局ID的方法：
	 *  UUID：
优：java自带，好用。
劣：占用空间大
Snowflake： timestamp + work number + seq number
优：可用性强，速度快
劣：需要引入zookeeper 和独立的snowflake专用服务器
Flikr：基于int/bigint的自增
优：开发成本低
劣：如果需要高性能，需要专门一套MySQL集群只用于生成自增ID。可用性也不强
Instagram：41b ts + 13b shard id + 10b increment seq
优： 开发成本低
劣： 基于postgreSQL的存储过程，通用性差
UUID变种：timestamp + machine number + random (具体见：变种介绍
优： 开发成本低
劣： 基于MySQL的存储过程，性能较差
	 * 
	 * 
	 */
	

}
