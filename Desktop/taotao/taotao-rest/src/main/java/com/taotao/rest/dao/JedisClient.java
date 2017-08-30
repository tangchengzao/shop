package com.taotao.rest.dao;

public interface JedisClient {
	
	//通过key获得value
	public String get(String key);
	
	//添加key-value
	public String set(String key, String value);
	
	//添加记录
	public String hget(String hkey, String key);
	
	//查询记录
	public long hset(String hkey, String key, String value);
	
	//key对应的value值加
	public long incr(String key);
	
	//设置存活时间
	public long expire(String key, int second);
	
	//查询剩余存活时间
	public long ttl(String key);
	
	//删除记录
	public long del(String key);
	
	//删除记录
	public long hdel(String hkey, String key);
	
}
