package me.zzp.code;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.zzp.ar.DB;
import me.zzp.ar.Record;
import me.zzp.ar.Table;
import me.zzp.code.bean.User;

public class Dispatcher extends HttpServlet {
  private final DB dbo;

  public Dispatcher() {
    try {
      Class.forName("org.sqlite.JDBC");
    } catch (ClassNotFoundException e) {
      System.err.println(e.getMessage());
    }

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
  }
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println(request.getContextPath());

    List<User> users = new LinkedList<>();
    Table table = dbo.active("users");
    for (Record record : table.all()) {
      User user = new User(record);
      System.out.println(user.getName());
      users.add(user);
    }
    request.setAttribute("users", users);
    request.getRequestDispatcher("/users").forward(request, response);
  }
}
