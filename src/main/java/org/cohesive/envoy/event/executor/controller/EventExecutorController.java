package org.cohesive.envoy.event.executor.controller;

import java.util.List;

import javax.inject.Inject;

import org.cohesive.envoy.event.Monitor;
import org.cohesive.envoy.event.executor.service.EventExecutorService;
import org.cohesive.envoy.event.monitor.HttpEventMonitor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventExecutorController {
	
	@Inject
	EventExecutorService service;
		
	@GetMapping("/monitors")
    public List<Monitor> getMonitors() {
		return service.getMonitors();
    }
	
	@GetMapping("/monitors/{id}")
    public Monitor getMonitor(@PathVariable("id") String id) {
		return service.getMonitor(id);
    }
	
	@PostMapping("/monitors")
    public void addMonitor(@RequestBody HttpEventMonitor monitor) {
		service.addMonitor(monitor);
    }
	
	@DeleteMapping("/monitors/{id}")
    public void delteMonitor(@PathVariable("id") String id) {
		service.deleteMonitor(id);
    }

}
