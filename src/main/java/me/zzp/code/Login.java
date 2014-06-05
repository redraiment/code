package me.zzp.code;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.zzp.ar.DB;
import me.zzp.ar.Record;
import me.zzp.ar.Table;
import me.zzp.jac.Service;

public final class Login extends Service {
  public Login(HttpServletRequest request, HttpServletResponse response) {
    super(request, response);
  }

  @Override
  public void query() {
    forward("/login-view");
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
      redirect("/".concat(name));
    } else {
      request.setAttribute("error", "用户名或密码错误");
      forward("/login-view");
    }
  }
}
