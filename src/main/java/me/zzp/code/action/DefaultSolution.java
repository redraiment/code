package me.zzp.code.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.zzp.ar.Table;
import me.zzp.code.Service;
import me.zzp.code.Url;

@Url("/[^/]+/?")
public final class DefaultSolution extends Service {
  @Override
  public void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name = request.getPathInfo().split("/")[1];
    Table issues = dbo.active("users").findA("name", name).get("issues");
    int id = issues.first().get("id");
    request.getRequestDispatcher(String.format("/%s/%d", name, id)).forward(request, response);
  }
}
