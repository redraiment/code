<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta charset="utf-8" />
    <title>用户列表 - 代码大全</title>

    <link rel="shortcut icon" type="image/png" href="images/fav.png" />
    <link rel="apple-touch-icon" sizes="57x57" type="image/png" href="images/fav-57x57.png" />
    <link rel="apple-touch-icon" sizes="114x114" type="image/png" href="images/fav-114x114.png" />

    <link type="text/css" rel="stylesheet" href="styles/reset.css" />
  </head>
  <body>
    <h1>用户列表</h1>
    <ul>
      <c:forEach var="user" items="${users}">
        <li><a href="<c:url value="/${user.name}" />">${user.name}</a></li>
      </c:forEach>
    </ul>
  </body>
</html>
