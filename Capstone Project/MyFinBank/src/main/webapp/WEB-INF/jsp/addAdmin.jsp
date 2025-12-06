<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create New Admin - MyFinBank</title>
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

<div class="container my-4" style="max-width: 650px;">
    <h3 class="page-title mb-3">Create New Admin</h3>

    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>

    <div class="card card-myfinbank">
        <div class="card-myfinbank-header">
            <span class="fw-semibold">Admin Details</span>
        </div>
        <div class="card-myfinbank-body">

            <form action="${pageContext.request.contextPath}/admin/add-admin" method="post">

                <label class="form-label">Admin Name</label>
                <input type="text" name="adminName" class="form-control mb-3" required>

                <label class="form-label">Username</label>
                <input type="text" name="adminUserName" class="form-control mb-3" required>

                <label class="form-label">Password</label>
                <input type="password" name="adminPassword" class="form-control mb-3" required>

                <label class="form-label">Email</label>
                <input type="email" name="adminEmail" class="form-control mb-3" required>

                <label class="form-label">Phone</label>
                <input type="text" name="adminPhone" class="form-control mb-3" required>

                <label class="form-label">Status</label>
                <select name="adminStatus" class="form-control mb-3">
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="INACTIVE">INACTIVE</option>
                </select>

                <button type="submit" class="btn btn-primary">Create Admin</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
