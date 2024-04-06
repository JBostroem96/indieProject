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
    <h1 class="text-center text-muted"><em>Your Deletion was successful!</em></h1>
</div>
</body>
<%@include file="footer.jsp"%>
</html>