<%--
  Created by IntelliJ IDEA.
  User: pingi
  Date: 3/15/2024
  Time: 12:40 PM
  To change this template use File | Settings | File Templates.
--%>

<nav class="navbar navbar-expand-md navbar-dark bg-dark"
     data-bs-theme="yellow">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">IndieProject</a>
        <button class="navbar-toggler" type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbarContent"
                aria-controls="navbarContent"
                aria-expanded="false" aria-label="Toggle Navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarContent">
            <ul class="nav navbar-nav">
                <li class="nav-item"><a class="nav-link" href = "index.jsp"><em>Home</em></a></li>
                <li class="nav-item"><a class="nav-link" href = "AddRaceDisplay"><em>Add Race</em></a></li>
                <li class="nav-item"><a class="nav-link" href = "FindRaceDisplay"><em>Races</em></a></li>
                <li class="nav-item"><a class="nav-link" href = "addRaceResultDisplay"><em>Add Race Result</em></a></li>
                <li class="nav-item"><a class="nav-link" href = "addTeamDisplay"><em>Add a Team</em></a></li>
                <li class="nav-item"><a class="nav-link" href = "logout"><em>Logout</em></a></li>
                <c:if test="${empty userName}">
                    <li class="nav-item"><a class="nav-link" href = "logIn"><em>Login or Sign Up</em></a></li>
                </c:if>
                <c:if test="${!empty userName}">
                    <li class="nav-item"><a class="nav-link" href = "logIn"><em>${userName}</em></a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

