<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<c:import url="head.jsp"/>
<body>

    <%@include file="nav.jsp"%>
    <div class="container bg-white">
        <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>You are adding results to this race:</em></h1>
        <c:if test="${!empty resultAdded}">
            <p class="text-success text-center fw-bold">${resultAdded}</p>
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
                        <td class="text-black">${entry.name}</td>
                        <td class="text-black">${entry.length}</td>
                        <td class="text-black">${entry.date}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="d-flex flex-column gap-5 fw-bold">
            <form action="addRaceResult" class="form-inline" method="POST">
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
                        <c:forEach var="team" items="${displayEntries}">
                            <option value="${team.id}">${team.name}</option>
                        </c:forEach>
                    </select>
                    <c:if test="${!empty message}">
                        <p class="text-danger small">${message}</p>
                    </c:if>
                </div>
                <button type="submit" name="id" class="btn btn-success bg-success" value="${entry.id}">Add Results</button>
            </form>
        </div>
    </div>
</body>
<c:import url="footer.jsp"/>
</html>
