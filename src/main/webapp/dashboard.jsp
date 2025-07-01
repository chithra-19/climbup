<c:forEach var="task" items="${taskList}">
  <tr>
    <td>${task.title}</td>
    <td>${task.dueDate}</td>
    <td>${task.status}</td>
    <td>
      <c:if test="${task.status == 'PENDING'}">
        <form action="task/updateStatus" method="post" style="display:inline;">
          <input type="hidden" name="id" value="${task.id}" />
          <input type="hidden" name="action" value="complete" />
          <button class="btn btn-success btn-sm">✅ Complete</button>
        </form>

        <form action="task/updateStatus" method="post" style="display:inline;">
          <input type="hidden" name="id" value="${task.id}" />
          <input type="hidden" name="action" value="miss" />
          <button class="btn btn-danger btn-sm">❌ Miss</button>
        </form>
      </c:if>
    </td>
  </tr>
</c:forEach>

<div class="card text-white bg-success mb-3" style="max-width: 18rem;">
  <div class="card-header">7-Day Productivity Score</div>
  <div class="card-body">
    <h5 class="card-title">${score}%</h5>
  </div>
</div>
<div class="card text-center">
  <div class="card-body">
    <h5 class="card-title">🔥 Streak</h5>
    <p class="card-text"><strong>${streak}</strong> days</p>
  </div>
</div>


