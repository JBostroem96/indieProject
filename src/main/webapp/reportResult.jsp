<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<c:import url="head.jsp"/>
<body>
    <c:choose>
        <c:when test="${user.role != 'admin' || user.role != 'user'}">
            <p class="text-center fw-bold text-danger">You do not have permission to this page</p>
        </c:when>
        <c:otherwise>
            <%@include file="nav.jsp"%>
            <div class="container bg-white">
                <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>You are reporting:</em></h1>
                <c:if test="${!empty resultReported}">
                    <p class="text-success text-center fw-bold">Your report concerning ${resultReported} has been submitted</p>
                </c:if>
                <div class="d-flex flex-column gap-5 fw-bold">
                    <form action="reportResult" class="form-inline" method="POST">
                        <c:if test="${!empty missingField}"><p class="text-danger fw-bold">${missingField}</p></c:if>
                        <div class="search text-white p-5 border rounded" id="report">
                            <label for="name">Name</label>
                            <input class="form-control" type="text" name="name" id="name" placeholder="${result.team.name}" value="${result.team.name}" disabled>
                            <div class="form-group">
                                <label for="subject">Subject</label>
                                <input type="text" class="form-control" name="subject" id="subject" required>
                            </div>
                            <div class="form-group">
                                <label for="teamTextArea">Description</label>
                                <br>
                                <textarea name="teamTextArea" class="form-control" rows="5" cols="80" id="teamTextArea"></textarea>
                            </div>
                        </div>
                        <button type="submit" name="id" class="btn btn-success bg-success" value="${result.id}">Submit</button>
                    </form>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</body>
<c:import url="footer.jsp"/>
</html>
