package org.cohesive.envoy.event.executor.service;

import java.util.ArrayList;
import java.util.List;

import org.cohesive.envoy.event.Monitor;
import org.cohesive.envoy.event.monitor.HttpEventMonitor;
import org.springframework.stereotype.Service;

@Service
public class EventExecutorService {
	
	private List<Monitor> monitors = new ArrayList<>();
	
	public EventExecutorService() {
		this.addMonitor(new HttpEventMonitor("https://www.google.com","test-app", 10000L, 10000L));
	}
	
	public List<Monitor> getMonitors() {
		return monitors;
	}
	
	public Monitor getMonitor(String id) {
		return monitors.stream()
				  .filter(monitor -> id.equals(((HttpEventMonitor) monitor).getId()))
				  .findAny()
				  .orElse(null);
	}
	
	public void addMonitor(Monitor monitor) {
		monitors.add(monitor);
	}
	
	public void deleteMonitor(String id) {
		monitors.forEach(monitor -> {
			if (id.equals(((HttpEventMonitor) monitor).getId())) {
				((HttpEventMonitor) monitor).cancel();
			}
		});
		monitors.removeIf(monitor -> id.equals(((HttpEventMonitor) monitor).getId()));
	}

}
