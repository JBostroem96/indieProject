<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<c:import url="head.jsp"/>

<body>

    <%@include file="nav.jsp"%>
    <h1 class="text-black fw-bold text-center mt-4"><em>Profile for ${user.userName}</em></h1>

    <div class="d-flex flex-column gap-5 fw-bold border rounded" id="form">
        <table id="addRace" class="display" cellspacing="=0" width="100%">
            <thead>
                <th>Name</th>
                <th>Username</th>
                <th>Email</th>
                <th>Role</th>
            </thead>
            <tbody>
                <tr class="bg-white">
                    <td class="text-black">${user.name}</td>
                    <td class="text-black">${user.userName}</td>
                    <td class="text-black">${user.email}</td>
                    <td class="text-black">${user.role}</td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
<c:import url="footer.jsp"/>
</html>