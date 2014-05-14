package me.zzp.code;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ServiceLoader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.zzp.ar.DB;
import me.zzp.ar.Table;
import me.zzp.code.action.Service;

public class Dispatcher extends HttpServlet {
  private final DB dbo;
  private final List<Service> services;

  public Dispatcher() {
    dbo = DB.open("jdbc:sqlite:/Users/redraiment/workspace/project/code/src/main/sql/code.db");
    Table User = dbo.active("users");
    User.hasMany("issues").by("user_id");
    User.hasMany("tools").by("user_id");

    Table Issue = dbo.active("issues");
    Issue.belongsTo("user").in("users");
    Issue.hasMany("solutions").by("issue_id");

    Table Solution = dbo.active("solutions");
    Solution.belongsTo("issue").in("issues");
    Solution.hasMany("tags").by("solution_id");
    Solution.hasAndBelongsToMany("tools").by("tool_id").through("tags");
    Solution.hasMany("snippets").by("solution_id");

    Table Snippet = dbo.active("snippets");
    Snippet.belongsTo("user").in("users");
    Snippet.belongsTo("solution").in("solutions");
    Snippet.hasOne("lexer").in("lexers");
    
    services = new LinkedList<>();
    for (Service s : ServiceLoader.load(Service.class)) {
      s.setDbo(dbo);
      services.add(s);
    }
  }
  
  private Service some(String path) {
    String[] resources = path.split("/");
    for (Service s : services) {
      if (s.accept(resources)) {
        return s;
      }
    }
    return null;
  }

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
