<%@include file="taglib.jsp"%>
<c:set var="title" value="Search Results"/>
<%@include file="head.jsp"%>
<script type="text/javascript" class="init">

    $(document).ready(function () {
        $('#userTable').DataTable();
    });
</script>
<html>
<body>
<div class="container-fluid">
    <h1 class="text-center text-muted">Search Results:</h1>
    <c:if test="${empty races}">
        <h2 class="text-center text-muted">No Users were found</h2>
    </c:if>
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
                <td></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
