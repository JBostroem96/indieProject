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
        <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>Race Result</em></h1>
        <c:if test="${!empty raceResultsDeleted}">
            <p class="text-success fw-bold">${raceResultsDeleted}</p>
        </c:if>
        <c:if test="${!empty e}">
            <p class="text-danger fw-bold">${e}</p>
        </c:if>
        <div class="d-flex flex-column gap-5 fw-bold border rounded">
            <table class="display" cellspacing="=0" width="100%">
                <thead>
                    <th>Name</th>
                    <th>Length</th>
                    <th>Date</th>
                </thead>
                <tbody>
                    <c:forEach var="race" items="${displayEntries}">
                        <tr>
                            <td class="text-black">${race.name}</td>
                            <td class="text-black">${race.length}</td>
                            <td class="text-black">${race.date}</td>
                            <c:if test="${user.role == 'ADMIN'}">
                                <td>
                                    <form action="addRaceResultDisplay" class="form-inline" method="get">
                                        <button type="submit" name="id" class="btn btn-primary bg-primary btn-sm" value="${race.id}">Add</button>
                                    </form>
                                </td>
                            </c:if>
                            <td>
                                <form action="viewRaceResult" class="form-inline" method="get">
                                    <button type="submit" name="id" class="btn btn-success bg-success btn-sm" value="${race.id}">View</button>
                                </form>
                            </td>
                            <c:if test="${user.role == 'ADMIN'}">
                                <c:if test="${entry[race.id]}">
                                    <td>
                                        <form action="deleteAllRaceResults" class="form-inline" method="post" onsubmit="return confirmDeletion();">
                                            <button type="submit" name="id" class="btn btn-danger bg-danger btn-sm" value="${race.id}">Delete Results</button>
                                        </form>
                                    </td>
                                </c:if>
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