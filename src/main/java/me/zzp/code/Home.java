package me.zzp.code;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.zzp.ar.DB;
import me.zzp.ar.Record;
import me.zzp.ar.Table;
import me.zzp.jac.Service;

public final class Home extends Service {
  private final DB dbo;
  private final Table User;

  public Home(HttpServletRequest request, HttpServletResponse response) {
    super(request, response);
    dbo = get("dbo");
    User = dbo.active("users");
  }

  @Override
  public void query() {
    Record user = User.findA("name", get("user"));
    if (user != null) {
      Table Issue = user.get("issues");
      int id = getInt("id");
      Record issue = id > 0? Issue.find(id): Issue.first();
      if (issue != null) {
        request.setAttribute("issue", issue);
        forward("/home-view");
      } else {
        forward("/statics/404.html");
      }
    } else {
      forward("/statics/404.html");
    }
  }
}
