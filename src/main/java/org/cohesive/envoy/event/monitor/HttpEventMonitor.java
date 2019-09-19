package org.cohesive.envoy.event.monitor;

import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;

import javax.inject.Inject;

import org.cohesive.envoy.event.Monitor;
import org.cohesive.envoy.event.MonitorEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class HttpEventMonitor extends ExecutorAwareMonitor {

	private String url = "https://www.google.com";
	private String appId = "test-app";
	
	protected Long delay = 10000L;
	protected Long interval = 10000L;
	
	public HttpEventMonitor(String url, String appId) {
		this.url = url;
		this.appId = appId;
		schedule(delay, interval, executorService );
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
