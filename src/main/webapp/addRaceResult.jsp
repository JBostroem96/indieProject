<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="nav.jsp"%>
<script type="text/javascript" class="init">

    $(document).ready(function () {
        $('#raceTable').DataTable();
    });
</script>
<html>
<body>
<div class="container bg-white">
    <div class="d-flex flex-column gap-5 fw-bold">
        <h2 class="mt-5 text-center text-muted fw-bold"><em>Add Race Result</em></h2>
        <form action="addRace" class="form-inline" method="POST">
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
                        <td>

                            <form action="addResult" class="form-inline" method="get">
                                <button type="submit" name="id" class="btn btn-primary bg-success" value="${race.id}">Add</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
    </div>
</div>
</body>
<%@include file="footer.jsp"%>
</html>