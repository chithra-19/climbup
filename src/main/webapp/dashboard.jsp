<c:forEach var="task" items="${taskList}">
  <tr>
    <td>${task.title}</td>
    <td>${task.dueDate}</td>
    <td>${task.status}</td>
  </tr>
</c:forEach>


<c:if test="${empty taskList}">
    <tr><td colspan="3">No tasks found for this filter.</td></tr>
</c:if>

<a href="DashboardServlet?filter=today">Today</a> |
<a href="DashboardServlet?filter=upcoming">Upcoming</a> |
<a href="DashboardServlet?filter=missed">Missed</a>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

