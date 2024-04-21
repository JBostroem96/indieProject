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
        <form action="editRaceById" class="form-inline" method="POST">
            <div class="search text-white p-5 border rounded" id="add_race_form">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" class="form-control" name="name" id="name">
                </div>
                <div class="form-group">
                    <label for="length">Length</label>
                    <input type="number" class="form-control" name="length" id="length">
                </div>
                <label class="fw-bold" for="team">Team</label>
                <select class="form-control" size="3" name="team" id="team">
                    <c:forEach var="team" items="${team}">
                        <option value="${team.name}">${team.name}</option>
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
