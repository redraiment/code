package me.zzp.code;

import me.zzp.ar.DB;
import me.zzp.ar.Record;
import me.zzp.ar.Table;

public class Main {
  
  public static void main(String[] args) {
    new org.sqlite.JDBC();
    DB dbo = DB.open("jdbc:sqlite:/Users/redraiment/workspace/project/code/src/main/sql/code.db");
    Table Lexer = dbo.active("lexers");
    for (Record lexer : Lexer.select("id", "name").orderBy("id").all()) {
      System.out.println(lexer.get("name"));
    }
    dbo.close();
  }
}
