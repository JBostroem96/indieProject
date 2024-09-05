<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<c:import url="head.jsp"/>

<script type="text/javascript" class="init">

    $(document).ready(function () {
        $('.display').DataTable();
    });
</script>
<body>

    <%@include file="nav.jsp"%>
    <div class="container bg-white">
        <c:choose>
            <c:when test="${empty userRole}">
                <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>Results:</em></h1>
                <div class="d-flex flex-column gap-5 fw-bold border rounded">
                    <table class="display" cellspacing="=0" width="100%">
                        <thead>
                            <th>Name</th>
                            <th>Role</th>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${users}">
                                <tr>
                                    <td class="text-black">${user.name}</td>
                                    <td class="text-black">${user.role}</td>
                                    <td>
                                        <form action="editUserRoleDisplay" class="form-inline" method="get">
                                            <button type="submit" name="id" class="btn btn-success bg-success btn-sm" value="${user.id}">Edit</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>Assign Role</em></h1>
                <c:if test="${!empty updatedRole}">
                    <p class="text-success text-center fw-bold">${updatedRole}</p>
                </c:if>
                <div class="d-flex flex-column gap-5 fw-bold">
                    <form action="editUserRole" class="form-inline">
                        <div class="search text-white p-5 border rounded" id="assign_role">
                            <label for="name">Name</label>
                            <input class="form-control" type="text" name="name" id="name" placeholder="${userRole.name}" disabled>
                            <div class="form-group">
                                <label for="user">User</label>
                                <input class="form-check-input" type="radio" name="role" id="user" value="USER" checked>
                            </div>
                            <div class="form-group">
                                <label for="admin">Admin</label>
                                <input class="form-check-input" type="radio" name="role" id="admin" value="ADMIN">
                        </div>
                        <c:if test="${!empty message}">
                            <p class="text-danger fw-bold">${message}</p>
                        </c:if>
                    </div>
                    <button type="submit" name="id" class="btn btn-success bg-success" value="${userRole.id}">Submit</button>
                </form>
            </div>
        </c:otherwise>
    </c:choose>
</body>
<c:import url="footer.jsp"/>
</html>


