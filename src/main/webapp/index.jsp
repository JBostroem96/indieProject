<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<c:import url="head.jsp"/>

<body>

    <%@include file="nav.jsp"%>
    <c:choose>
        <c:when test="${empty user}">
            <h1 class="text-black fw-bold text-center mt-4"><em>Welcome!</em></h1>
        </c:when>
        <c:otherwise>
                <h1 class="text-black fw-bold text-center mt-4"><em>Welcome ${user.userName}!</em></h1>
        </c:otherwise>
    </c:choose>
    <p class="text-black p-3 fw-italics">https://www.wisconsinadventureracingseries.com/ is a racing series that is free and offers prizes to winners. They are present in WI, IL and MN, and offer a wide variety of different racing activities, such as biking, paddling, running or trekking, not to mention other challenges. However, it is mostly not a solo sport, as team generally form for the premier division. That said, there are races that do allow for solo racing.

        With this description, it's understandable that there needs to be a way to store the data involved. They happen to store a wide variety of data, such as the rank, division, team name, total points out of the 500 max race points, and much more, into a spreadsheet. Currently, this process is completely manual, and thus requires hands-on work that could otherwise be automated.

        There are many things this application needs to be able to track, and this is because some of the data involved is affected by other factors, specifically, the rules. For instance, there is a maximum of 500 points for the races, but the maximum is determined by the number of hours in the races themselves. For example, 3-6 hour races allow for a total of 50 points, while 8-12 hour races allow for a total of 100 points.

        This application's goal is to automate this heavy data processing. It would spare a lot of work and effort that goes into manually entering all these different values, not to mention the tedium involved. In addition, manual input is prone to user error, especially when dealing with a lot of data, not to mention having to manage that kind of quantity. By implementing an automatic system, we are streamlining the process where there is less room for user error.</p>
</body>
<c:import url="footer.jsp"/>
</html>