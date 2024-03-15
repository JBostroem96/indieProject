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
                        <div class="form-group">
                            <label for="name">Name</label>
                            <input type="text" class="form-control" name="searchTerm" id="name">
                        </div>
                        <div class="form-group">
                            <label for="name">Length</label>
                            <input type="number" class="form-control" name="searchTerm" id="length">
                        </div>
                        <div class="form-group">
                            <label for="name">Date</label>
                            <input type="date" class="form-control" name="searchTerm" id="date">
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