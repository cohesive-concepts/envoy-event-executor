package org.cohesive.envoy.event.executor.controller;

import java.util.List;

import javax.inject.Inject;

import org.cohesive.envoy.event.Monitor;
import org.cohesive.envoy.event.executor.service.EventExecutorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventExecutorController {
	
	@Inject
	EventExecutorService service;
	
	@RequestMapping("/monitors")
    public List<Monitor> monitors() {
		return service.getMonitors();
    }

}
