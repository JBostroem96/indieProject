<%@include file="taglib.jsp"%>
<c:set var="title" value="Search Results"/>
<%@include file="head.jsp"%>
<%@include file="nav.jsp"%>
<script type="text/javascript" class="init">

    $(document).ready(function () {
        $('#teamTable').DataTable();
    });
</script>
<html>
<body>
<div class="container-fluid">
    <h1 class="text-center text-muted">You Added:</h1>
    <c:if test="${empty team}">
        <h2 class="text-center text-muted">The team couldn't be added</h2>
    </c:if>
    <table id="teamTable" class="display" cellspacing="=0" width="100%">
        <thead>
        <th>Name</th>
        <th>Division</th>
        </thead>
        <tbody>
            <tr>
                <td>${team.name}</td>
                <td>${team.division}</td>
            </tr>
        </tbody>
    </table>
</div>
</body>
<%@include file="footer.jsp"%>
</html>
