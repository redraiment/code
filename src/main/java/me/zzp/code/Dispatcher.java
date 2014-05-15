package me.zzp.code;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import me.zzp.ar.DB;
import me.zzp.ar.Table;

public final class Dispatcher extends HttpServlet {
  private final Map<String, Service> services;

  public Dispatcher() {
    DataSource pool;
    try {
      InitialContext env = new InitialContext();
      pool = (DataSource)env.lookup("java:comp/env/jdbc/datasource");
    } catch(NamingException e) {
      System.err.println(e.getMessage());
      System.err.println("Cannot get database source");
      pool = null;
    }
    Service.dbo = DB.open(pool);

    Table User = Service.dbo.active("users");
    User.hasMany("issues").by("user_id");
    User.hasMany("tools").by("user_id");

    Table Issue = Service.dbo.active("issues");
    Issue.belongsTo("user").in("users");
    Issue.hasMany("solutions").by("issue_id");

    Table Solution = Service.dbo.active("solutions");
    Solution.belongsTo("issue").in("issues");
    Solution.hasMany("tags").by("solution_id");
    Solution.hasAndBelongsToMany("tools").by("tool_id").through("tags");
    Solution.hasMany("snippets").by("solution_id");

    Table Snippet = Service.dbo.active("snippets");
    Snippet.belongsTo("user").in("users");
    Snippet.belongsTo("solution").in("solutions");
    Snippet.belongsTo("lexer").in("lexers");

    services = new HashMap<>();
    for (Service s : ServiceLoader.load(Service.class)) {
      Url url = s.getClass().getAnnotation(Url.class);
      services.put(url.value(), s);
    }
  }

  private Service some(String path) {
    for (Map.Entry<String, Service> s : services.entrySet()) {
      if (path.matches(s.getKey())) {
        return s.getValue();
      }
    }
    return null;
  }

  // create
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    some(request.getPathInfo()).create(request, response);
  }

  // update
  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    some(request.getPathInfo()).update(request, response);
  }

  // query
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    some(request.getPathInfo()).query(request, response);
  }

  // delete
  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    some(request.getPathInfo()).delete(request, response);
  }
}
