package me.zzp.code.setup;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import me.zzp.code.Session;
import me.zzp.code.Home;
import me.zzp.code.Tree;
import me.zzp.code.NotFound;
import me.zzp.code.User;
import me.zzp.jac.Dispatcher;

public class Services implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent e) {
    Dispatcher.add("/", Session.class);
    Dispatcher.add("/{user}", User.class);
    Dispatcher.add("/{user}/{property:(?:issue|tool)s}", Tree.class);
    Dispatcher.add("/{user}/{id:\\d*}", Home.class);
    Dispatcher.add("/{anything:.*}", NotFound.class);
  }

  @Override
  public void contextDestroyed(ServletContextEvent e) {
  }
}
