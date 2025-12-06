<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Customer - MyFinBank Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/css/FinBanklogo.png">
    
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-myfinbank">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold" href="#">MyFinBank</a>
        <a href="${pageContext.request.contextPath}/admin/dashboard"
           class="btn btn-outline-light btn-sm">Dashboard</a>
           
             

            <a href="${pageContext.request.contextPath}/admin/logout"
               class="btn btn-outline-light btn-sm">Logout</a>
    </div>
</nav>

<div class="container my-4" style="max-width: 500px;">
    <h3 class="page-title mb-3">Search Customer</h3>

    <div class="card card-myfinbank">
        <div class="card-myfinbank-header">
            <span class="fw-semibold">Enter Account Number</span>
        </div>
        <div class="card-myfinbank-body">

            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/admin/search-customer" method="post">
                <label class="form-label">Account Number</label>
                <input type="text" name="accountNo" class="form-control mb-3" required>

                <button type="submit" class="btn btn-primary">Search</button>
            </form>

        </div>
    </div>
</div>

</body>
</html>
