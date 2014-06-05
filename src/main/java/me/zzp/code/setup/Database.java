package me.zzp.code.setup;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import me.zzp.ar.DB;
import me.zzp.ar.Table;
import me.zzp.ar.ex.DBOpenException;
import me.zzp.code.bean.Solution;

public class Database implements ServletContextListener {
  private DataSource getDataSource() {
    ComboPooledDataSource pool = new ComboPooledDataSource();
    try {
      pool.setDriverClass("org.postgresql.Driver");
    } catch (PropertyVetoException ex) {
      throw new DBOpenException(ex);
    }
    pool.setJdbcUrl("jdbc:postgresql:code");
    pool.setUser("redraiment");
    pool.setPassword("123");

    pool.setMinPoolSize(8);
    pool.setMaxPoolSize(128);
    pool.setMaxIdleTime(5000);
    pool.setAcquireIncrement(8);
    
    return pool;
  }
  
  private DB open(DataSource pool) {
    DB dbo = DB.open(pool);

    Table User = dbo.active("users");
    User.hasMany("issues").by("user_id");
    User.hasMany("tools").by("user_id");

    Table Issue = dbo.active("issues");
    Issue.belongsTo("user").in("users");
    Issue.hasMany("solutions").by("issue_id");

    Table Solution = dbo.active("solutions");
    Solution.belongsTo("issue").in("issues");
    Solution.hasMany("tags").by("solution_id");
    Solution.hasAndBelongsToMany("tools").by("tool_id").through("tags");
    Solution.hasMany("snippets").by("solution_id");
    Solution.extend(new Solution());

    Table Snippet = dbo.active("snippets");
    Snippet.belongsTo("user").in("users");
    Snippet.belongsTo("solution").in("solutions");
    Snippet.belongsTo("lexer").in("lexers");

    return dbo;
  }

  @Override
  public void contextInitialized(ServletContextEvent e) {
    DataSource pool = getDataSource();
    DB dbo = open(pool);

    ServletContext context = e.getServletContext();
    context.setAttribute("pool", pool);
    context.setAttribute("dbo", dbo);

    // cache
    Table Lexer = dbo.active("lexers");
    context.setAttribute("lexers", Lexer.all());

    Table Tool = dbo.active("tools");
    context.setAttribute("tags", Tool.all());
  }

  @Override
  public void contextDestroyed(ServletContextEvent e) {
    ServletContext context = e.getServletContext();
    Object o = context.getAttribute("pool");
    if (o != null && o instanceof ComboPooledDataSource) {
      context.removeAttribute("pool");
      ComboPooledDataSource pool = (ComboPooledDataSource)o;
      pool.close();
    }
  }
}
