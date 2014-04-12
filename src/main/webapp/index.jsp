<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Snippet</title>
  </head>
  <body>
    <form method="post" action="code.jsp">
      <div>
        <label>Package: </label>
        <input name="package" type="text" />
        <label>Language: </label>
        <input name="language" type="text" />
      </div>
      <div>
        <textarea name="code"></textarea>
      </div>
      <div>
        <button type="submit">Submit</button>
      </div>
    </form>
  </body>
</html>
