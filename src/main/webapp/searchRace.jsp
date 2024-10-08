<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<c:import url="head.jsp"/>
<body>
<%@include file="nav.jsp"%>
    <div class="container bg-white">
        <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>Search Race</em></h1>
        <div class="d-flex flex-column gap-5 fw-bold">
            <form action="search" class="form-inline">
                <c:if test="${!empty missingField}">
                    <p class="text-success fw-bold">${missingField}</p>
                </c:if>
                <div class="search text-white p-5 border rounded" id="search_race">
                    <label class="mb-2" for="searchTerm">Search</label>
                    <input  class="form-control" type="search" name="searchTerm" id="searchTerm">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input class="form-check-input" type="radio" name="searchType" id="name" value="raceName" checked>
                    </div>
                    <div class="form-group">
                        <label for="length">Length</label>
                        <input class="form-check-input" type="radio" name="searchType" id="length" value="length">
                    </div>
                </div>
                <button type="submit" name="submit" class="btn btn-success bg-success" value="search">Search</button>
                <button type="submit" name="submit" class="btn btn-success bg-primary" value="viewAllRaces">View All</button>
                <button type="reset" name="clear" class="btn btn-danger bg-danger" value="clear">Clear</button>
            </form>
        </div>
    </div>
</body>
<c:import url="footer.jsp"/>
</html>