package org.cohesive.envoy.event.executor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import org.cohesive.envoy.event.Monitor;
import org.cohesive.envoy.event.monitor.HttpEventMonitor;
import org.springframework.stereotype.Service;

@Service
public class EventExecutorService {
	
	private List<Monitor> monitors = new ArrayList<>();
	
	@Inject
	public EventExecutorService() {
		this.addMonitor(new HttpEventMonitor("https://www.google.com","test-app"));
	}
	
	public List<Monitor> getMonitors() {
		return monitors;
	}
	
	public void addMonitor(Monitor monitor) {
		monitors.add(monitor);
	}
	
	

}
