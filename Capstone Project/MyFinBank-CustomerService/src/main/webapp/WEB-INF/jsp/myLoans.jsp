<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>MyFinBank - My Loans</title>

    <!-- Bootstrap + custom CSS + favicon -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/css/FinBanklogo.png">
</head>
<body>

<!-- Top navbar -->
<nav class="navbar navbar-expand-lg navbar-myfinbank">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/">MyFinBank</a>
        <div class="d-flex">
            <a href="${pageContext.request.contextPath}/customer/logout"
               class="btn btn-outline-light btn-sm me-2">Log out</a>
        </div>
    </div>
</nav>

<div class="container my-4">

    <h3 class="page-title mb-3">My Loan Applications</h3>

    <div class="card card-myfinbank">
        <div class="card-myfinbank-header d-flex justify-content-between align-items-center">
            <span class="fw-semibold">Loans Overview</span>
           
        </div>

        <div class="card-myfinbank-body">

            <c:if test="${empty loans}">
                <div class="alert alert-info mb-0">
                    You have not applied for any loans yet.
                </div>
            </c:if>

            <c:if test="${not empty loans}">
                <table class="table table-sm table-bordered mb-0">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Type</th>
                        <th>Amount</th>
                        <th>Tenure (months)</th>
                        <th>Rate (%)</th>
                        <th>Status</th>
                        <th>Applied At</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="l" items="${loans}">
                        <tr>
                            <td>${l.id}</td>
                            <td>${l.type}</td>
                            <td>₹ ${l.amount}</td>
                            <td>${l.tenureMonths}</td>
                            <td>${l.annualInterestRate}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${l.status == 'APPROVED'}">
                                        <span class="badge badge-status-active">APPROVED</span>
                                    </c:when>
                                    <c:when test="${l.status == 'REJECTED'}">
                                        <span class="badge badge-status-red">REJECTED</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-secondary">PENDING</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${l.appliedAt}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>

        </div>
    </div>

</div>

</body>
</html>
