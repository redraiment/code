package me.zzp.code.action;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.zzp.ar.Record;
import me.zzp.ar.Table;
import me.zzp.code.bean.User;

public final class Users extends Service {
  @Override
  public boolean accept(String... resources) {
    if (resources.length > 0) {
      for (String resource : resources) {
        if (!resource.isEmpty()) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.sendRedirect(request.getContextPath());
  }

  @Override
  public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.sendRedirect(request.getContextPath());
  }

  @Override
  public void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<User> users = new LinkedList<>();
    Table table = dbo.active("users");
    for (Record record : table.all()) {
      User user = new User(record);
      System.out.println(user.getName());
      users.add(user);
    }
    request.setAttribute("users", users);
    request.getRequestDispatcher("/view-users").forward(request, response);
  }

  @Override
  public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.sendRedirect(request.getContextPath());
  }
}
