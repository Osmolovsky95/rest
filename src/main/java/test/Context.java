package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Context {

   private static volatile ApplicationContext applicationContext;

   public static ApplicationContext getInstance() {
      ApplicationContext localInstance = applicationContext;
      if (localInstance == null) {
         synchronized (Context.class) {
            localInstance = applicationContext;
            if (localInstance == null) {
               applicationContext = localInstance = new ClassPathXmlApplicationContext("applicationContext.xml");
            }
         }
      }
      return localInstance;
   }
}


