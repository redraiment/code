package me.zzp.code.bean;

import me.zzp.ar.Record;

public final class Tool extends Model {
  public Tool(Record model) {
    super(model);
  }

  public String getName() {
    return model.get("name");
  }
}
