<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="nav.jsp"%>
<html>
<body>
<div class="container bg-white">
    <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>Your edited Race:</em></h1>
    <div class="d-flex flex-column gap-5 fw-bold border rounded" id="added_race">
        <table id="addRace" class="display" cellspacing="=0" width="100%">
            <thead>
            <th>Name</th>
            <th>Length</th>
            <th>Date</th>
            </thead>
            <tbody>
            <tr class="bg-white">
                <td class="text-black">${race.name}</td>
                <td class="text-black">${race.length}</td>
                <td class="text-black">${race.date}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>