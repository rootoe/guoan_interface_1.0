package com.guoan.guoan_interface_15.redis;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.guoan.service.redis.RedisService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/spring.xml", "classpath:conf/spring-mvc.xml","classpath:conf/spring-redis.xml"})
public class RedisTest extends RedisService{
	
	@Test
	public void testLoginSuccess(){
		
	}

}
