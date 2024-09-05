<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<c:import url="head.jsp"/>
<body>

    <%@include file="nav.jsp"%>
    <div class="container bg-white">
        <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>You are editing:</em></h1>
        <c:if test="${!empty raceUpdated}">
            <p class="text-success text-center fw-bold">${raceUpdated}</p>
        </c:if>
        <div class="d-flex flex-column gap-5 fw-bold border rounded" id="added_race">
            <table id="addRace" class="display" cellspacing="=0" width="100%">
                <thead>
                    <th>Name</th>
                    <th>Length</th>
                    <th>Date</th>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${!empty raceToUpdate}">
                            <tr class="bg-white">
                                <td class="text-black">${raceToUpdate.name}</td>
                                <td class="text-black">${raceToUpdate.length}</td>
                                <td class="text-black">${raceToUpdate.date}</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr class="bg-white">
                                <td class="text-black">${race.name}</td>
                                <td class="text-black">${race.length}</td>
                                <td class="text-black">${race.date}</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>
        <div class="d-flex flex-column gap-5 fw-bold">
            <form action="editRace" class="form-inline" method="POST">
                <c:if test="${!empty missingField}"><p class="text-danger fw-bold">${missingField}</p></c:if>
                <c:if test="${!empty e}"><p class="text-danger fw-bold">${e}</p></c:if>
                <div class="search text-white p-5 border rounded" id="edit_race">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" class="form-control" name="name" id="name" required>
                        <c:if test="${!empty message}">
                            <p class="text-danger fw-bold">${message}</p>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="length">Length</label>
                        <input type="number" class="form-control" name="length" id="length" required>
                    </div>
                    <div class="form-group">
                        <label for="date">Date</label>
                        <input type="date" class="form-control" name="date" id="date" required>
                    </div>
                </div>
                <c:choose>
                    <c:when test="${!empty raceToUpdate}">
                        <button type="submit" name="id" class="btn btn-primary bg-success" value="${raceToUpdate.id}">Edit Race</button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" name="id" class="btn btn-success bg-success" value="${race.id}">Edit Race</button>
                    </c:otherwise>
                </c:choose>
            </form>
        </div>
    </div>
</body>
<c:import url="footer.jsp"/>
</html>
