<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<c:import url="head.jsp"/>
<body>
    <%@include file="nav.jsp"%>
    <div class="container bg-white">
        <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>Search Team</em></h1>
        <div class="d-flex flex-column gap-5 fw-bold">
            <form action="search" class="form-inline">
                <div class="search text-white p-5 border rounded" id="search_team">
                    <label class="mb-2" for="searchTerm">Search</label>
                    <input  class="form-control" type="search" name="searchTerm" id="searchTerm">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input class="form-check-input" type="radio" name="searchType" id="name" value="teamName" checked>
                    </div>
                    <div class="form-group">
                        <label for="division">Division</label>
                        <input class="form-check-input" type="radio" name="searchType" id="division" value="division">
                    </div>
                </div>
                <button type="submit" name="submit" class="btn btn-success bg-success" value="search">Search</button>
                <button type="submit" name="submit" class="btn btn-primary bg-primary" value="viewAllTeams">View All</button>
                <button type="reset" name="clear" class="btn btn-danger bg-danger" value="clear">Clear</button>
            </form>
        </div>
    </div>
</body>
<c:import url="footer.jsp"/>
</html>