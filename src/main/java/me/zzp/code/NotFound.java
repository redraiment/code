package me.zzp.code;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.zzp.jac.Service;

public final class NotFound extends Service {
  private static final String PAGE = "/statics/404.html";
  
  public NotFound(HttpServletRequest request, HttpServletResponse response) {
    super(request, response);
  }

  @Override
  public void query() {
    forward(PAGE);
  }

  @Override
  public void delete() {
    redirect(PAGE);
  }

  @Override
  public void update() {
    redirect(PAGE);
  }

  @Override
  public void create() {
    redirect(PAGE);
  }
}
