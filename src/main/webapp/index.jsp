<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@include file="nav.jsp"%>
<html>
<body>

    <c:choose>
        <c:when test="${empty userName}">
            <h1 class="text-black fw-bold text-center"><em>Welcome!</em></h1>
            </c:when>
            <c:otherwise>
                <h1 class="text-black fw-bold text-center"><em>Welcome ${userName}!</em></h1>
            </c:otherwise>
        </c:choose>
</body>
    <%@include file="footer.jsp"%>
</html>