<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="nav.jsp"%>
<html>
<div class="container bg-white">
    <body>
        <c:choose>
            <c:when test="${!empty deletedRace}">
                <h1 class="text-success text-center">You have successfully deleted the race!</h1>
            </c:when>
            <c:otherwise>
                <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>You're about to delete this Race:</em></h1>
                <form action="deleteRaceById" class="form-inline" method="POST">
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
                    <button type="submit" name="id" class="btn btn-primary bg-danger mt-2" value="${race.id}">Delete Race</button>
                </form>
        </c:otherwise>
    </c:choose>
    </body>
    <%@include file="footer.jsp"%>
</html>
