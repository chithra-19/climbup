<c:forEach var="task" items="${taskList}">
  <tr>
    <td>${task.title}</td>
    <td>${task.dueDate}</td>
    <td>${task.status}</td>
  </tr>
</c:forEach>


<a href="DashboardServlet?filter=today">Today</a> |
<a href="DashboardServlet?filter=upcoming">Upcoming</a> |
<a href="DashboardServlet?filter=missed">Missed</a>
