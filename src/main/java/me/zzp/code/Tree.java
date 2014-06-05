package me.zzp.code;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.zzp.ar.DB;
import me.zzp.ar.Record;
import me.zzp.ar.Table;
import me.zzp.jac.Service;

public final class Tree extends Service {
  private final DB dbo;
  private final Table User;

  public Tree(HttpServletRequest request, HttpServletResponse response) {
    super(request, response);
    dbo = get("dbo");
    User = dbo.active("users");
  }

  @Override
  public void query() {
    Record user = User.findA("name", get("user"));
    if (user != null) {
      Table table = user.get(getStr("property"));
      request.setAttribute("tree", table.all());
      forward("/json-view");
    } else {
      forward("/statics/404.html");
    }
  }
}
