<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
    <title>代码大全</title>

    <link rel="shortcut icon" type="image/png" href="images/fav.png" />
    <link rel="apple-touch-icon" sizes="57x57" type="image/png" href="images/fav-57x57.png" />
    <link rel="apple-touch-icon" sizes="114x114" type="image/png" href="images/fav-114x114.png" />

    <link type="text/css" rel="stylesheet" href="styles/reset.css" />
    <link type="text/css" rel="stylesheet" href="styles/jquery.chosen.css" />
    <link type="text/css" rel="stylesheet" href="styles/ztree.css" />
    <link type="text/css" rel="stylesheet" href="styles/main.css" />

    <link id="code-theme" type="text/css" rel="stylesheet" href="styles/highlight/default.css" />
  </head>
  <body>
    <aside class="left">
      <h1>问题分类</h1>
      <menu>
        <menuitem class="expand">展开</menuitem>
        <menuitem class="collapse">收拢</menuitem>
        <menuitem class="add">添加</menuitem>
        <menuitem class="delete">删除</menuitem>
        <menuitem class="edit">编辑</menuitem>
      </menu>
      <ul id="issues" class="ztree"></ul>
    </aside>

    <aside class="right">
      <h1>工具分类</h1>
      <menu>
        <menuitem class="all">全选</menuitem>
        <menuitem class="inverse">反选</menuitem>
        <menuitem class="expand">展开</menuitem>
        <menuitem class="collapse">收拢</menuitem>
        <menuitem class="add">添加</menuitem>
        <menuitem class="delete">删除</menuitem>
        <menuitem class="edit">编辑</menuitem>
      </menu>
      <ul id="tools" class="ztree"></ul>
    </aside>

    <article>
      <header>
        <h1>${issue.get("name")}</h1>
        <select id="theme">
          <option>autumn</option>
          <option>borland</option>
          <option>bw</option>
          <option>colorful</option>
          <option selected="selected">default</option>
          <option>emacs</option>
          <option>friendly</option>
          <option>fruity</option>
          <option>manni</option>
          <option>monokai</option>
          <option>murphy</option>
          <option>native</option>
          <option>pastie</option>
          <option>perldoc</option>
          <option>rrt</option>
          <option>tango</option>
          <option>trac</option>
          <option>vim</option>
          <option>vs</option>
        </select>
      </header>

      <section>
        <h1>问题描述</h1>
        ${issue.get("description")}
      </section>

      <c:forEach var="solution" items='${issue.get("solutions")}'>
        <section>
          
        </section>
      </c:forEach>
      <section>
        <h1>Java</h1>
        <div>
          <label>版本：</label>
          <select class="version">
            <option value="0">3</option>
            <option value="1">2</option>
            <option value="2">1</option>
          </select>
          <button>改进</button>
          <button>修正</button>
        </div>
        <table class="highlighttable"><tr><td class="linenos"><div class="linenodiv"><pre>1</pre></div></td><td class="code"><div class="highlight"><pre><span class="kd">public</span> <span class="kd">class</span> <span class="nc">Main</span> <span class="o">{</span></pre></div></td></tr></table>
        <table class="highlighttable"><tr><td class="linenos"><div class="linenodiv"><pre>2</pre></div></td><td class="code"><div class="highlight"><pre>    <span class="kd">public</span> <span class="kd">static</span> <span class="kt">void</span> <span class="nf">main</span><span class="o">(</span><span class="n">String</span><span class="o">[]</span> <span class="n">args</span><span class="o">)</span> <span class="o">{</span></pre></div></td></tr></table>
        <table class="highlighttable"><tr><td class="linenos"><div class="linenodiv"><pre>3</pre></div></td><td class="code"><div class="highlight"><pre>        <span class="kt">int</span> <span class="n">a</span> <span class="o">=</span> <span class="mi">0</span><span class="o">,</span> <span class="n">b</span> <span class="o">=</span> <span class="mi">1</span><span class="o">;</span></pre></div></td></tr></table>
        <p>变更日志：</p>
        <p>[2014-01-20 01:23:45] <a rel="nofollow" target="_blank" href="http://weibo.com/redraiment">@redraiment</a></p>
        <blockquote>init password</blockquote>
        <p>[2014-01-23 01:23:45] <a rel="nofollow" target="_blank" href="http://weibo.com/redraiment">@redraiment</a></p>
        <blockquote>Hello world</blockquote>
      </section>

      <section>
        <table id="workspace">
          <caption>解决方案</caption>
          <thead>
            <tr>
              <td>
                <select id="syntax">
                  <c:forEach var="lex" items="${lexers}">
                    <option value='${lex.get("id")}'>${lex.get("name")}</option>
                  </c:forEach>
                </select>
              </td>
              <td>
                <select id="tags" multiple="multiple">
                  <option>Java</option>
                  <option>JavaScript</option>
                  <option>XML</option>
                </select>
              </td>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td><textarea></textarea></td>
              <td><textarea></textarea></td>
            </tr>
          </tbody>
          <tfoot>
            <tr>
              <td></td>
              <td><button>提交</button></td>
            </tr>
          </tfoot>
        </table>
      </section>

    </article>

    <script type="text/javascript" src="scripts/jquery.js"></script>
    <script type="text/javascript" src="scripts/jquery.chosen.js"></script>
    <script type="text/javascript" src="scripts/jquery.ztree.js"></script>
    <script type="text/javascript" src="scripts/main.js"></script>
  </body>
</html>