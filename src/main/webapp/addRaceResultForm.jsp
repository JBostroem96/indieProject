<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="nav.jsp"%>
<html>
<body>
<div class="container bg-white">
    <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>You are adding results to this race:</em></h1>
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
            <div class="search text-white p-5 border rounded" id="add_race_form">
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
                    <c:forEach var="teams" items="${team}">
                        <option value="${teams.id}">${teams.name}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" name="id" class="btn btn-primary bg-success" value="${race.id}">Add Results</button>
        </form>
    </div>
</div>
</body>
<%@include file="footer.jsp"%>
</html>
