package me.zzp.code.bean;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import me.zzp.ar.Record;
import me.zzp.ar.Table;

public abstract class Model implements Serializable {
  protected final Record model;

  public Model(Record model) {
    this.model = model;
  }
  
  public int getId() {
    return model.get("id");
  }
  
  public String getCreatedAt() {
    return model.get("created_at").toString();
  }

  public String getUpdatedAt() {
    return model.get("updated_at").toString();
  }
  
  protected <E extends Model> E one(String key, Class<E> type) {
    try {
      Constructor<E> fn = type.getConstructor(Record.class);
      Record record = model.get(key);
      return fn.newInstance(record);
    } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      System.err.println(e.getMessage());
      return null;
    }
  }
  
  protected <E extends Model> List<E> map(List<Record> records, Class<E> type) {
    try {
      Constructor<E> fn = type.getConstructor(Record.class);
      List<E> list = new LinkedList<>();
      for (Record record : records) {
        list.add(fn.newInstance(record));
      }
      return list;
    } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      System.err.println(e.getMessage());
      return null;
    }

  }

  protected <E extends Model> List<E> list(String key, Class<E> type) {
    Table table = model.get(key);
    return map(table.all(), type);
  }
}
