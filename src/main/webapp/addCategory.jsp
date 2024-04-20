<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="nav.jsp"%>
<html>
<body>
<div class="container bg-white">
    <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>Add a Category</em></h1>
    <div class="d-flex flex-column gap-5 fw-bold">
        <form action="addCategory" class="form-inline" method="POST">
            <div class="search text-white p-5 border rounded" id="add_team_form">
                <div class="form-group">
                    <label for="category">Category</label>
                    <input type="text" class="form-control" name="category" id="category">
                </div>
            </div>
            <div class="buttons mt-3">
                <button type="submit" name="submit" class="btn btn-primary bg-success" value="submit">Submit</button>
                <button type="reset" name="clear" class="btn btn-primary bg-danger" value="clear">Clear</button>
            </div>
        </form>
    </div>
</div>
</body>
<%@include file="footer.jsp"%>
</html>