package me.zzp.code.setup;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import me.zzp.code.Login;
import me.zzp.code.Home;
import me.zzp.code.NotFound;
import me.zzp.jac.Dispatcher;

public class Services implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent e) {
    Dispatcher.add("/", Login.class);
    Dispatcher.add("/{user}", Home.class);
    Dispatcher.add("/{user}/{id:\\d*}", Home.class);
    Dispatcher.add("/{anything:.*}", NotFound.class);
  }

  @Override
  public void contextDestroyed(ServletContextEvent e) {
  }
}
