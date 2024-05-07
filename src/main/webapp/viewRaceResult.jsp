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
        <div class="d-flex flex-column gap-5 fw-bold border rounded" id="add_race_result">
            <table class="display" cellspacing="=0" width="100%">
                <thead>
                    <th>Race id</th>
                    <th>Overall Place</th>
                    <th>Division Place</th>
                    <th>Team Name</th>
                    <th>Division</th>
                    <th>CP</th>
                    <th>Late Penalty</th>
                    <th>Total Time</th>
                </thead>
                <tbody>
                    <c:forEach var="team_race" items="${team_races}">
                        <tr>
                            <td class="text-black">${team_race.race_id}</td>
                            <td class="text-black">${team_race.overallPlace}</td>
                            <td class="text-black">${team_race.divisionPlace}</td>
                            <td class="text-black">${team_race.team.name}</td>
                            <td class="text-black">${team_race.team.division}</td>
                            <td class="text-black">${team_race.cp}</td>
                            <td class="text-black">${team_race.latePenalty}</td>
                            <td class="text-black">${team_race.totalTime}</td>
                            <td>
                                <form action="reportResultDisplay" class="form-inline" method="get">
                                    <button type="submit" name="id" class="btn btn-primary bg-danger" value="${team_race.id}">Report</button>
                                </form>
                            </td>
                            <c:if test="${user.role == 'admin' || user.role == 'user'}">
                                <td>
                                    <form action="editRaceResultDisplay" class="form-inline" method="get">
                                        <button type="submit" name="id" class="btn btn-primary bg-success" value="${team_race.id}">Edit</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="deleteRaceResultDisplay" class="form-inline" method="get">
                                        <button type="submit" name="id" class="btn btn-primary bg-danger" value="${team_race.id}">Delete</button>
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


