package org.cohesive.envoy.event.monitor;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import javax.inject.Inject;

import org.cohesive.envoy.event.Monitor;
import org.cohesive.envoy.event.MonitorEvent;
import org.cohesive.envoy.event.executor.service.ScheduledThreadPoolExecutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonAutoDetect(getterVisibility=Visibility.PUBLIC_ONLY)
public class HttpEventMonitor extends Monitor {

	private String id;
	private String url;
	private String appId;
	
	protected Long delay;
	protected Long interval;
	
	@JsonIgnore
	private ScheduledFuture<?> future;

	@Inject
	@JsonIgnore
	private ScheduledExecutorService service = ScheduledThreadPoolExecutorService.getExecutorService();
	
	public HttpEventMonitor(String url, String appId, Long delay, Long interval) {
		this.id = UUID.randomUUID().toString();
		this.url = url;
		this.appId = appId;
		this.delay = delay;
		this.interval = interval;
		this.future = schedule(this.delay, this.interval, this.service);
	}

	@Override
	public MonitorEvent performCheck() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		if (response.getStatusCodeValue() == 200) {
			return emitter.createEvent()
				.applicationId(appId)
				.message("HTTP status: " + response.getStatusCodeValue() + " "
					+	 "URL: " + url )
				.priority("error")
				.timestamp(new Date().getTime())
				.title("HTTP Status failure")
				.send(true);
		}
		return null;
	}
	
	public void cancel() {
		this.future.cancel(true);
	}
	
	public String getId() {
		return this.id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Long getDelay() {
		return this.delay;
	}

	public void setDelay(Long delay) {
		this.delay = delay;
	}

	public Long getInterval() {
		return this.interval;
	}

	public void setInterval(Long interval) {
		this.interval = interval;
	}

	public ScheduledExecutorService getService() {
		return service;
	}

	public void setService(ScheduledExecutorService service) {
		this.service = service;
	}

}
