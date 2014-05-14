package me.zzp.code.bean;

import me.zzp.ar.Record;

public final class Lexer extends Model {
  public Lexer(Record model) {
    super(model);
  }

  public String getName() {
    return model.get("name");
  }
}
