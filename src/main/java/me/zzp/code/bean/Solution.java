package me.zzp.code.bean;

import me.zzp.ar.Record;
import me.zzp.ar.Table;

public final class Solution {
  public String getTitle(Record self, Object value) {
    Table Tool = self.get("tools");
    StringBuilder title = new StringBuilder();
    for (Record tool : Tool.all()) {
      if (title.length() > 0) {
        title.append('+');
      }
      title.append(tool.get("name"));
    }
    return title.toString();
  }
}
