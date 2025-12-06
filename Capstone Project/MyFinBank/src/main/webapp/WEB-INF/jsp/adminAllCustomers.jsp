<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>MyFinBank - All Customers</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/css/FinBanklogo.png">
    
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-myfinbank">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold" href="#">MyFinBank</a>
        <div class="d-flex">
            <a href="${pageContext.request.contextPath}/admin/inactive-customers"
               class="btn btn-outline-light btn-sm me-2">Inactive Customers</a>
            <a href="${pageContext.request.contextPath}/customer/add"
               class="btn btn-outline-light btn-sm">Add Customer</a>
               <a href="${pageContext.request.contextPath}/admin/dashboard"
               class="btn btn-outline-light btn-sm me-2">Dashboard</a>

            <a href="${pageContext.request.contextPath}/admin/logout"
               class="btn btn-outline-light btn-sm">Logout</a>
        </div>
    </div>
</nav>

<div class="container my-4">
    <h3 class="page-title mb-3">All Customers</h3>

    <div class="card card-myfinbank">
        <div class="card-myfinbank-header">
            <span class="fw-semibold">Customer List</span>
        </div>
        <div class="card-myfinbank-body">

            <c:if test="${empty customers}">
                <div class="alert alert-info mb-0">
                    No customers found.
                </div>
            </c:if>

            <c:if test="${not empty customers}">
                <div class="table-responsive">
                    <table class="table table-bordered align-middle">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Account No</th>
                            <th>Name</th>
                            <th>City</th>
                            <th>Amount</th>
                            <th>Status</th>
                            <th>Edit Details</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="cst" items="${customers}">
                            <tr>
                                <td>${cst.id}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty cst.accountNo}">
                                            ${cst.accountNo}
                                        </c:when>
                                        <c:otherwise>
                                            -
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${cst.firstName} ${cst.lastName}</td>
                                <td>${cst.city}</td>
                                <td>${cst.amount}</td>
                                <td>
    <c:choose>
        <c:when test="${cst.status == 'ACTIVE'}">
            <span class="badge badge-status-active">ACTIVE</span>
        </c:when>
        <c:otherwise>
            <span class="badge badge-status-inactive">INACTIVE</span>
        </c:otherwise>
    </c:choose>
</td>
<td class="text-center">
    <a href="${pageContext.request.contextPath}/admin/customer/edit/${cst.id}"
       class="btn btn-warning btn-sm">
        Edit
    </a>
</td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>

        </div>
    </div>
</div>

</body>
</html>
