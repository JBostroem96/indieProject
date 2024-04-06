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
        <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>Add Race Result</em></h1>
            <div class="d-flex flex-column gap-5 fw-bold border rounded" id="add_race_result">
            <table id="raceTable" class="display" cellspacing="=0" width="100%">
                <thead>
                <th>Name</th>
                <th>Length</th>
                <th>Date</th>
                </thead>
                <tbody>
                <c:forEach var="race" items="${races}">
                    <tr>
                        <td class="text-black">${race.name}</td>
                        <td class="text-black">${race.length}</td>
                        <td class="text-black">${race.date}</td>
                        <td>
                            <form action="addRaceResult" class="form-inline" method="get">
                                <button type="submit" name="id" class="btn btn-primary bg-success" value="${race.id}">Add</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

    </div>
</div>
</body>
<%@include file="footer.jsp"%>
</html>