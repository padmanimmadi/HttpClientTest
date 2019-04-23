package com.sample.HttpClientapiTest;

import javax.ws.rs.core.MediaType;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.core.executors.ApacheHttpClient4Executor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseClass {
	
	String ROOT_URL=null;
	ClientRequest request=null;
	ClientResponse response =null;
	
	@BeforeClass
	public void setup()
	{
		 ROOT_URL="http://pqalngwos303.corp.intuit.net:8080/nextgenweb/nxgen/wi/1.0/accounts?action=discovery";
		
	}
	
	
	@BeforeMethod
	public void createRequest()
	{
		CloseableHttpClient client=HttpClients.createDefault();
		request=new ClientRequest(ROOT_URL,new ApacheHttpClient4Executor(client));
		request.header("intuit_offeringid","6");
		request.header("intuit_providerid","6");
		request.header("intuit_tid","NGWI-PFM-sanity-020");
		request.header("Authorization","Authorization");
		request.header("intuit_ficds_fi_url","http://avatar.corp.intuit.net/SuperMock/data/edit/1095A-test.jsp");
		request.header("intuit_appid","ngwi");
		request.header("intuit_app_secret","ngwisecretkey");
		request.header("Content-Type","application/json");
		request.header("intuit_fds_agent_config_url","https://financialprovider.platform.intuit.net/v1/providers/995c9bc3-a155-498f-9e3b-bf06e8d0e331/channels/webIntegration/41b267ee-fcd7-4bc3-8757-dae4fc43223e?agentType=NGWI");
	}
	
	
	public void execute_request() throws Exception
	{
		String reqBody="{  \n" + 
				"   \"Accounts\":{  \n" + 
				"      \"Login\":{  \n" + 
				"         \"ExtraNVPairs\":[\n" + 
				"            {\n" + 
				"               \"Name\":\"Banking Userid\",\n" + 
				"               \"Value\":\"SANITY_TEST\"\n" + 
				"            },\n" + 
				"            {\n" + 
				"               \"Name\":\"Banking Password\",\n" + 
				"               \"Value\":\"ihp\"\n" + 
				"            }\n" + 
				"         ]\n" + 
				"      },\n" + 
				"      \"ExecutionData\":{  \n" + 
				"         \"CustomerId\":123456,\n" + 
				"         \"InstitutionId\":200000,\n" + 
				"         \"DataMode\":\"PFM\",\n" + 
				"         \"ExtraNVPairs\":[],\n" + 
				"         \"ScriptName\":\"ngwitestbank.scr\"\n" + 
				"      }\n" + 
				"   }\n" + 
				"}";
		
		request.body(MediaType.APPLICATION_JSON, reqBody);
		postRequest(request);
	}

	
	

	public void postRequest(ClientRequest request) throws Exception
	{
		response=request.post();
		
		//Assert the requests
		System.out.println(response.getResponseStatus());
		System.out.println(response.getStatus());
		System.out.println(response.getEntity(String.class).toString());
		
		ObjectMapper mapper;
	}
	
}
