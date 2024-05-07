<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<c:import url="head.jsp"/>
<body>
    <%@include file="nav.jsp"%>
    <div class="container bg-white">
        <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>You are reporting:</em></h1>
        <c:if test="${!empty resultReported}">
            <p class="text-success text-center fw-bold">Your report concerning ${resultReported} has been submitted</p>
        </c:if>
        <div class="d-flex flex-column gap-5 fw-bold" id="added_race">
            <form action="reportResultById" class="form-inline" method="POST">
                <div class="search text-white p-5 border rounded" id="add_race_form">
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
                <button type="submit" name="id" class="btn btn-primary bg-success" value="${result.id}">Submit</button>
            </form>
        </div>
    </div>
</body>
<c:import url="footer.jsp"/>
</html>
