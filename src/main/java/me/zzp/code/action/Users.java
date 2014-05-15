package me.zzp.code.action;

import me.zzp.code.Service;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.zzp.ar.Record;
import me.zzp.ar.Table;
import me.zzp.code.Url;
import me.zzp.code.bean.User;

@Url("/?")
public final class Users extends Service {
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
}
