<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<c:import url="head.jsp"/>
<body>

    <%@include file="nav.jsp"%>
    <div class="container bg-white">
        <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>You are editing:</em></h1>
        <c:if test="${!empty teamUpdated}">
            <p class="text-success text-center fw-bold">${teamUpdated}</p>
        </c:if>
        <div class="d-flex flex-column gap-5 fw-bold border rounded" id="added_race">
            <table id="addRace" class="display" cellspacing="=0" width="100%">
                <thead>
                    <th>Name</th>
                    <th>Division</th>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${!empty entry}">
                            <tr class="bg-white">
                                <td class="text-black">${entry.name}</td>
                                <td class="text-black">${entry.division}</td>
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
            <form action="editTeam" class="form-inline" method="POST">
                <c:if test="${!empty missingField}"><p class="text-danger fw-bold">${missingField}</p></c:if>
                <c:if test="${!empty e}"><p class="text-danger fw-bold">${e}</p></c:if>
                    <div class="search text-white p-5 border rounded" id="edit_team">
                        <div class="form-group">
                            <label for="name">Name</label>
                            <input type="text" class="form-control" name="name" id="name" required>
                            <c:if test="${!empty message}">
                                <p class="text-danger fw-bold">${message}</p>
                            </c:if>
                        </div>
                        <div class="form-group">
                            <label class="fw-bold" for="division">Division (Category)</label>
                            <select class="form-control" size="3" name="division" id="division" required>
                                <c:forEach var="category" items="${displayEntries}">
                                    <option value="${category.category_id}">${category.division}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                <c:choose>
                    <c:when test="${!empty entry}">
                        <button type="submit" name="id" class="btn btn-success bg-success" value="${entry.id}">Edit Team</button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" name="id" class="btn btn-success bg-success" value="${team.id}">Edit Team</button>
                    </c:otherwise>
                </c:choose>
            </form>
        </div>
    </div>
</body>
<c:import url="footer.jsp"/>
</html>
