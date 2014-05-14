package me.zzp.code.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.zzp.ar.DB;

public abstract class Service {
  protected DB dbo;

  public void setDbo(DB dbo) {
    this.dbo = dbo;
  }

  public abstract boolean accept(String... resources);

  public abstract void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
  public abstract void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
  public abstract void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
  public abstract void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
