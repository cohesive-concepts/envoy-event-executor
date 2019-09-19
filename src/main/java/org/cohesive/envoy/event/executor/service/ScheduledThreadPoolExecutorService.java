package org.cohesive.envoy.event.executor.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

@JsonIgnoreType
public class ScheduledThreadPoolExecutorService {

	private static ScheduledExecutorService executorService  = Executors.newScheduledThreadPool(5);

	public static ScheduledExecutorService getExecutorService() {
		return executorService;
	}
}
