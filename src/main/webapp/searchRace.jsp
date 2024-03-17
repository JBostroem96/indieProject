<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="nav.jsp"%>
<html>
<body>
<div class="container bg-white">
    <div class="d-flex flex-column gap-5 fw-bold">
        <h2 class="mt-5 text-center fw-bold">Find race</h2>
        <form action="searchRace" class="form-inline">
                    <div class="search bg-primary text-white p-5 border rounded">
                        <label class="mb-2" for="searchTerm">Search</label>
                        <input type="search" name="searchTerm" id="searchTerm" required>
                        <div class="form-group">
                            <label for="name">Name</label>
                            <input type="radio" name="searchType" id="name" value="name" checked>
                        </div>
                        <div class="form-group">
                            <label for="length">Length</label>
                            <input type="radio" name="searchType" id="length" value="length">
                        </div>
                    </div>
                <div class="buttons mt-3">
                    <button type="submit" name="submit" class="btn btn-primary bg-success" value="search">Search</button>
                    <button type="submit" name="submit" class="btn btn-primary bg-primary" value="viewAll">View All</button>
                    <button type="reset" name="clear" class="btn btn-primary bg-danger" value="clear">Clear</button>
                </div>
        </form>
    </div>
</div>
</body>
</html>