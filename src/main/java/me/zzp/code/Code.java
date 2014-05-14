package me.zzp.code;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.zzp.ar.DB;
import me.zzp.ar.Record;
import me.zzp.ar.Table;

public final class Code extends HttpServlet {
  private final DB dbo;
  private final Table User, Issue, Tool, Lexer, Solution, Snippet, Tag;
  private final List<Record> lexers;
  
  public Code() {
    new org.sqlite.JDBC();
    dbo = DB.open("jdbc:sqlite:/Users/redraiment/workspace/project/code/src/main/sql/code.db");

    User = dbo.active("users");
    Issue = dbo.active("issues");
    Tool = dbo.active("tools");
    Lexer = dbo.active("lexers");
    Solution = dbo.active("solutions");
    Snippet = dbo.active("snippets");
    Tag = dbo.active("tags");

    User.hasMany("issues").by("user_id");
    User.hasMany("tools").by("user_id");
    Issue.hasMany("solutions").by("issue_id");
    Solution.hasMany("snippets").by("solution_id");
    Solution.hasMany("tags").by("solution_id");
    Solution.hasMany("tools").by("tool_id").through("tags");
    Snippet.belongsTo("lexer").by("lexer_id").by("lexers");
    
    lexers = Lexer.select("id", "name").orderBy("id").all();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setAttribute("issue", Issue.find(1));
    request.setAttribute("lexers", lexers);
    request.getRequestDispatcher("/views/snippet.jsp").forward(request, response);
  }
}
