<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>MyFinBank - Admin Dashboard</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/css/FinBanklogo.png">
    
    <style>
        /* small visual tweaks to match card-myfinbank theme if not in CSS */
        .card-myfinbank { border-radius: 12px; box-shadow: 0 6px 18px rgba(10,20,40,0.06); }
        .card-myfinbank-header { background: linear-gradient(90deg,#0d6efd22,#0d6efd11); padding: .75rem 1rem; border-top-left-radius:12px; border-top-right-radius:12px; }
        .card-myfinbank-body { padding: 1rem; }
        .badge-status-active { background:#198754; color:white; padding:.25rem .5rem; border-radius:.25rem; }
        .badge-status-inactive { background:#6c757d; color:white; padding:.25rem .5rem; border-radius:.25rem; }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-myfinbank bg-dark text-white">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold text-white" href="${pageContext.request.contextPath}/admin/dashboard">MyFinBank - Admin</a>

        <div class="d-flex align-items-center">
            <c:choose>
                <c:when test="${not empty sessionScope.admin}">
                    <span class="me-3 text-light">Signed in as: <strong><c:out value="${sessionScope.admin.adminUserName}"/></strong></span>
                </c:when>
                <c:when test="${not empty sessionScope.loggedInAdmin}">
                    <span class="me-3 text-light">Signed in as: <strong><c:out value="${sessionScope.loggedInAdmin}"/></strong></span>
                </c:when>
            </c:choose>

            <a href="${pageContext.request.contextPath}/admin/logout" class="btn btn-outline-light btn-sm">Logout</a>
        </div>
    </div>
</nav>

<div class="container my-4">

    <h3 class="page-title mb-3">Admin Dashboard</h3>

    <!-- flash messages -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>

    <!-- Top statistic cards -->
    <div class="row g-3 mb-4">

        <!-- Total Customers -->
        <div class="col-md-3">
            <div class="card card-myfinbank">
                <div class="card-myfinbank-header">
                    <span class="fw-semibold">Total Customers</span>
                </div>
                <div class="card-myfinbank-body">
                    <h2><c:out value="${empty totalCustomers ? 0 : totalCustomers}"/></h2>
                    <small class="text-muted">All registered customers</small>
                </div>
            </div>
        </div>

        <!-- Inactive Accounts -->
        <div class="col-md-3">
            <div class="card card-myfinbank">
                <div class="card-myfinbank-header">
                    <span class="fw-semibold">Inactive Accounts</span>
                </div>
                <div class="card-myfinbank-body">
                    <h2><c:out value="${empty inactiveCustomers ? 0 : inactiveCustomers}"/></h2>
                    <small class="text-muted">Awaiting activation</small>
                </div>
            </div>
        </div>





    <!-- Main action cards -->
    <div class="row g-3">

        <!-- Add Customer -->
        <div class="col-md-4">
            <div class="card card-myfinbank">
                <div class="card-myfinbank-header">
                    <span class="fw-semibold">Add Customer</span>
                </div>
                <div class="card-myfinbank-body">
                    <p>Register a new customer account.</p>
                    <a href="${pageContext.request.contextPath}/customer/add" class="btn btn-primary btn-sm">
                        Go to Add Customer
                    </a>
                </div>
            </div>
        </div>

        <!-- Activate Accounts -->
        <div class="col-md-4">
            <div class="card card-myfinbank">
                <div class="card-myfinbank-header">
                    <span class="fw-semibold">Activate Accounts</span>
                </div>
                <div class="card-myfinbank-body">
                    <p>Approve and activate pending customers.</p>
                    <a href="${pageContext.request.contextPath}/admin/inactive-customers" class="btn btn-primary btn-sm">
                        View Pending Customers
                    </a>
                </div>
            </div>
        </div>

        <!-- All Customers -->
        <div class="col-md-4">
            <div class="card card-myfinbank">
                <div class="card-myfinbank-header">
                    <span class="fw-semibold">Show All Customers</span>
                </div>
                <div class="card-myfinbank-body">
                    <p>List of all customer accounts.</p>
                    <a href="${pageContext.request.contextPath}/admin/customers" class="btn btn-primary btn-sm">
                        View All Customers
                    </a>
                </div>
            </div>
        </div>

        <!-- Create New Admin -->
        <div class="col-md-4">
            <div class="card card-myfinbank">
                <div class="card-myfinbank-header">
                    <span class="fw-semibold">Create New Admin</span>
                </div>
                <div class="card-myfinbank-body">
                    <p>Manage admin users.</p>
                    <a href="${pageContext.request.contextPath}/admin/add-admin" class="btn btn-primary btn-sm">
                        Create Admin
                    </a>
                </div>
            </div>
        </div>

        <!-- View Specific Account -->
        <div class="col-md-4">
            <div class="card card-myfinbank">
                <div class="card-myfinbank-header">
                    <span class="fw-semibold">View Specific Account</span>
                </div>
                <div class="card-myfinbank-body">
                    <p>Search and view a customer by account number.</p>
                    <a href="${pageContext.request.contextPath}/admin/search" class="btn btn-primary btn-sm">
                        Search Customer
                    </a>
                </div>
            </div>
        </div>

        <!-- Manage Loan Applications -->
        <div class="col-md-4">
            <div class="card card-myfinbank">
                <div class="card-myfinbank-header">
                    <span class="fw-semibold">Manage Loan Applications</span>
                </div>
                <div class="card-myfinbank-body">
                    <p>Review and approve/reject customer loan requests.</p>
                    <a href="${pageContext.request.contextPath}/admin/loans/pending" class="btn btn-primary btn-sm">
                        Loan Requests
                    </a>
                </div>
            </div>
        </div>

    </div>

</div>

</body>
</html>
