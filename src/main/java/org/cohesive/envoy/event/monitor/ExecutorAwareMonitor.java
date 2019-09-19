package org.cohesive.envoy.event.monitor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.cohesive.envoy.event.Monitor;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
public abstract class ExecutorAwareMonitor extends Monitor {

	@JsonIgnore
	ScheduledExecutorService executorService  = Executors.newScheduledThreadPool(5);	
	

}
