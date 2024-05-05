<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<c:import url="head.jsp"/>
<body>
<%@include file="nav.jsp"%>
<div class="container bg-white">
    <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>You are editing:</em></h1>
    <c:if test="${!empty editedRaceResult}">
        <p class="text-success text-center fw-bold">You have successfully edited the race!</p>
    </c:if>
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
            <c:choose>
                <c:when test="${!empty editedRaceResult}">
                    <tr class="bg-white">
                        <td class="text-black">${team_race.team.name}</td>
                        <td class="text-black">${team_race.team.division}</td>
                        <td class="text-black">${team_race.cp}</td>
                        <td class="text-black">${team_race.latePenalty}</td>
                        <td class="text-black">${team_race.totalTime}</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr class="bg-white">
                        <td class="text-black">${team_race.team.name}</td>
                        <td class="text-black">${team_race.team.division}</td>
                        <td class="text-black">${team_race.cp}</td>
                        <td class="text-black">${team_race.latePenalty}</td>
                        <td class="text-black">${team_race.totalTime}</td>
                    </tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </div>
    <div class="d-flex flex-column gap-5 fw-bold">
        <form action="editRaceResultById" class="form-inline" method="POST">
            <div class="form-group">
                <label for="time">Total Time (in minutes)</label>
                <input type="number" class="form-control" name="time" id="time" required>
            </div>
            <div class="form-group">
                <label for="cp">CP</label>
                <input type="number" class="form-control" name="cp" id="cp" required>
            </div>
            <div class="form-group">
                <label for="penalty">Late Penalty</label>
                <input type="number" class="form-control" name="penalty" id="penalty" required>
            </div>
            <label class="fw-bold" for="team">Team</label>
            <select class="form-control" size="3" id="team" name="team" required>
                <c:forEach var="team" items="${team}">
                    <option value="${team.id}">${team.name}</option>
                </c:forEach>
            </select>
            <c:if test="${!empty message}">
                <p class="text-danger small">${message}</p>
            </c:if>
            <c:choose>
                <c:when test="${!empty editedRaceResult}">
                    <button type="submit" name="id" class="btn btn-primary bg-success" value="${editedRaceResult.id}">Edit Race Result</button>
                </c:when>
                <c:otherwise>
                    <button type="submit" name="id" class="btn btn-primary bg-success" value="${team_race.id}">Edit Race Result</button>
                </c:otherwise>
            </c:choose>
        </form>
    </div>
</div>
</body>
<c:import url="footer.jsp"/>
</html>
