package com.ichinait.service.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class PostController {
	public static final String URL = "http://localhost:8080/food_safety_web/";
	
	private RestTemplate restTemp = new RestTemplate();
	
	
	public void sendPost(String url,Object request,Map<String,Object> urlVariables ){
		restTemp.put(url, request, urlVariables);
		String result = restTemp.postForObject(url, request, String.class, urlVariables);
		System.out.println(result);
	}
	
	@Test
	public void query(){
		Map<String,Object> params = new HashMap<>();
		params.put("curror", "1");
		String url = URL+"user/query";
		sendPost(url, null, params);
		}

}
