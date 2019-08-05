package dev.mvc.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class App {
  public static void main(String[] args) {
    String configLocation = "Spring-Scheduler.xml";
    System.out.println(configLocation);
    ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
  }                     
  
  @Scheduled(fixedDelay=1000)
  public void TestScheduler(){
      System.out.println("스케줄링 테스트");
  }

}







