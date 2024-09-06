<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<c:import url="head.jsp"/>
<body>
    <%@include file="nav.jsp"%>
    <div class="container bg-white">
        <h1 class="text-black fw-bold text-center mt-4"><em>Profile for ${user.userName}</em></h1>
        <div class="d-flex flex-column gap-5 fw-bold border rounded" id="form">
            <table id="addRace" class="display" cellspacing="=0" width="100%">
                <thead>
                    <th>Name</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Role</th>
                </thead>
                <tbody>
                    <tr class="bg-white">
                        <td class="text-black">${user.name}</td>
                        <td class="text-black">${user.userName}</td>
                        <td class="text-black">${user.email}</td>
                        <td class="text-black">${user.role}</td>
                    </tr>
                </tbody>
            </table>
            <br>
            <h2 class="text-center">Results Submitted</h2>
            <table class="display" cellspacing="=0" width="100%">
                <thead>
                    <th>Race Name</th>
                    <th>Overall Place</th>
                    <th>Division Place</th>
                    <th>Team Name</th>
                    <th>Time</th>
                    <th>CP</th>
                    <th>Late Penalty</th>
                </thead>
                <tbody>
                    <c:forEach var="result" items="${results}">
                        <tr class="bg-white">
                            <td class="text-black">${result.race.name}</td>
                            <td class="text-black">${result.overallPlace}</td>
                            <td class="text-black">${result.divisionPlace}</td>
                            <td class="text-black">${result.team.name}</td>
                            <td class="text-black">${result.totalTime}</td>
                            <td class="text-black">${result.cp}</td>
                            <td class="text-black">${result.latePenalty}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <form action="deleteUser" class="form-inline" method="POST" onsubmit="return confirmDeletion();">
            <button type="submit" name="id" class="btn btn-danger bg-danger">DELETE ACCOUNT</button>
        </form>
    </div>
</body>
<c:import url="footer.jsp"/>
</html>