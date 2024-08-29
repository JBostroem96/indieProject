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
                <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>You are editing:</em></h1>
                <c:if test="${!empty resultUpdated}">
                    <p class="text-success text-center fw-bold">${resultUpdated}</p>
                </c:if>
                <div class="d-flex flex-column gap-5 fw-bold border rounded" id="form">
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
                                    <td class="text-black">${editedRaceResult.team.name}</td>
                                    <td class="text-black">${editedRaceResult.team.division}</td>
                                    <td class="text-black">${editedRaceResult.cp}</td>
                                    <td class="text-black">${editedRaceResult.latePenalty}</td>
                                    <td class="text-black">${editedRaceResult.totalTime}</td>
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
                        <c:if test="${!empty missingField}"><p class="text-danger fw-bold">${missingField}</p></c:if>
                        <c:if test="${!empty e}"><p class="text-danger fw-bold">Something went wrong!</p></c:if>
                        <div class="search text-white p-5 border rounded" id="edit_race_result">
                            <div class="form-group">
                                <label for="time">Total Time (in minutes)</label>
                                <input type="number" class="form-control" name="time" step="0.01" id="time" required>
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
                        </div>
                        <c:choose>
                            <c:when test="${!empty editedRaceResult}">
                                <input type="hidden" name="race_id" value="${editedRaceResult.race_id}">
                                <button type="submit" name="id" class="btn btn-success bg-success" value="${editedRaceResult.id}">Edit Race Result</button>
                            </c:when>
                            <c:otherwise>
                                <input type="hidden" name="race_id" value="${team_race.race_id}">
                                <button type="submit" name="id" class="btn btn-success bg-success" value="${team_race.id}">Edit Race Result</button>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</body>
<c:import url="footer.jsp"/>
</html>
