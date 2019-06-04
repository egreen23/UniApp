package it.unisalento.se.saw.services;

import java.io.IOException;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
 
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PushNotificationService {

	private static final String FIREBASE_SERVER_KEY = "AAAAbkXDo9Y:APA91bGCPb7tcLjJ_daP3bKb0PMjvs1LuSRDc-nMSN3rSWrFk8nZrdJO9Z9A01Ivh7xy9VeMEAf_m40fbM4GITo-3rBamj17mL68Rxgghrym0t8dKqBWxRbLSOIX6dadpDo5VT_IUFXh";
	private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";
	
	@Async
	  public CompletableFuture<String> send(HttpEntity<String> entity) {
	 
	    RestTemplate restTemplate = new RestTemplate();
	 
	    /**
	    https://fcm.googleapis.com/fcm/send
	    Content-Type:application/json
	    Authorization:key=FIREBASE_SERVER_KEY*/
	 
	    ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
	    interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
	    interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
	    restTemplate.setInterceptors(interceptors);
	 
	    String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);
	 
	    return CompletableFuture.completedFuture(firebaseResponse);
	  }
}
