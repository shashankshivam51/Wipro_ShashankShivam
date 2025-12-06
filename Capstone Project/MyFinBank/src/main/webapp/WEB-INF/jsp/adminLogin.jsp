<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>MyFinBank - Admin Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/css/FinBanklogo.png">
    
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-myfinbank">
    <div class="container-fluid">
    
        <a class="navbar-brand fw-bold" href="http://localhost:8080/">MyFinBank</a>
    </div>
</nav>

<div class="container d-flex justify-content-center align-items-center" style="min-height: 70vh;">
    <div class="card card-myfinbank" style="max-width: 420px; width: 100%;">
        <div class="card-myfinbank-header">
            <h5 class="mb-0">Admin Login</h5>
        </div>
        <div class="card-myfinbank-body">
            <c:if test="${not empty error}">
                <div class="alert alert-danger mb-3">${error}</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/admin/login" method="post" class="row g-3">

                <div class="col-12">
                    <label class="form-label">Username</label>
                    <input type="text" name="username" class="form-control" required>
                </div>

                <div class="col-12">
                    <label class="form-label">Password</label>
                    <input type="password" name="password" class="form-control" required>
                </div>

                <div class="col-12 text-end">
                    <button type="submit" class="btn btn-primary">
                        Login
                    </button>
                </div>

            </form>
        </div>
    </div>
</div>

</body>
</html>
