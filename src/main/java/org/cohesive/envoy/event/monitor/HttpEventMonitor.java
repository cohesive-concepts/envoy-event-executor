package org.cohesive.envoy.event.monitor;

import java.util.Date;

import org.cohesive.envoy.event.Monitor;
import org.cohesive.envoy.event.MonitorEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class HttpEventMonitor extends Monitor {

	private String url;
	private String appId;
	
	public HttpEventMonitor(String url, String appId) {
		super();
		this.url = url;
		this.appId = appId;
	}

	@Override
	public MonitorEvent performCheck() {
		Boolean send = true;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//		if (response.getStatusCodeValue() > 204) {
//			send = true;
//		}
		return emitter.createEvent()
				.applicationId(appId)
				.message("HTTP status > 204")
				.priority("error")
				.timestamp(new Date().getTime())
				.title("HTTP Status failure")
				.send(send);
	}

}
