package me.zzp.code.bean;

import java.util.List;
import me.zzp.ar.Record;
import me.zzp.ar.Table;

public final class User extends Model {
  public User(Record model) {
    super(model);
  }
  
  public String getName() {
    return model.get("name");
  }
  
  public String getPassword() {
    return model.get("password");
  }
  
  public String getEmail() {
    return model.get("email");
  }
  
  public String getHomepage() {
    return model.get("homepage");
  }
  
  public List<Issue> getIssues() {
    return list("issues", Issue.class);
  }

  public List<Tool> getTools() {
    return list("tools", Tool.class);
  }

  public Issue getIssue(int id) {
    Table table = model.get("issues");
    return new Issue(table.find(id));
  }
}
