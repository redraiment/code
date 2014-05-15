package me.zzp.code.action;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.zzp.ar.Record;
import me.zzp.ar.Table;
import me.zzp.code.Service;
import me.zzp.code.Url;
import me.zzp.code.bean.Issue;
import me.zzp.code.bean.Lexer;

@Url("/[^/]+/[1-9]\\d*")
public final class Solutions extends Service {
  private static final List<Lexer> lexers = new LinkedList<>();
  
  private static List<Lexer> getLexers() {
    if (lexers.isEmpty()) {
      synchronized(lexers) {
        if (lexers.isEmpty()) {
          for (Record record : dbo.active("lexers").all()) {
            lexers.add(new Lexer(record));
          }
        }
      }
    }
    return lexers;
  }
  
  @Override
  public void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String[] resources = request.getPathInfo().split("/");
    String name = resources[1];
    int id = Integer.valueOf(resources[2]);
    
    Record user = dbo.active("users").findA("name", name);
    if (user == null) {
      oops(request, response);
    }

    Table issues = user.get("issues");
    Record issue = issues.find(id);
    if (issue == null) {
      oops(request, response);
    }

    request.setAttribute("issue", new Issue(issue));
    request.setAttribute("lexers", getLexers());
    request.getRequestDispatcher("/view-main").forward(request, response);
  }
}
