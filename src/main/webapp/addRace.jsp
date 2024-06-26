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
                <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>Add Race</em></h1>
                <c:if test="${!empty race}">
                    <p class="text-success text-center fw-bold">You have successfully added a new race!</p>
                </c:if>
                <div class="d-flex flex-column gap-5 fw-bold">
                    <form action="addRace" class="form-inline" method="POST">

                        <div class="search text-white p-5 border rounded" id="add_race">
                            <div class="form-group">
                                <label for="name">Name</label>
                                <input type="text" class="form-control" name="name" id="name" required>
                                <c:if test="${!empty message}">
                                    <p class="text-danger small">${message}</p>
                                </c:if>
                            </div>
                            <div class="form-group">
                                <label for="length">Length (in hours)</label>
                                <input type="number" class="form-control" name="length" id="length" required>
                            </div>
                            <div class="form-group">
                                <label for="date">Date</label>
                                <input type="date" class="form-control" name="date" id="date" required>
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