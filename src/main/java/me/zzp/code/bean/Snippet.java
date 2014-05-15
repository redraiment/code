package me.zzp.code.bean;

import me.zzp.ar.Record;
import org.python.util.PythonInterpreter;

public final class Snippet extends Model {
  public Snippet(Record model) {
    super(model);
  }

  public String getCode() {
    PythonInterpreter python = new PythonInterpreter();
    python.exec("from pygments import highlight");
    python.exec("from pygments.formatters import HtmlFormatter");
    
    Record lexer = model.get("lexer");
    python.exec(String.format("from %s import %s", lexer.get("module"), lexer.get("class")));
    
    python.set("code", model.getStr("code"));
    python.exec(String.format("html = highlight(code, %s(tabsize = 2, encoding = 'utf-8'), HtmlFormatter(linenos = True))", lexer.get("class")));

    return python.get("html", String.class);
  }

  public String getComment() {
    return model.get("comment");
  }

  public User getUser() {
    return one("user", User.class);
  }

  public Solution getSolution() {
    return one("solution", Solution.class);
  }

  public Lexer getLexer() {
    return one("lexer", Lexer.class);
  }
}
