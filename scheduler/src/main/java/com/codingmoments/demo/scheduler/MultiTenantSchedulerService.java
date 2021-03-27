package com.codingmoments.demo.scheduler;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

@Service
public class MultiTenantSchedulerService {

  private static final Logger LOGGER = LoggerFactory.getLogger(MultiTenantSchedulerService.class);

  private final static int[] TENANT_IDS = { 1, 2, 3, 4, 5 };
  private final static int FIXED_DELAY_SECONDS = 15;

  @Autowired
  private TaskScheduler taskScheduler;

  @PostConstruct
  public void scheduleTasksForAllTenants() {
    // Schedule task for each tenant
    for (int tenantId : TENANT_IDS) {
      taskScheduler.scheduleWithFixedDelay(() -> {
        processTenant(tenantId);
      }, FIXED_DELAY_SECONDS * 1000);
    }
  }

  private void processTenant(int tenantId) {
    LOGGER.info("processTenant for tenant - {}", tenantId);
    // Set tenant in the context
    try {
      // Thread sleep represents the time taken by the business logic
      Thread.sleep(3000);
    }
    catch (InterruptedException e) {
    }
  }

  @Bean
  public TaskScheduler taskScheduler() {
    ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    taskScheduler.setPoolSize(10);
    taskScheduler.setThreadNamePrefix("taskScheduler");
    taskScheduler.initialize();
    return taskScheduler;
  }

}
