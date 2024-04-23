<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="nav.jsp"%>
<script type="text/javascript" class="init">

    $(document).ready(function () {
        $('#addedRaceResult').DataTable();
    });
</script>
<html>
<body>
<div class="container bg-white">
    <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>Results:</em></h1>
    <div class="d-flex flex-column gap-5 fw-bold border rounded" id="add_race_result">
        <table id="addedRaceResult" class="display" cellspacing="=0" width="100%">
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
                        <td class="text-black">${team_race.overall_place}</td>
                        <td class="text-black">${team_race.division_place}</td>
                        <td class="text-black">${team_race.team.name}</td>
                        <td class="text-black">${team_race.team.division}</td>
                        <td class="text-black">${team_race.cp}</td>
                        <td class="text-black">${team_race.late_penalty}</td>
                        <td class="text-black">${team_race.total_time}</td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
    </div>
</div>
</body>
<%@include file="footer.jsp"%>
</html>
<br>

