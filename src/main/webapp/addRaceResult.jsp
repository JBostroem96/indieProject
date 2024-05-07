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
        <div class="d-flex flex-column gap-5 fw-bold border rounded" id="add_race_result">
            <table class="display" cellspacing="=0" width="100%">
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
                            <c:if test="${user.role == 'admin' || user.role == 'user'}">
                                <td>
                                    <form action="addRaceResult" class="form-inline" method="get">
                                        <button type="submit" name="id" class="btn btn-primary bg-primary btn-sm" value="${race.id}">Add</button>
                                    </form>
                                </td>
                            </c:if>
                            <td>
                                <form action="viewRaceResult" class="form-inline" method="get">
                                    <button type="submit" name="id" class="btn btn-success bg-success btn-sm" value="${race.id}">View</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
<c:import url="footer.jsp"/>
</html>