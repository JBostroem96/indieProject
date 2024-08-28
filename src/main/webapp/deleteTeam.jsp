<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<c:import url="head.jsp"/>
<body>
    <c:choose>
        <c:when test="${user.role == null}">
            <p class="text-center fw-bold text-danger">You do not have permission to this page</p>
        </c:when>
        <c:otherwise>
            <%@include file="nav.jsp"%>
            <div class="container bg-white">
                <c:choose>
                    <c:when test="${!empty deletedEntry}">
                        <p class="text-success text-center fw-bold mt-4">You have successfully deleted the team!</p>
                    </c:when>
                    <c:otherwise>
                        <p class="mt-5 text-center fw-bold mb-5 text-danger"><em>WARNING: Deleting this team will remove all results associated with it!</em></p>
                        <form action="deleteTeamById" class="form-inline text-center" method="POST">
                            <div class="d-flex flex-column gap-5 fw-bold border rounded">
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
            </div>
        </c:otherwise>
    </c:choose>
</body>
<c:import url="footer.jsp"/>
</html>
