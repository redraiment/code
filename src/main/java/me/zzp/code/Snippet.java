package me.zzp.code;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.zzp.ar.DB;
import me.zzp.ar.Record;
import me.zzp.ar.Table;

public final class Snippet extends HttpServlet {
  private DB dbo;
  private Table Issue;

  @Override
  public void init() {
    dbo = (DB)getServletContext().getAttribute("dbo");
    Issue = dbo.active("issues");

    ServletContext app = getServletContext();

    Table Lexer = dbo.active("lexers");
    app.setAttribute("lexers", Lexer.all());

    Table Tool = dbo.active("tools");
    app.setAttribute("tags", Tool.all());
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String path = request.getPathInfo();
    if (path.matches("/\\d*")) {
      int id = path.length() == 1? 0: Integer.parseInt(path.substring(1));
      request.setAttribute("id", id);
      super.service(request, response);
    } else {
      request.getRequestDispatcher("/view-404").forward(request, response);
    }
  }

  // create
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }

  // update
  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }

  // read
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = (int) request.getAttribute("id");
    Record issue = id == 0? Issue.first(): Issue.find(id);
    request.setAttribute("issue", issue);
    request.getRequestDispatcher("/view-main").forward(request, response);
  }

  // delete
  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }
}
