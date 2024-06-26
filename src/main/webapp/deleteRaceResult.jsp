<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<c:import url="head.jsp"/>
<body>
    <c:choose>
        <c:when test="${user.role == null}">
            <p class="text-center fw-bold text-danger">You do not have permission to this page</p>
        </c:when>
        <c:otherwise>
        <%@include file="nav.jsp"%>
            <div class="container bg-white">
                <c:choose>
                    <c:when test="${!empty deletedRaceResult}">
                        <p class="text-success text-center fw-bold mt-4">You have successfully deleted the result!</p>
                    </c:when>
                    <c:otherwise>
                        <p class="mt-5 text-center fw-bold mb-5 text-danger"><em>WARNING: Deleting this result will remove it from the ranks!</em></p>
                        <form action="deleteRaceResultById" class="form-inline text-center" method="POST">
                            <div class="d-flex flex-column gap-5 fw-bold border rounded" id="added_race">
                                <table id="addRace" class="display" cellspacing="=0" width="100%">
                                    <thead>
                                        <th>team name</th>
                                        <th>team division</th>
                                        <th>cp</th>
                                        <th>latePenalty</th>
                                        <th>Total Time</th>
                                    </thead>
                                    <tbody>
                                    <tr class="bg-white">
                                        <td class="text-black">${team_race.team.name}</td>
                                        <td class="text-black">${team_race.team.division}</td>
                                        <td class="text-black">${team_race.cp}</td>
                                        <td class="text-black">${team_race.latePenalty}</td>
                                        <td class="text-black">${team_race.totalTime}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <button type="submit" name="id" class="btn btn-primary bg-danger mt-2" value="${team_race.id}">Delete Result</button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:otherwise>
    </c:choose>
</body>
<c:import url="footer.jsp"/>
</html>
