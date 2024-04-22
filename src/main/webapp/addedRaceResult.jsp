<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="nav.jsp"%>
<html>
<body>
<div class="container bg-white">
    <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>You Added:</em></h1>
    <div class="d-flex flex-column gap-5 fw-bold border rounded" id="added_race">
        <table id="addRace" class="display" cellspacing="=0" width="100%">
            <thead>
            <th>race result</th>
            </thead>
            <tbody>
            <tr class="bg-white">
                <td class="text-black">${teamRaceResult.race}</td>
                <td class="text-black">${teamRaceResult.team}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
<%@include file="footer.jsp"%>
</html>