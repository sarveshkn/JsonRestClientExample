package com.practise.client;

import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.practise.model.Education;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class LoanClient2 {

	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource("http://localhost:8080/LoanService/rest/person/getEducation");
		Education edu = new Education("HAL");
        edu.setSchool("HAL");
        JSONObject json = new JSONObject(edu);
        System.out.println(json);
		System.out.println(service.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(Education.class,
				json.toString()));

	}

}
