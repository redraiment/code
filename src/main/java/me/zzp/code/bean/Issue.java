package me.zzp.code.bean;

import java.util.List;
import me.zzp.ar.Record;

public final class Issue extends Model {
  public Issue(Record model) {
    super(model);
  }

  public String getName() {
    return model.get("name");
  }
  
  public String getDescription() {
    return model.get("description");
  }

  public User getUser() {
    return one("user", User.class);
  }
  
  public List<Solution> getSolutions() {
    return list("solutions", Solution.class);
  }
}
