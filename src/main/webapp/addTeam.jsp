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
                <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>Add a Team</em></h1>
                <c:if test="${!empty team}">
                    <p class="text-success text-center fw-bold">You have successfully added a new team!</p>
                </c:if>
                <div class="d-flex flex-column gap-5 fw-bold">
                    <form action="addTeam" class="form-inline" method="POST">
                        <c:if test="${!empty missingField}"><p class="text-danger fw-bold">${missingField}</p></c:if>
                        <c:if test="${!empty e}"><p class="text-danger fw-bold">${e}</p></c:if>
                        <div class="search text-white p-5 border rounded" id="add_team">
                            <div class="form-group">
                                <label for="name">Name</label>
                                <input type="text" class="form-control" name="name" id="name" required>
                                <c:if test="${!empty message}">
                                    <p class="text-danger small">${message}</p>
                                </c:if>
                            </div>
                            <div class="form-group">
                                <label class="fw-bold" for="division">Division (Category)</label>
                                <select class="form-control" size="3" name="id" id="division" required>
                                    <c:forEach var="category" items="${category}">
                                        <option value="${category.category_id}">${category.division}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <button type="submit" name="submit" class="btn btn-success bg-success" value="submit">Submit</button>
                        <button type="reset" name="clear" class="btn btn-danger bg-danger" value="clear">Clear</button>
                    </form>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</body>
<c:import url="footer.jsp"/>
</html>