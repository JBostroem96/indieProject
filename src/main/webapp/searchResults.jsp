<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<c:import url="head.jsp"/>
<script type="text/javascript" class="init">

    $(document).ready(function () {
        $('.display').DataTable();
    });
</script>
<body>
    <%@include file="nav.jsp"%>
    <div class="container bg-white">
        <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>Results:</em></h1>
        <c:if test="${!empty entryDeleted}">
            <p class="text-success fw-bold">${entryDeleted}</p>
        </c:if>
        <c:if test="${!empty e}">
            <p class="text-danger fw-bold">${e}</p>
        </c:if>
        <div class="d-flex flex-column gap-5 fw-bold border rounded">
            <table class="display" cellspacing="=0" width="100%">
                <c:choose>
                    <c:when test="${!empty races}">
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
                                    <c:if test="${user.role == 'ADMIN'}">
                                        <td>
                                            <form action="editRaceDisplay" class="form-inline" method="get">
                                                <button type="submit" name="id" class="btn btn-success bg-success btn-sm" value="${race.id}">Edit</button>
                                            </form>
                                        </td>
                                        <td>
                                            <form action="deleteRace" class="form-inline" method="post" onsubmit="return confirmDeletion();">
                                                <button type="submit" name="id" class="btn btn-danger bg-danger btn-sm" value="${race.id}">Delete</button>
                                            </form>
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </c:when>
                    <c:otherwise>
                        <thead>
                            <th>Name</th>
                            <th>Division</th>
                        </thead>
                        <tbody>
                            <c:forEach var="team" items="${teams}">
                                <tr>
                                    <td class="text-black">${team.name}</td>
                                    <td class="text-black">${team.division}</td>
                                    <c:if test="${user.role == 'ADMIN'}">
                                        <td>
                                            <form action="editTeamDisplay" class="form-inline" method="get">
                                                <button type="submit" name="id" class="btn btn-success bg-success btn-sm" value="${team.id}">Edit</button>
                                            </form>
                                        </td>
                                        <td>
                                            <form action="deleteTeam" class="form-inline" method="post" onsubmit="return confirmDeletion();">
                                                <button type="submit" name="id" class="btn btn-danger bg-danger btn-sm" value="${team.id}">Delete</button>
                                            </form>
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </c:otherwise>
                </c:choose>
            </table>
        </div>
    </div>
</body>
<c:import url="footer.jsp"/>
</html>


