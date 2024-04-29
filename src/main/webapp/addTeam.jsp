<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="head.jsp"/>
<c:import url="nav.jsp"/>
<html>
<body>
<c:choose>

    <c:when test="${user.role == null}">
        <h1 class="text-center">${"You do not have permission to this page"}</h1>
    </c:when>
    <c:otherwise>
        <div class="container bg-white">
            <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>Add a Team</em></h1>
            <c:if test="${!empty team}">
                <h2 class="text-success text-center">You have successfully added a new team!</h2>
            </c:if>
            <div class="d-flex flex-column gap-5 fw-bold">
                <form action="addTeam" class="form-inline" method="POST">
                    <div class="search text-white p-5 border rounded" id="add_team_form">
                        <div class="form-group">
                            <label for="name">Name</label>
                            <input type="text" class="form-control" name="name" id="name">
                            <c:if test="${!empty message}">
                                <p class="text-danger small">${message}</p>
                            </c:if>
                        </div>
                        <div class="form-group">
                            <label class="fw-bold" for="division">Division (Category)</label>
                            <select class="form-control" size="3" name="id" id="division">
                                <c:forEach var="category" items="${category}">
                                    <option value="${category.category_id}">${category.division}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="buttons mt-3">
                        <button type="submit" name="submit" class="btn btn-primary bg-success" value="submit">Submit</button>
                        <button type="reset" name="clear" class="btn btn-primary bg-danger" value="clear">Clear</button>
                    </div>
                </form>
            </div>
        </div>
    </c:otherwise>
</c:choose>

</body>
<c:import url="footer.jsp"/>
</html>