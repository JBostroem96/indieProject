<%--
  Created by IntelliJ IDEA.
  User: pingi
  Date: 3/15/2024
  Time: 12:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav>
    <ul class="bg-primary text-white d-flex justify-content-around fw-bold p-4 list-unstyled">

        <a class="text-white" href = "index.jsp">Home</a>
        <li>Add Race</li>
        <a class="text-white" href = "FindRaceDisplay">Races</a>
        <li>About</li>
        <li>Add Results</li>
        <c:choose>
            <c:when test="${empty userName}">
                <a class="text-white" href = "logIn">Log in or Sign up</a>
            </c:when>
            <c:otherwise>
                <p class="sm text-muted">User: ${userName}</p>
            </c:otherwise>
        </c:choose>

    </ul>
</nav>
