<%@page contentType="text/javascript" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
[
<c:forEach var="node" varStatus="status" items="${tree}">
  <c:if test="${status.index > 0}">,</c:if>{
    "id": ${node.id},
    "pId": ${node.parentId},
    "name": "${node.name.replaceAll("\"", "\\\"")}",
    "open": true
  }
</c:forEach>
]