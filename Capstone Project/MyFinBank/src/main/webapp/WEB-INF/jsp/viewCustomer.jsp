<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Details - MyFinBank</title>
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
    </div>
</nav>

<div class="container my-4">
    <h3 class="page-title mb-3">Customer Details</h3>

    <div class="card card-myfinbank">
        <div class="card-myfinbank-header">
            <span class="fw-semibold">Account Information</span>
        </div>
        <div class="card-myfinbank-body">

            <table class="table table-bordered">
                <tr><th>Account No</th><td>${customer.accountNo}</td></tr>
                <tr><th>Name</th><td>${customer.firstName} ${customer.lastName}</td></tr>
                <tr><th>Email</th><td>${customer.email}</td></tr>
                <tr><th>Mobile</th><td>${customer.mobileNo}</td></tr>
                <tr><th>City</th><td>${customer.city}</td></tr>
                <tr><th>State</th><td>${customer.state}</td></tr>
                <tr><th>Amount</th><td>${customer.amount}</td></tr>
                <tr><th>Status</th>
                    <td>
                        <c:choose>
                            <c:when test="${customer.status == 'ACTIVE'}">
                                <span class="badge badge-status-active">ACTIVE</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge badge-status-inactive">INACTIVE</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </table>

        </div>
    </div>
</div>

</body>
</html>
