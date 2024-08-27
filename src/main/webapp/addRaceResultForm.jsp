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
                <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>You are adding results to this race:</em></h1>
                <c:if test="${!empty teamRaceResult}">
                    <p class="text-success text-center fw-bold">You have successfully added a new team!</p>
                </c:if>
                <div class="d-flex flex-column gap-5 fw-bold border rounded" id="added_race">
                    <table id="addRace" class="display" cellspacing="=0" width="100%">
                        <thead>
                        <th>Name</th>
                        <th>Length</th>
                        <th>Date</th>
                        </thead>
                        <tbody>
                        <tr class="bg-white">
                            <td class="text-black">${race.name}</td>
                            <td class="text-black">${race.length}</td>
                            <td class="text-black">${race.date}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="d-flex flex-column gap-5 fw-bold">
                    <form action="addRaceResultById" class="form-inline" method="POST">
                        <c:if test="${!empty missingField}"><p class="text-danger fw-bold">${missingField}</p></c:if>
                        <c:if test="${!empty e}"><p class="text-danger fw-bold">Something went wrong!</p></c:if>
                        <div class="search text-white p-5 border rounded" id="add_result">
                            <div class="form-group">
                                <label for="time">Total Time (in minutes.seconds)</label>
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
                        <button type="submit" name="race_id" class="btn btn-success bg-success" value="${race.id}">Add Results</button>
                    </form>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</body>
<c:import url="footer.jsp"/>
</html>
