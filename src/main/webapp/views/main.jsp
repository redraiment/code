<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta charset="UTF-8" /> 
    <title>${issue.name} - 代码大全</title>

    <link rel="shortcut icon" type="image/png" href="<c:url value="/images/fav.png" />" />
    <link rel="apple-touch-icon" sizes="57x57" type="image/png" href="<c:url value="/images/fav-57x57.png" />" />
    <link rel="apple-touch-icon" sizes="114x114" type="image/png" href="<c:url value="/images/fav-114x114.png" />" />

    <link type="text/css" rel="stylesheet" href="<c:url value="/styles/reset.css" />" />
    <link type="text/css" rel="stylesheet" href="<c:url value="/styles/jquery.chosen.css" />" />
    <link type="text/css" rel="stylesheet" href="<c:url value="/styles/ztree.css" />" />
    <link type="text/css" rel="stylesheet" href="<c:url value="/styles/main.css" />" />

    <link id="code-theme" type="text/css" rel="stylesheet" href="<c:url value="/styles/highlight/default.css" />" />
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
        <h1>${issue.name}</h1>
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
        ${issue.description}
      </section>

      <c:forEach var="solution" items="${issue.solutions}">
        <c:set var="snippets" value="${solution.snippets}" />
        <section>
          <h1>${solution.title}</h1>
          <div>
            <label>版本：</label>
            <select class="version">
              <c:forEach varStatus="status" items="${snippets}">
                <option>${snippets.size() - status.index}</option>
              </c:forEach>
            </select>
            <button>改进</button>
            <button>修正</button>
            <button>引用</button>
          </div>
          <c:forEach var="snippet" items="${snippets}">
            <p>${snippet.code}</p>
          </c:forEach>
          <p>变更日志：</p>
          <c:forEach var="snippet" items="${snippets}">
            <p>[${snippet.createdAt}] <a rel="nofollow" target="_blank" href="http://weibo.com/redraiment">@${snippet.user.name}</a></p>
            <blockquote>${snippet.comment}</blockquote>
          </c:forEach>
        </section>
      </c:forEach>

      <section>
        <table id="workspace">
          <caption>解决方案</caption>
          <thead>
            <tr>
              <td>
                <select id="syntax">
                  <c:forEach var="lexer" items="${lexers}">
                    <option value="${lexer.id}">${lexer.name}</option>
                  </c:forEach>
                </select>
              </td>
              <td>
                <select id="tags" multiple="multiple">
                  <c:forEach var="tag" items="${tags}">
                    <option>${tag.name}</option>
                  </c:forEach>
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

    <script type="text/javascript" src="<c:url value="/scripts/jquery.js" />"></script>
    <script type="text/javascript" src="<c:url value="/scripts/jquery.chosen.js" />"></script>
    <script type="text/javascript" src="<c:url value="/scripts/jquery.ztree.js" />"></script>
    <script type="text/javascript" src="<c:url value="/scripts/main.js" />"></script>
  </body>
</html>
