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
                <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>You are editing:</em></h1>
                <c:if test="${!empty editedRace}">
                    <p class="text-success text-center fw-bold">You have successfully edited the race!</p>
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
                                <c:when test="${!empty editedRace}">
                                    <tr class="bg-white">
                                        <td class="text-black">${editedRace.name}</td>
                                        <td class="text-black">${editedRace.length}</td>
                                        <td class="text-black">${editedRace.date}</td>
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
                    <form action="editRaceById" class="form-inline" method="POST">
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
                            <c:when test="${!empty editedRace}">
                                <button type="submit" name="id" class="btn btn-primary bg-success" value="${editedRace.id}">Edit Race</button>
                            </c:when>
                            <c:otherwise>
                                <button type="submit" name="id" class="btn btn-success bg-success" value="${race.id}">Edit Race</button>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</body>
<c:import url="footer.jsp"/>
</html>
