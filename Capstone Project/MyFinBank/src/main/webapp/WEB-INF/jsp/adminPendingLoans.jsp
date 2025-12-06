<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pending Loan Requests</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/css/FinBanklogo.png">
    
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-myfinbank">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold" href="#">MyFinBank - Admin</a>
        <div class="d-flex">
            <a href="${pageContext.request.contextPath}/admin/dashboard"
               class="btn btn-outline-light btn-sm me-2">Dashboard</a>
            <a href="${pageContext.request.contextPath}/admin/logout"
               class="btn btn-outline-light btn-sm">Logout</a>
        </div>
    </div>
</nav>

<div class="container my-4">

    <h3 class="page-title mb-3">Pending Loan Applications</h3>

    <!-- Flash messages -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>

    <!-- If no loan requests -->
    <c:if test="${empty pendingLoans}">
        <div class="alert alert-info">No pending loan requests at the moment.</div>
    </c:if>

    <!-- Loan Table -->
    <c:if test="${not empty pendingLoans}">
        <div class="card card-myfinbank">
            <div class="card-myfinbank-header">
                <span class="fw-semibold">Loan Requests</span>
            </div>
            <div class="card-myfinbank-body">

                <div class="table-responsive">
                    <table class="table table-bordered table-sm align-middle">
                        <thead>
                        <tr>
                            <th>Loan ID</th>
                            <th>Customer ID</th>
                            <th>Loan Type</th>
                            <th>Amount</th>
                            <th>Tenure (Months)</th>
                            <th>Interest Rate</th>
                            <th>Applied On</th>
                            <th>Actions</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="loan" items="${pendingLoans}">
                            <tr>
                                <td>${loan.id}</td>
                                <td>${loan.customerId}</td>
                                <td>${loan.type}</td>
                                <td>₹ ${loan.amount}</td>
                                <td>${loan.tenureMonths}</td>
                                <td>${loan.annualInterestRate}%</td>
                                <td>${loan.appliedAt}</td>

                                <td>
                                    <!-- Approve -->
                                    <form action="${pageContext.request.contextPath}/admin/loans/${loan.id}/approve"
                                          method="post" style="display:inline;">
                                        <button type="submit" class="btn btn-success btn-sm">
                                            Approve
                                        </button>
                                    </form>

                                    <!-- Reject -->
                                    <form action="${pageContext.request.contextPath}/admin/loans/${loan.id}/reject"
                                          method="post" style="display:inline;margin-left:6px;">
                                        <input type="text" name="reason"
                                               placeholder="Reason"
                                               class="form-control form-control-sm d-inline-block"
                                               style="width:150px;" />
                                        <button type="submit" class="btn btn-danger btn-sm">
                                            Reject
                                        </button>
                                    </form>

                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>

                    </table>
                </div>

            </div>
        </div>
    </c:if>

</div>

</body>
</html>
