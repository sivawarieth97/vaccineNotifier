package com.example.demo.com.example.cowin;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.UriBuilder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@Component 
public class apiRequest {
	
	@PostConstruct  
    @Scheduled(cron = "* * * * * 1") 
	public static void sendRequest() throws IOException, Exception {
		
		URL url=new URL("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode=673001&date=17-08-2021");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		int code=connection.getResponseCode();
		System.out.println(code);
		StringBuffer response = new StringBuffer();
		String readline=null;
		if(code==HttpURLConnection.HTTP_OK) {
			
			Scanner sc= new Scanner(url.openStream());
			while(sc.hasNext()) {
				response.append(sc.nextLine());
			}sc.close();
			System.out.println(response);
			
			
			
			
			Object obj = new JSONParser().parse(response.toString());
	          
			 
		    JSONObject jo = (JSONObject) obj;
		     
		    JSONArray session = (JSONArray) jo.get("sessions");
		    HashMap<String,String> map= new HashMap<>();
		       

		    String chatID="655291282";
		    String botToken="1875463888:AAE102tVxHt3lvmcioRUZwpUGtcuiJ2l0jE";
		    for(int i=0;i<session.size();i++) {
		        	JSONObject data = (JSONObject) session.get(i);
		        	StringBuilder message=new StringBuilder();
		        	message.append("Hi Cowin Update \n");
		        	message.append("Date : "+data.get("date")+"\n");
		        	message.append("Name : "+data.get("name")+"\n");
		        	message.append("Address : "+data.get("address")+"\n");
		        	message.append("fee Type : "+data.get("fee_type")+"\n");
		        	message.append("Available Capacity for dose1 : "+data.get("available_capacity_dose1")+"\n");
		        	message.append("Available Capacity for dose2 : "+data.get("available_capacity_dose2")+"\n");
		        	
		        	System.out.println(data.get("date"));
		        	
		        	
				    UriBuilder builder = UriBuilder
			                .fromUri("https://api.telegram.org")
			                .path("/{botToken}/sendMessage")
			                .queryParam("chat_id", chatID)
			                .queryParam("text", message.toString());
				    
				    
			        HttpClient client = HttpClient.newBuilder()
			                .connectTimeout(Duration.ofSeconds(5))
			                .version(HttpClient.Version.HTTP_2)
			                .build();
			 
			        
			 
			        HttpRequest request = HttpRequest.newBuilder()
			                .GET()
			                .uri(builder.build("bot" + botToken))
			                .timeout(Duration.ofSeconds(5))
			                .build();
			 
			        HttpResponse<String> response1 = client
			          .send(request, HttpResponse.BodyHandlers.ofString());
			 
		        
		        }
		    
		    
		    
	       
		}
		        
		        
		else {
			System.out.println("Error");
		}
		
	}
}
