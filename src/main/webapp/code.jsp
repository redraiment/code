<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.python.util.PythonInterpreter"%>
<%
  final String template = "from pygments import highlight\n"
                          + "from %1$s import %2$s\n"
                          + "from pygments.formatters import HtmlFormatter\n"
                          + "result = highlight(code, %2$s(), HtmlFormatter())";
  String pkg = request.getParameter("package");
  String lang = request.getParameter("language");

  PythonInterpreter interpreter = new PythonInterpreter();
  interpreter.set("code", request.getParameter("code"));
  interpreter.exec(String.format(template, pkg, lang));
  String code = interpreter.get("result", String.class);
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><%= pkg %>.<%= lang %></title>
    <link type="text/css" rel="stylesheet" href="styles/highlight.css" />
  </head>
  <body>
    <%= code %>
  </body>
</html>
