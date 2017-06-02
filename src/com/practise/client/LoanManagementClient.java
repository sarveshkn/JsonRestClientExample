package com.practise.client;

import java.io.IOException;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practise.model.Address;
import com.practise.model.Education;
import com.practise.model.Person;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class LoanManagementClient {
	
	public static void main(String[] args) {
		testPostJsonCall();
	}
	
	private static void testGetJsonCall() {
		Client client = Client.create();

        WebResource webResource = client.resource("http://localhost:8080/LoanService/rest/person/name/hi");

        String json = createPerson();

        ClientResponse response = webResource.accept("application/json")
           .get(ClientResponse.class);

        System.out.println("Output from Server .... \n");
        String output = response.getEntity(String.class);
        System.out.println(output);
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            Person person = mapper.readValue(output, Person.class);
            System.out.println("Person = " + person);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private static void testPostJsonCall() {
		Client client = Client.create();

        WebResource webResource = client.resource("http://localhost:8080/LoanService/rest/person/savePerson");

        String json = createPerson();
        System.out.println(json);

        ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, json);	

        System.out.println("Output from Server .... \n");
        String output = response.getEntity(String.class);
        System.out.println(output);
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            Person person = mapper.readValue(output, Person.class);
            System.out.println("Person = " + person);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	private static void testEducation() {
		Client client = Client.create();

        WebResource webResource = client.resource("http://localhost:8080/LoanService/rest/person/getEducation");

        Education edu = new Education("RLB");
        edu.setSchool("HAL");
        JSONObject json = new JSONObject(edu);
        System.out.println(json);

        ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, json.toString());	

        System.out.println("Output from Server .... \n");
        String output = response.getEntity(String.class);
        System.out.println(output);
        
        ObjectMapper mapper = new ObjectMapper();
        try {
        	Education person = mapper.readValue(output, Education.class);
            System.out.println("Person = " + person);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private static void testGetCall() {
		Client client = Client.create();

        WebResource webResource = client.resource("http://localhost:8080/LoanService/rest/person/print");

        String json = createPerson();

        ClientResponse response = webResource.accept("text/plain")
           .get(ClientResponse.class);

        System.out.println("Output from Server .... \n");
        String output = response.getEntity(String.class);
        System.out.println(output);
	}

	private static String createPerson() {
		Person p = new Person();
		p.setName(" anjali kaushik You are lovely wife");
		p.setAge("11");
		Address address = new Address();
		address.setPostalCode("226016");
		address.setStreet("Indira");
		p.setAddress(address);
		JSONObject j = new JSONObject(p);
		return j.toString();
	}

}
