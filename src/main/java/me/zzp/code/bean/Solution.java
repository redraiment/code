package me.zzp.code.bean;

import java.util.List;
import me.zzp.ar.Record;
import me.zzp.ar.Table;

public final class Solution extends Model {
  public Solution(Record model) {
    super(model);
  }
  
  public Issue getIssue() {
    return one("issue", Issue.class);
  }
  
  public List<Snippet> getSnippets() {
    Table snippets = model.get("snippets");
    return map(snippets.select().orderBy("id desc").all(), Snippet.class);
  }
  
  public List<Tool> getTools() {
    return list("tools", Tool.class);
  }

  public String getTitle() {
    StringBuilder title = new StringBuilder();
    for (Tool tool : getTools()) {
      if (title.length() > 0) {
        title.append("+");
      }
      title.append(tool.getName());
    }
    return title.toString();
  }
}
