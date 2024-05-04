<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="head.jsp"/>

<html>
<body>
<%@include file="nav.jsp"%>
<div class="container bg-white">
    <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>Search Race</em></h1>
    <div class="d-flex flex-column gap-5 fw-bold">
        <form action="search" class="form-inline">
                    <div class="search text-white p-5 border rounded" id="search_form">
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
                <div class="buttons mt-3">
                    <button type="submit" name="submit" class="btn btn-primary bg-success" value="search">Search</button>
                    <button type="submit" name="submit" class="btn btn-primary bg-primary" value="viewAllRaces">View All</button>
                    <button type="reset" name="clear" class="btn btn-primary bg-danger" value="clear">Clear</button>
                </div>
        </form>
    </div>
</div>
</body>
<c:import url="footer.jsp"/>
</html>