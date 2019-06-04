package it.unisalento.se.saw.restapi;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
 
import net.minidev.json.*;
// import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.services.PushNotificationService;
 
@RequestMapping("/web")
@RestController
public class WebController {
	
	private final String TOPIC = "JavaSampleApproach";
	
	@Autowired
	PushNotificationService notifService;
	
	@RequestMapping(value = "/send",  produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<String> send() {
	 
	    JSONObject body = new JSONObject();
	    body.put("to", "/topics/" + TOPIC);
	    body.put("priority", "high");
	 
	    JSONObject notification = new JSONObject();
	    notification.put("title", "JSA Notification");
	    notification.put("body", "Happy Message!");
	    
	    JSONObject data = new JSONObject();
	    data.put("Key-1", "JSA Data 1");
	    data.put("Key-2", "JSA Data 2");
	 
	    body.put("notification", notification);
	    body.put("data", data);
	 
	/**
	    {
	       "notification": {
	          "title": "JSA Notification",
	          "body": "Happy Message!"
	       },
	       "data": {
	          "Key-1": "JSA Data 1",
	          "Key-2": "JSA Data 2"
	       },
	       "to": "/topics/JavaSampleApproach",
	       "priority": "high"
	    }
	*/
	 
	    HttpEntity<String> request = new HttpEntity<>(body.toString());
	 
	    CompletableFuture<String> pushNotification = notifService.send(request);
	    CompletableFuture.allOf(pushNotification).join();
	 
	    try {
	      String firebaseResponse = pushNotification.get();
	      
	      return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	 
	    return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
	  }

}
