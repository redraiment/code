package me.zzp.code;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.zzp.ar.DB;

public abstract class Service {
  protected static DB dbo;

  protected void oops(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("/view-404").forward(request, response);
  }

  public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    oops(request, response);
  }

  public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    oops(request, response);
  }

  public void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    oops(request, response);
  }

  public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    oops(request, response);
  }
}
