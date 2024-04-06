<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="nav.jsp"%>
<html>
<body>
<div class="container bg-white">
    <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>Add a Team</em></h1>
    <div class="d-flex flex-column gap-5 fw-bold">
        <form action="addTeam" class="form-inline" method="POST">
            <div class="search text-white p-5 border rounded" id="add_team_form">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" class="form-control" name="name" id="name">
                </div>
                <div class="form-group">
                    <label class="fw-bold" for="division">Division (Category)</label>
                    <select class="form-control" size="3" name="division" id="division">
                        <option value="1">Solo Female</option>
                        <option value="2">Female</option>
                        <option value="3">Solo Male</option>
                        <option value="4">Male</option>
                        <option value="5+">Mixed</option>
                    </select>
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