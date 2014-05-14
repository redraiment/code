package me.zzp.code.bean;

import me.zzp.ar.Record;

public final class Snippet extends Model {
  public Snippet(Record model) {
    super(model);
  }
  
  public String getCode() {
    return model.get("code");
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
