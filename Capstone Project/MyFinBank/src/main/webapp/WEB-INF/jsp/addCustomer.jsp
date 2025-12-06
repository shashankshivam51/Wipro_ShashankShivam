<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>MyFinBank - Add Customer</title>
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

<div class="container my-4">
    <h3 class="page-title mb-3">Open New Customer Account</h3>

    <div class="card card-myfinbank">
        <div class="card-myfinbank-header">
            <span class="fw-semibold">Customer Details</span>
        </div>
        <div class="card-myfinbank-body">

            <c:if test="${not empty message}">
                <div class="alert alert-success mb-3">${message}</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/customer/add" method="post" class="row g-3">

                <div class="col-md-6">
                    <label class="form-label">First Name</label>
                    <input type="text" name="firstName" class="form-control" required>
                </div>

                <div class="col-md-6">
                    <label class="form-label">Last Name</label>
                    <input type="text" name="lastName" class="form-control" required>
                </div>

                <div class="col-md-4">
                    <label class="form-label">Date of Birth</label>
                    <input type="date" name="dob" class="form-control">
                </div>

                <div class="col-md-4">
                    <label class="form-label">Account Type</label>
                    <select name="accountType" class="form-select">
                        <option value="SAVINGS">SAVINGS</option>
                        <option value="CURRENT">CURRENT</option>
                    </select>
                </div>

                <div class="col-md-4">
                    <label class="form-label">Initial Amount</label>
                    <input type="number" step="0.01" name="amount" class="form-control">
                </div>

                <div class="col-md-4">
                    <label class="form-label">Cheque Facility</label>
                    <select name="cheqFacil" class="form-select">
                        <option value="YES">YES</option>
                        <option value="NO">NO</option>
                    </select>
                </div>

                <div class="col-md-4">
                    <label class="form-label">Mobile No</label>
                    <input type="text" name="mobileNo" class="form-control">
                </div>

                <div class="col-md-4">
                    <label class="form-label">Email</label>
                    <input type="email" name="email" class="form-control">
                </div>

                <div class="col-md-6">
                    <label class="form-label">Address 1</label>
                    <input type="text" name="address1" class="form-control">
                </div>

                <div class="col-md-6">
                    <label class="form-label">Address 2</label>
                    <input type="text" name="address2" class="form-control">
                </div>

                <div class="col-md-4">
                    <label class="form-label">City</label>
                    <input type="text" name="city" class="form-control">
                </div>

                <div class="col-md-4">
                    <label class="form-label">State</label>
                    <input type="text" name="state" class="form-control">
                </div>

                <div class="col-md-4">
                    <label class="form-label">Zip Code</label>
                    <input type="text" name="zipCode" class="form-control">
                </div>

                <div class="col-md-4">
                    <label class="form-label">Country</label>
                    <input type="text" name="country" class="form-control">
                </div>

                <div class="col-md-4">
                    <label class="form-label">User Name</label>
                    <input type="text" name="userName" class="form-control">
                </div>

                <div class="col-md-4">
                    <label class="form-label">Password</label>
                    <input type="password" name="password" class="form-control">
                </div>

                <div class="col-12 text-end">
                    <button type="submit" class="btn btn-primary">
                        Create Account (Inactive)
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
