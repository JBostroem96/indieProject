<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="head.jsp"/>

<script type="text/javascript" class="init">

    $(document).ready(function () {
        $('#addedRaceResult').DataTable();
    });
</script>
<html>
<body>
<%@include file="nav.jsp"%>
<div class="container bg-white">
    <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>Results:</em></h1>
    <div class="d-flex flex-column gap-5 fw-bold border rounded" id="add_race_result">
        <table id="addedRaceResult" class="display" cellspacing="=0" width="100%">

            <thead>
            <th>Name</th>
            <th>Division</th>
            </thead>
            <tbody>
            <c:forEach var="team" items="${teams}">
                <tr>
                    <td class="text-black">${team.name}</td>
                    <td class="text-black">${team.division}</td>

                    <c:if test="${user.role == 'admin' || user.role == 'user'}">
                        <td>
                            <form action="editRace" class="form-inline" method="get">
                                <button type="submit" name="id" class="btn btn-primary bg-success" value="${team.id}">Edit</button>
                            </form>
                        </td>
                        <td>
                            <form action="deleteRace" class="form-inline" method="get">
                                <button type="submit" name="id" class="btn btn-primary bg-danger" value="${team.id}">Delete</button>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
<c:import url="footer.jsp"/>
</html>
<br>

