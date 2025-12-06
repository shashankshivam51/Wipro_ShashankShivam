<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>MyFinBank - Customer Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/css/FinBanklogo.png">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-myfinbank">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold" href="http://localhost:8080/">MyFinBank</a>
        <div class="d-flex">
            <a href="${pageContext.request.contextPath}/customer/logout"
               class="btn btn-outline-light btn-sm me-2">Log out</a>
        </div>
    </div>
</nav>

<div class="container my-4">

    <!-- error -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <!-- success -->
    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>

    <c:if test="${not empty customer}">
        <h3 class="page-title mb-3">
            Welcome,
            <c:out value="${customer.firstName}"/> <c:out value="${customer.lastName}"/>
        </h3>

        <!-- ---- ACCOUNT SUMMARY + CUSTOMER DETAILS ---- -->
        <div class="row g-3">
            <div class="col-md-6">
                <div class="card card-myfinbank">
                    <div class="card-myfinbank-header">
                        <span class="fw-semibold">Account Summary</span>
                    </div>
                    <div class="card-myfinbank-body">
                        <p class="mb-1"><strong>Account No:</strong>
                            <c:choose>
                                <c:when test="${not empty customer.accountNo}">
                                    ${customer.accountNo}
                                </c:when>
                                <c:otherwise>
                                    Pending Approval
                                </c:otherwise>
                            </c:choose>
                        </p>
                        <p class="mb-1"><strong>Account Type:</strong> ${customer.accountType}</p>
                        <p class="mb-1"><strong>Balance:</strong> ₹ ${customer.amount}</p>
                        <p class="mb-1"><strong>Status:</strong>
                            <c:choose>
                                <c:when test="${customer.status == 'ACTIVE'}">
                                    <span class="badge badge-status-active">ACTIVE</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-status-inactive">INACTIVE</span>
                                </c:otherwise>
                            </c:choose>
                        </p>
                        <p class="mb-0"><strong>Date of Open:</strong> ${customer.dateOfOpen}</p>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="card card-myfinbank">
                    <div class="card-myfinbank-header">
                        <span class="fw-semibold">Customer Details</span>
                    </div>
                    <div class="card-myfinbank-body">
                        <p class="mb-1"><strong>Name:</strong> ${customer.firstName} ${customer.lastName}</p>
                        <p class="mb-1"><strong>Email:</strong> ${customer.email}</p>
                        <p class="mb-1"><strong>Mobile:</strong> ${customer.mobileNo}</p>
                        <p class="mb-1"><strong>Address:</strong>
                            ${customer.address1}
                            <c:if test="${not empty customer.address2}">, ${customer.address2}</c:if>,
                            ${customer.city}, ${customer.state} - ${customer.zipCode}, ${customer.country}
                        </p>
                        <p class="mb-0"><strong>DOB:</strong> ${customer.dob}</p>
                    </div>
                </div>
            </div>
        </div>

        <!-- ---- ACCOUNT ACTIONS: Deposit / Withdraw / Transfer ---- -->
        <div class="row g-3 mt-3">
            <div class="col-md-12">
                <div class="card card-myfinbank">
                    <div class="card-myfinbank-header">
                        <span class="fw-semibold">Account Actions</span>
                    </div>
                    <div class="card-myfinbank-body">
                        <div class="row g-3">

                            <!-- Deposit -->
                            <div class="col-md-4">
                                <h6>Deposit</h6>
                                <form method="post" action="${pageContext.request.contextPath}/customer/deposit">
                                    <div class="mb-2">
                                        <label class="form-label">Amount</label>
                                        <input type="number" step="0.01" min="0.01"
                                               name="amount" class="form-control" required>
                                    </div>
                                    <div class="mb-2">
                                        <label class="form-label">Remarks</label>
                                        <input type="text" name="remarks" class="form-control">
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-sm">Deposit</button>
                                </form>
                            </div>

                            <!-- Withdraw -->
                            <div class="col-md-4">
                                <h6>Withdraw</h6>
                                <form method="post" action="${pageContext.request.contextPath}/customer/withdraw">
                                    <div class="mb-2">
                                        <label class="form-label">Amount</label>
                                        <input type="number" step="0.01" min="0.01"
                                               name="amount" class="form-control" required>
                                    </div>
                                    <div class="mb-2">
                                        <label class="form-label">Remarks</label>
                                        <input type="text" name="remarks" class="form-control">
                                    </div>
                                    <button type="submit" class="btn btn-warning btn-sm">Withdraw</button>
                                </form>
                            </div>

                            <!-- Fund Transfer -->
                            <div class="col-md-4">
                                <h6>Fund Transfer</h6>
                                <form method="post" action="${pageContext.request.contextPath}/customer/transfer">
                                    <div class="mb-2">
                                        <label class="form-label">To Account No</label>
                                        <input type="text" name="toAccountNo" class="form-control" required>
                                    </div>
                                    <div class="mb-2">
                                        <label class="form-label">Amount</label>
                                        <input type="number" step="0.01" min="0.01"
                                               name="amount" class="form-control" required>
                                    </div>
                                    <div class="mb-2">
                                        <label class="form-label">Remarks</label>
                                        <input type="text" name="remarks" class="form-control">
                                    </div>
                                    <button type="submit" class="btn btn-success btn-sm">Transfer</button>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- ---- LOAN APPLICATION CARD (NEW) ---- -->
        <div class="row g-3 mt-3">
            <div class="col-md-12">
                <div class="card card-myfinbank">
                    <div class="card-myfinbank-header">
                        <span class="fw-semibold">Apply for Loan</span>
                    
                        
                    </div>

                    <div class="card-myfinbank-body">

                        <form method="post" action="${pageContext.request.contextPath}/customer/loans/apply">

                            <input type="hidden" name="customerId" value="${customer.id}" />

                            <div class="row g-2">

                                <div class="col-md-3">
                                    <label class="form-label">Loan Type</label>
                                    <select name="type" class="form-select" required>
                                        <option value="">Select</option>
                                        <option value="CAR">CAR</option>
                                        <option value="HOME">HOME</option>
                                        <option value="PERSONAL">PERSONAL</option>
                                    </select>
                                </div>

                                <div class="col-md-3">
                                    <label class="form-label">Amount (₹)</label>
                                    <input id="loan_amount" name="amount" type="number" class="form-control" required>
                                </div>

                                <div class="col-md-2">
                                    <label class="form-label">Rate (%)</label>
                                    <input id="loan_rate" name="annualInterestRate" type="number" class="form-control" value="7.5">
                                </div>

                                <div class="col-md-2">
                                    <label class="form-label">Months</label>
                                    <input id="loan_months" name="tenureMonths" type="number" class="form-control" value="36" required>
                                </div>

                                <div class="col-md-2 d-flex align-items-end">
                                    <button type="button" class="btn btn-outline-secondary btn-sm me-2" onclick="calculateAndShowEmi()">Calc EMI</button>
                                    <button type="submit" class="btn btn-primary btn-sm">Apply</button>
                                </div>
                            </div>

                            <div class="mt-2">
                                <small id="emiResult" class="text-muted">Estimated EMI: -</small>
                            </div>
                        </form>
                        
 <a href="${pageContext.request.contextPath}/customer/loans/my?customerId=${customer.id}"
   class="btn btn-warning btn-sm mt-3">
    View My Loans
</a>


                    </div>
                </div>
            </div>
        </div>

        <!-- ---- RECENT TRANSACTIONS ---- -->
        <div class="row g-3 mt-3">
            <div class="col-md-12">
                <div class="card card-myfinbank">
                    <div class="card-myfinbank-header d-flex justify-content-between align-items-center">
                        <span class="fw-semibold">Recent Transactions</span>
                        <small class="text-muted">Last 5 entries</small>
                    </div>

                    <div class="card-myfinbank-body">

                        <c:choose>

                            <c:when test="${empty customer.accountNo}">
                                <div class="alert alert-info mb-0">
                                    Account is not yet approved.
                                </div>
                            </c:when>

                            <c:when test="${empty transactions}">
                                <div class="alert alert-info mb-0">
                                    No transactions yet.
                                </div>
                            </c:when>

                            <c:otherwise>
                                <table class="table table-sm table-bordered mb-0">
                                    <thead>
                                    <tr>
                                        <th>Txn ID</th>
                                        <th>Time</th>
                                        <th>Type</th>
                                        <th>Amount</th>
                                        <th>Balance After</th>
                                        <th>Remarks</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="tx" items="${transactions}">
                                        <tr>
                                            <td>${tx.transactionId}</td>
                                            <td>${tx.transactionTime}</td>
                                            <td>${tx.transactionType}</td>
                                            <td>₹ ${tx.amount}</td>
                                            <td>₹ ${tx.balanceAfter}</td>
                                            <td>${tx.remarks}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:otherwise>

                        </c:choose>

                    </div>
                </div>
            </div>
        </div>

    </c:if>

</div>

<!-- ---- EMI SCRIPT ---- -->
<script>
    async function calculateAndShowEmi() {
        const principal = document.getElementById('loan_amount').value;
        const annualRate = document.getElementById('loan_rate').value;
        const months = document.getElementById('loan_months').value;

        if (!principal || !months) {
            document.getElementById('emiResult').innerText = "Enter amount and months";
            return;
        }

        // Evaluate contextPath on server side (JSP) so browser gets a valid base URL
        const contextPath = '${pageContext.request.contextPath}'; // correct JSP interpolation
        const fetchUrl = (contextPath ? contextPath : '') + '/customer/loans/emi'
            + '?principal=' + encodeURIComponent(principal)
            + '&annualRate=' + encodeURIComponent(annualRate)
            + '&months=' + encodeURIComponent(months);

        try {
            console.debug('Fetching EMI from:', fetchUrl);
            const res = await fetch(fetchUrl, { method: 'GET' });
            // optional: show status for debugging
            if (!res.ok) {
                console.error('EMI API returned status', res.status);
                document.getElementById('emiResult').innerText = 'Error calculating EMI';
                return;
            }
            const data = await res.json();
            console.debug('EMI API response:', data);

            if (data.emi !== undefined) {
                document.getElementById('emiResult').innerText = "Estimated EMI: ₹ " + data.emi;
            } else if (data.error) {
                document.getElementById('emiResult').innerText = "Error: " + data.error;
            } else {
                document.getElementById('emiResult').innerText = "Unexpected response";
            }
        } catch (e) {
            console.error('EMI fetch failed', e);
            document.getElementById('emiResult').innerText = "Error contacting server";
        }
    }
</script>








</body>
</html>
