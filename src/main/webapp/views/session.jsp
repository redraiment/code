<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登入 - 代码大全</title>

    <link rel="shortcut icon" type="image/png" href="<c:url value="/images/fav.png" />" />
    <link rel="apple-touch-icon" sizes="57x57" type="image/png" href="<c:url value="/images/fav-57x57.png" />" />
    <link rel="apple-touch-icon" sizes="114x114" type="image/png" href="<c:url value="/images/fav-114x114.png" />" />

    <link rel="stylesheet" type="text/css" href="styles/session.css" />
  </head>
  <body>
    <c:if test="${empty who}">
      <form method="post" action="<c:url value="/" />">
        <table>
          <caption>用户登入</caption>
          <tbody>
            <tr>
              <th>账号：</th>
              <td>
                <input name="name" type="text" />
              </td>
            </tr>
            <tr>
              <th>密码：</th>
              <td>
                <input name="password" type="password" />
              </td>
            </tr>
            <tr>
              <td colspan="2" class="error-message">
                <c:out value="${error}" />
              </td>
            </tr>
          </tbody>
          <tfoot>
            <tr>
              <td colspan="2">
                <button type="submit">提交</button>
              </td>
            </tr>
          </tfoot>
        </table>
      </form>
    </c:if>
    <c:if test="${! empty who}">
      <a href="<c:url value="/${who.name}/" />">主页</a>
      <button>退出</button>
      <script type="text/javascript" src="<c:url value="/scripts/jquery.js" />"></script>
      <script type="text/javascript" src="<c:url value="/scripts/jquery.restful.js" />"></script>
      <script type="text/javascript" src="<c:url value="/scripts/code.session.js" />"></script>
    </c:if>
  </body>
</html>
