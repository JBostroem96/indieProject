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
    <h1 class="text-center text-muted"><em>You are about to delete this race:</em></h1>
    <table id="raceTable" class="display" cellspacing="=0" width="100%">
        <thead>
        <th>Name</th>
        <th>Length</th>
        <th>Date</th>
        </thead>
        <tbody>
        <tr>
            <td>${race.name}</td>
            <td>${race.length}</td>
            <td>${race.date}</td>
        </tr>
        </tbody>
    </table>

    <div class="d-flex flex-column gap-5 fw-bold">
        <form action="deleteRaceById" class="form-inline" method="POST">
            <button type="submit" name="id" class="btn btn-primary bg-success" value="${race.id}">Delete Race</button>
        </form>

    </div>
</div>
</body>
<%@include file="footer.jsp"%>
</html>
