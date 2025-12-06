<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>MyFinBank - Customer Login</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/css/FinBanklogo.png">

    <style>
        /* Google Red Button */
        .google-btn-red {
            background: #db4437; 
            color: white;
            border: none;
            padding: 10px 14px;
            border-radius: 6px;
            font-weight: 500;
            transition: 0.2s;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .google-btn-red:hover {
            background: #c23321;
            color: #fff;
        }
        .google-icon {
            width: 18px;
            margin-right: 10px;
            background: white;
            border-radius: 50%;
            padding: 2px;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-myfinbank">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold" href="http://localhost:8080/">MyFinBank - Customer</a>
    </div>
</nav>

<div class="container d-flex justify-content-center align-items-center" style="min-height: 70vh;">
    <div class="card card-myfinbank" style="max-width: 420px; width: 100%;">
        <div class="card-myfinbank-header">
            <h5 class="mb-0 text-center">Customer Login</h5>
        </div>

        <div class="card-myfinbank-body p-4">

            <!-- Error from Controller -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger py-2">${error}</div>
            </c:if>

            <!-- Error from Google Login -->
            <c:if test="${not empty param.error}">
                <div class="alert alert-danger py-2">${param.error}</div>
            </c:if>

            <!-- Normal Login Form -->
            <form action="${pageContext.request.contextPath}/customer/login" method="post" class="row g-3">

                <div class="col-12">
                    <label class="form-label">Username</label>
                    <input type="text" name="username" class="form-control" required>
                </div>

                <div class="col-12">
                    <label class="form-label">Password</label>
                    <input type="password" name="password" class="form-control" required>
                </div>

                <div class="col-12 text-end">
                    <button type="submit" class="btn btn-primary px-4">Login</button>
                </div>
            </form>

            <hr class="my-4">

            <!-- Google Login Button (RED) -->
            <div class="d-grid">
                <a href="<c:url value='/oauth2/authorization/google'/>" class="google-btn-red">
                    <img src="https://www.gstatic.com/images/branding/product/1x/gsa_48dp.png"
                         class="google-icon">
                    Login with Google
                </a>
            </div>

        </div>
    </div>
</div>

</body>
</html>
