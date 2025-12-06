<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Customer - MyFinBank</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/css/FinBanklogo.png">
    
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-myfinbank">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold" href="#">MyFinBank</a>
        <a href="${pageContext.request.contextPath}/admin/customers"
           class="btn btn-outline-light btn-sm">Back to Customers</a>
                       <a href="${pageContext.request.contextPath}/admin/dashboard"
               class="btn btn-outline-light btn-sm me-2">Dashboard</a>

            <a href="${pageContext.request.contextPath}/admin/logout"
               class="btn btn-outline-light btn-sm">Logout</a>
    </div>
</nav>

<div class="container my-4">
    <h3 class="page-title mb-3">Edit Customer</h3>

    <div class="card card-myfinbank">
        <div class="card-myfinbank-header">
            <span class="fw-semibold">Customer Information</span>
        </div>
        <div class="card-myfinbank-body">
            <form action="${pageContext.request.contextPath}/admin/customer/update" method="post" class="row g-3">

                <!-- important: hidden id -->
                <input type="hidden" name="id" value="${customer.id}"/>

                <div class="col-md-4">
                    <label class="form-label">Account No</label>
                    <input type="text" name="accountNo" class="form-control"
                           value="${customer.accountNo}">
                </div>

                <div class="col-md-4">
                    <label class="form-label">First Name</label>
                    <input type="text" name="firstName" class="form-control"
                           value="${customer.firstName}">
                </div>

                <div class="col-md-4">
                    <label class="form-label">Last Name</label>
                    <input type="text" name="lastName" class="form-control"
                           value="${customer.lastName}">
                </div>

                <div class="col-md-4">
                    <label class="form-label">Date of Birth</label>
                    <input type="date" name="dob" class="form-control"
                           value="${customer.dob}">
                </div>

                <div class="col-md-4">
                    <label class="form-label">Date of Open</label>
                    <input type="date" name="dateOfOpen" class="form-control"
                           value="${customer.dateOfOpen}">
                </div>

                <div class="col-md-4">
                    <label class="form-label">Account Type</label>
                    <select name="accountType" class="form-select">
                        <option value="SAVINGS" ${customer.accountType == 'SAVINGS' ? 'selected' : ''}>SAVINGS</option>
                        <option value="CURRENT" ${customer.accountType == 'CURRENT' ? 'selected' : ''}>CURRENT</option>
                    </select>
                </div>

                <div class="col-md-4">
                    <label class="form-label">Cheque Facility</label>
                    <select name="cheqFacil" class="form-select">
                        <option value="YES" ${customer.cheqFacil == 'YES' ? 'selected' : ''}>YES</option>
                        <option value="NO" ${customer.cheqFacil == 'NO' ? 'selected' : ''}>NO</option>
                    </select>
                </div>

                <div class="col-md-4">
                    <label class="form-label">Amount</label>
                    <input type="number" step="0.01" name="amount" class="form-control"
                           value="${customer.amount}">
                </div>

                <div class="col-md-4">
                    <label class="form-label">Status</label>
                    <select name="status" class="form-select">
                        <option value="ACTIVE" ${customer.status == 'ACTIVE' ? 'selected' : ''}>ACTIVE</option>
                        <option value="INACTIVE" ${customer.status == 'INACTIVE' ? 'selected' : ''}>INACTIVE</option>
                    </select>
                </div>

                <div class="col-md-6">
                    <label class="form-label">Email</label>
                    <input type="email" name="email" class="form-control"
                           value="${customer.email}">
                </div>

                <div class="col-md-6">
                    <label class="form-label">Mobile No</label>
                    <input type="text" name="mobileNo" class="form-control"
                           value="${customer.mobileNo}">
                </div>

                <div class="col-md-6">
                    <label class="form-label">Address 1</label>
                    <input type="text" name="address1" class="form-control"
                           value="${customer.address1}">
                </div>

                <div class="col-md-6">
                    <label class="form-label">Address 2</label>
                    <input type="text" name="address2" class="form-control"
                           value="${customer.address2}">
                </div>

                <div class="col-md-4">
                    <label class="form-label">City</label>
                    <input type="text" name="city" class="form-control"
                           value="${customer.city}">
                </div>

                <div class="col-md-4">
                    <label class="form-label">State</label>
                    <input type="text" name="state" class="form-control"
                           value="${customer.state}">
                </div>

                <div class="col-md-4">
                    <label class="form-label">Zip Code</label>
                    <input type="text" name="zipCode" class="form-control"
                           value="${customer.zipCode}">
                </div>

                <div class="col-md-4">
                    <label class="form-label">Country</label>
                    <input type="text" name="country" class="form-control"
                           value="${customer.country}">
                </div>

                <div class="col-md-4">
                    <label class="form-label">User Name</label>
                    <input type="text" name="userName" class="form-control"
                           value="${customer.userName}">
                </div>

                <div class="col-md-4">
                    <label class="form-label">Password</label>
                    <input type="password" name="password" class="form-control"
                           value="${customer.password}">
                </div>

                <div class="col-12 text-end">
                    <button type="submit" class="btn btn-primary">
                        Save Changes
                    </button>
                </div>

            </form>
        </div>
    </div>
</div>

</body>
</html>
