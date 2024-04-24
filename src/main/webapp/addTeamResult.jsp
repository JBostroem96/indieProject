<%@include file="taglib.jsp"%>
<c:set var="title" value="Search Results"/>
<%@include file="head.jsp"%>
<%@include file="nav.jsp"%>
<script type="text/javascript" class="init">

    $(document).ready(function () {
        $('#result').DataTable();
    });
</script>
<html>
<body>
<div class="container-fluid">
    <c:choose>
        <c:when test="${!empty message}">
            <h1 class="text-center text-muted">${message}</h1>
            <%@include file="footer.jsp"%>
            </body>
            </html>
        </c:when>
        <c:otherwise>
            <h1 class="text-center text-muted">You Added:</h1>
            <table id="result" class="display" cellspacing="=0" width="100%">
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
        </c:otherwise>
    </c:choose>

