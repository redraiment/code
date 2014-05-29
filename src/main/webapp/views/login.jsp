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

    <link rel="stylesheet" type="text/css" href="styles/login.css" />
  </head>
  <body>
    <form method="post" action="<c:url value="/login" />">
      <table>
        <caption>用户登入</caption>
        <tbody>
          <tr>
            <th>账号：</th>
            <td>
              <input type="text" />
            </td>
          </tr>
          <tr>
            <th>密码：</th>
            <td>
              <input type="text" />
            </td>
          </tr>
        </tbody>
        <tfoot>
          <tr>
            <th>&nbsp;</th>
            <td>
              <button type="submit">提交</button>
            </td>
          </tr>
        </tfoot>
      </table>
    </form>
  </body>
</html>
