<%--
  Created by IntelliJ IDEA.
  User: pingi
  Date: 3/15/2024
  Time: 12:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav>
    <ul class="bg-danger text-black d-flex justify-content-around fw-bold p-5 list-unstyled">

        <a class="text-black" href = "index.jsp"><em>Home</em></a>
        <a class="text-black" href = "AddRaceDisplay"><em>Add Race</em></a>
        <a class="text-black" href = "FindRaceDisplay"><em>Races</em></a>
        <li>About</li>
        <li>Add Results</li>
        <c:choose>
            <c:when test="${empty userName}">
                <a class="text-black" href = "logIn"><em>Log in or Sign up</em></a>
            </c:when>
            <c:otherwise>
                <p class="sm text-muted">User: ${userName}</p>
            </c:otherwise>
        </c:choose>

    </ul>
</nav>

