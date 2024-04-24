<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="nav.jsp"%>
<html>
<body>
<div class="container bg-white">
    <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>You are editing:</em></h1>
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
                        <input type="text" class="form-control" name="name" id="name" required>
                    </div>
                    <div class="form-group">
                        <label for="length">Length</label>
                        <input type="number" class="form-control" name="length" id="length" required>
                    </div>
                    <div class="form-group">
                        <label for="date">Date</label>
                        <input type="date" class="form-control" name="date" id="date" required>
                    </div>
                </div>
                <button type="submit" name="id" class="btn btn-primary bg-success" value="${race.id}">Edit Race</button>
            </form>
        </div>
    </div>
</div>
</body>
<%@include file="footer.jsp"%>
</html>
