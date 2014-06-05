package me.zzp.code;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.zzp.jac.Service;

public final class User extends Service {
  public User(HttpServletRequest request, HttpServletResponse response) {
    super(request, response);
  }

  @Override
  public void query() {
    redirect(String.format("/%s/", get("user")));
  }

  @Override
  public void delete() {
    query();
  }

  @Override
  public void update() {
    query();
  }

  @Override
  public void create() {
    query();
  }
}
