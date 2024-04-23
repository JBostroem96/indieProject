<%--
  Created by IntelliJ IDEA.
  User: pingi
  Date: 4/20/2024
  Time: 4:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="d-flex flex-column gap-5 fw-bold border rounded" id="add_race_result">
  <table id="addedRaceResult" class="display" cellspacing="=0" width="100%">
    <thead>
      <th>Name</th>
      <th>Length</th>
      <th>Date</th>
    </thead>
    <tbody>
    <c:forEach var="race" items="${races}">
      <tr>
        <td class="text-black">${race.name}</td>
        <td class="text-black">${race.length}</td>
        <td class="text-black">${race.date}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
