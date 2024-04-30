<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="head.jsp"/>
<%@include file="nav.jsp"%>
<html>
<body>
<div class="container bg-white">
    <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>You are editing:</em></h1>
    <c:if test="${!empty editedTeam}">
        <h2 class="text-success text-center">You have successfully edited the team!</h2>
    </c:if>
    <div class="d-flex flex-column gap-5 fw-bold border rounded" id="added_race">
        <table id="addRace" class="display" cellspacing="=0" width="100%">
            <thead>
            <th>Name</th>
            <th>Division</th>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${!empty editedTeam}">
                    <tr class="bg-white">
                        <td class="text-black">${editedTeam.name}</td>
                        <td class="text-black">${editedTeam.division}</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr class="bg-white">
                        <td class="text-black">${team.name}</td>
                        <td class="text-black">${team.division}</td>
                    </tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </div>
    <div class="d-flex flex-column gap-5 fw-bold">
        <form action="editTeamById" class="form-inline" method="POST">
            <div class="search text-white p-5 border rounded" id="add_race_form">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" class="form-control" name="name" id="name" required>
                    <c:if test="${!empty alreadyExists}">
                        <p class="text-danger fw-bold">${alreadyExists}</p>
                    </c:if>
                </div>
                <div class="form-group">
                    <label class="fw-bold" for="division">Division (Category)</label>
                    <select class="form-control" size="3" name="division" id="division">
                        <c:forEach var="category" items="${category}">
                            <option value="${category.division}">${category.division}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <c:choose>
                <c:when test="${!empty editedTeam}">
                    <button type="submit" name="id" class="btn btn-primary bg-success" value="${editedTeam.id}">Edit Team</button>
                </c:when>
                <c:otherwise>
                    <button type="submit" name="id" class="btn btn-primary bg-success" value="${team.id}">Edit Team</button>
                </c:otherwise>
            </c:choose>
        </form>
    </div>
</div>
</body>
<c:import url="footer.jsp"/>
</html>
