<%@include file="taglib.jsp"%>
<c:set var="title" value="Search Results"/>
<%@include file="head.jsp"%>
<%@include file="nav.jsp"%>
<script type="text/javascript" class="init">

    $(document).ready(function () {
        $('#raceTable').DataTable();
    });
</script>
<html>
<body>
<div class="container-fluid">
    <h1 class="text-center text-muted"><em>Editing the following race:</em></h1>
    <table id="raceTable" class="display" cellspacing="=0" width="100%">
        <thead>
        <th>Name</th>
        <th>Length</th>
        <th>Date</th>
        </thead>
        <tbody>
        <c:forEach var="race" items="${races}">
            <tr>
                <td>${race.name}</td>
                <td>${race.length}</td>
                <td>${race.date}</td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
        <div class="d-flex flex-column gap-5 fw-bold">
            <h2 class="mt-5 text-center text-muted fw-bold"><em>Edit Race</em></h2>
            <form action="addRace" class="form-inline" method="POST">
                <div class="search bg-danger text-white p-5 border rounded">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" class="form-control" name="name" id="name">
                    </div>
                    <div class="form-group">
                        <label for="length">Length</label>
                        <input type="number" class="form-control" name="length" id="length">
                    </div>
                    <div class="form-group">
                        <label for="date">Date</label>
                        <input type="date" class="form-control" name="date" id="date">
                    </div>
                </div>
                <div class="buttons mt-3">
                    <button type="submit" name="submit" class="btn btn-primary bg-success" value="submit">Submit</button>
                    <button type="reset" name="clear" class="btn btn-primary bg-danger" value="clear">Clear</button>
                </div>
            </form>
        </div>
    </div>
</body>
<%@include file="footer.jsp"%>
</html>
