<%@include file="taglib.jsp"%>
<c:set var="title" value="Search Results"/>
<%@include file="head.jsp"%>
<%@include file="nav.jsp"%>
<script type="text/javascript" class="init">

    $(document).ready(function () {
        $('#categoryTable').DataTable();
    });
</script>
<html>
<body>
<div class="container-fluid">
    <h1 class="text-center text-muted">You Added:</h1>
    <c:if test="${empty category}">
        <h2 class="text-center text-muted">The category couldn't be added</h2>
    </c:if>
    <table id="categoryTable" class="display" cellspacing="=0" width="100%">
        <thead>
        <th>Category</th>
        </thead>
        <tbody>
        <tr>
            <td>${category.division}</td>
        </tr>
        </tbody>
    </table>
</div>
</body>
<%@include file="footer.jsp"%>
</html>
