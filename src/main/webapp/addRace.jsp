<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="nav.jsp"%>
<html>
<body>
<div class="container bg-white">
    <div class="d-flex flex-column gap-5 fw-bold">
        <h2 class="mt-5 text-center fw-bold">Add Race</h2>
        <form action="addRace" class="form-inline" method="POST">
            <div class="search bg-primary text-white p-5 border rounded">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" class="form-control" name="name" id="name">
                </div>
                <div class="form-group">
                    <label for="name">Length</label>
                    <input type="number" class="form-control" name="length" id="length">
                </div>
                <div class="form-group">
                    <label for="name">Date</label>
                    <input type="date" class="form-control" name="date" id="date">
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
</html>