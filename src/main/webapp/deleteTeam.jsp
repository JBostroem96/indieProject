<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<c:import url="head.jsp"/>
<body>
<%@include file="nav.jsp"%>
<div class="container bg-white">

    <c:choose>
    <c:when test="${!empty deletedTeam}">
    <h1 class="text-success text-center">You have successfully deleted the team!</h1>
    </c:when>
    <c:otherwise>
    <h1 class="mt-5 text-center fw-bold mb-5 text-danger"><em>WARNING: Deleting this team will remove all results associated with it!</em></h1>
    <form action="deleteTeamById" class="form-inline" method="POST">
        <div class="d-flex flex-column gap-5 fw-bold border rounded" id="added_race">
            <table id="addRace" class="display" cellspacing="=0" width="100%">
                <thead>
                <th>Name</th>
                <th>Division</th>
                </thead>
                <tbody>
                <tr class="bg-white">
                    <td class="text-black">${team.name}</td>
                    <td class="text-black">${team.division}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <button type="submit" name="id" class="btn btn-primary bg-danger mt-2" value="${team.id}">Delete Team</button>
    </form>
    </c:otherwise>
    </c:choose>
</body>
<c:import url="footer.jsp"/>
</html>
