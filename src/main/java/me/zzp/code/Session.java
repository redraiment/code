package me.zzp.code;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.zzp.ar.DB;
import me.zzp.ar.Record;
import me.zzp.ar.Table;
import me.zzp.jac.Service;

public final class Session extends Service {
  public Session(HttpServletRequest request, HttpServletResponse response) {
    super(request, response);
  }

  @Override
  public void query() {
    forward("/session-view");
  }

  @Override
  public void create() {
    String name = get("name");
    String password = get("password");
    DB dbo = get("dbo");
    Table User = dbo.active("users");
    Record user = User.first("name = ? and password = ?", name, password);
    if (user != null) {
      session.setAttribute("who", user);
      redirect(String.format("/%s/", name));
    } else {
      request.setAttribute("error", "用户名或密码错误");
      forward("/session-view");
    }
  }

  @Override
  public void delete() {
    session.removeAttribute("who");
    response.setContentType("text/javascript");
    response.setCharacterEncoding("utf-8");
    try (PrintWriter out = response.getWriter()) {
      out.println("{\"status\": \"success\"}");
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }
}
