<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>

    <title>MyFinBank - Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/css/FinBanklogo.png">

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-myfinbank">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold d-flex align-items-center" href="#">
            <img src="${pageContext.request.contextPath}/css/FinBanklogo.png"
                 alt="MyFinBank Logo"
                 style="height:32px; margin-right:8px;">
            MyFinBank
        </a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNav">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="mainNav">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">

                <li class="nav-item">
                    <a class="nav-link" href="#about-us">About Us</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="#history">History</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="https://www.rbi.org.in" target="_blank">RBI Website</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="#services">Services</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="#branches">Branches</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="#contact">Contact</a>
                </li>

            </ul>
        </div>
    </div>
</nav>

<div class="container my-5" id="services">
    <h3 class="page-title mb-4 text-center">Welcome to MyFinBank</h3>

    <div class="row justify-content-center g-4">
        <div class="col-md-4">
            <div class="card card-myfinbank text-center">
                <div class="card-myfinbank-header">
                    <span class="fw-semibold">Admin Portal</span>
                </div>
                <div class="card-myfinbank-body">
                    <p>Login as bank admin to manage customers and accounts.</p>
                    <a href="${pageContext.request.contextPath}/admin/login"
                       class="btn btn-primary">Admin Login</a>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card card-myfinbank text-center">
                <div class="card-myfinbank-header">
                    <span class="fw-semibold">Customer Portal</span>
                </div>
                <div class="card-myfinbank-body">
                    <p>Login as customer to view accounts and transactions.</p>

                    <a href="http://localhost:9090/customer/login"
                       class="btn btn-primary">Customer Login</a>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row justify-content-center g-4">
    <div class="col-md-4">
        <div class="card card-myfinbank text-center">
            <div class="card-myfinbank-header">
                <span class="fw-semibold">Apply for new Account</span>
            </div>
            <div class="card-myfinbank-body">
                <p>Create a new account to bank with us.</p>
                <a href="http://localhost:8080/customer/add"
                   class="btn btn-primary">Register here</a>
            </div>
        </div>
    </div>
</div>

<!-- Why Choose Section -->
<section class="bg-light py-5">
    <div class="container text-center">
        <h4 class="fw-bold mb-3">Why Choose MyFinBank?</h4>
        <p class="mb-4">We provide secure, transparent, and fast banking services to make your financial journey smooth and reliable.</p>

        <div class="row g-4">
            <div class="col-md-4">
                <div class="p-3 border rounded h-100">
                    <h5>Secure Banking</h5>
                    <p>Your data and transactions are protected with industry-grade security.</p>
                </div>
            </div>

            <div class="col-md-4">
                <div class="p-3 border rounded h-100">
                    <h5>24/7 Support</h5>
                    <p>Our customer service team is always available to assist you.</p>
                </div>
            </div>

            <div class="col-md-4">
                <div class="p-3 border rounded h-100">
                    <h5>Easy Access</h5>
                    <p>Manage your accounts anytime from our modern online portal.</p>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Current Promotions -->
<section class="py-5">
    <div class="container text-center">
        <h4 class="fw-bold mb-3">Current Promotions</h4>
        <p class="mb-4">Join today and enjoy exclusive offers designed to help you save more.</p>

        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="alert alert-primary">
                    <strong>✨ New Customer Offer:</strong> Get a <b>zero-fee account</b> for the first 6 months!
                </div>
                <div class="alert alert-success">
                    <strong>💳 Cashback Bonus:</strong> Earn up to <b>5% cashback</b> on your first 3 transactions.
                </div>
            </div>
        </div>
    </div>
</section>

<!-- About Us (Neo Bank) -->
<section class="py-5 bg-light" id="about-us">
    <div class="container text-center">
        <h4 class="fw-bold mb-3">About Us</h4>
        <p class="mb-4">
            MyFinBank is a next-generation <strong>neo bank</strong> launched in
            <strong>October 2025</strong> with a mission to deliver secure,
            fully digital, and highly efficient financial services. Our platform
            is designed to give users a seamless banking experience without the
            limitations of traditional branch-based systems.
        </p>

        <p>
            Powered by modern technology and backed by strong compliance standards,
            MyFinBank provides instant account access, real-time transaction updates,
            and personalized financial tools—all through a clean and user-friendly interface.
        </p>
    </div>
</section>

<!-- History Section -->
<section class="py-5" id="history">
    <div class="container text-center">
        <h4 class="fw-bold mb-3">Our History</h4>

        <div class="row justify-content-center">
            <div class="col-md-8">

                <div class="timeline-item mb-4">
                    <h5 class="fw-semibold">October 2025 – Founding</h5>
                    <p>
                        MyFinBank was established with the vision to create a
                        fast, transparent, and modern banking alternative suitable
                        for the digital era.
                    </p>
                </div>

                <div class="timeline-item mb-4">
                    <h5 class="fw-semibold">2026 – Neo Banking Expansion</h5>
                    <p>
                        With rapid user adoption, the bank expanded its neo-banking
                        capabilities including instant KYC, AI-driven financial insights,
                        and secure mobile payment systems.
                    </p>
                </div>

                <div class="timeline-item">
                    <h5 class="fw-semibold">Future Roadmap</h5>
                    <p>
                        MyFinBank aims to integrate smart investment features,
                        international payment support, and advanced credit scoring models
                        to continue transforming how digital banking works in India.
                    </p>
                </div>

            </div>
        </div>
    </div>
</section>

<!-- Placeholder sections for Branches & Contact (linked from navbar) -->
<section class="py-5 bg-light" id="branches">
    <div class="container text-center">
        <h4 class="fw-bold mb-3">Our Presence</h4>
        <p class="mb-0">As a digital-first neo bank, MyFinBank offers services online across India, with partner touchpoints in major cities.</p>
    </div>
</section>

<section class="py-5" id="contact">
    <div class="container text-center">
        <h4 class="fw-bold mb-3">Contact Us</h4>
        <p class="mb-1">For any queries, support, or feedback, reach out to our customer support team.</p>
        <p class="mb-0">Email: support@myfinbank.com &nbsp; | &nbsp; Helpline: 1800-000-000</p>
    </div>
</section>

<footer class="text-center py-4 bg-dark text-white">
    <p class="mb-0">&copy; 2025 MyFinBank — Your trusted partner in digital banking.</p>
</footer>

<!-- Optional: Bootstrap JS for navbar toggler -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
