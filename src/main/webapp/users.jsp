<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<c:import url="head.jsp"/>

<script type="text/javascript" class="init">

    $(document).ready(function () {
        $('.display').DataTable();
    });
</script>
<body>
<c:choose>
    <c:when test="${user.role != 'admin'}">
        <p class="text-center fw-bold text-danger">You do not have permission to this page ${user.role}</p>
    </c:when>
    <c:otherwise>
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
                                            <form action="editUserRole" class="form-inline" method="get">
                                                <button type="submit" name="id" class="btn btn-success bg-success btn-sm" value="${user.id}">Edit</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                </c:when>
                <c:otherwise>
                    <h1 class="mt-5 text-center text-black fw-bold mb-5"><em>Assign Role</em></h1>
                    <c:if test="${!empty success}">
                        <p class="text-success text-center fw-bold">${success}</p>
                    </c:if>
                    <div class="d-flex flex-column gap-5 fw-bold">
                        <form action="editUserRoleById" class="form-inline">
                            <div class="search text-white p-5 border rounded" id="assign_role">
                                <label for="name">Name</label>
                                <input class="form-control" type="text" name="name" id="name" placeholder="${userRole.name}" disabled>
                                <div class="form-group">
                                    <label for="user">User</label>
                                    <input class="form-check-input" type="radio" name="role" id="user" value="user" checked>
                                </div>
                                <div class="form-group">
                                    <label for="admin">Admin</label>
                                    <input class="form-check-input" type="radio" name="role" id="admin" value="admin">
                                </div>
                                <c:if test="${!empty failure}">
                                    <p class="text-danger fw-bold">${failure}</p>
                                </c:if>
                            </div>
                            <button type="submit" name="id" class="btn btn-success bg-success" value="${userRole.id}">Submit</button>
                        </form>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</body>
<c:import url="footer.jsp"/>
</html>


