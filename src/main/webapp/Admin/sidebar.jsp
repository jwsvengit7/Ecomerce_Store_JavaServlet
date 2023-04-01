<%--
  Created by IntelliJ IDEA.
  User: dabz
  Date: 22/03/2023
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar bg-secondary navbar-dark">
  <a href="index.html" class="navbar-brand mx-4 mb-3">
    <h3 class="text-primary"><img src="https://salespoint.com.ng/app/images/logo.png" width="50"/> </h3>
  </a>
  <div class="d-flex align-items-center ms-4 mb-4">
    <div class="position-relative">
      <img class="rounded-circle" src="https://salespoint.com.ng/app/images/logo.png" alt="" style="width: 40px; height: 40px;">
      <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
    </div>
    <div class="ms-3">
      <h6 class="mb-0">Store Admin</h6>
      <span>Admin</span>
    </div>
  </div>
  <div class="navbar-nav w-100">
    <a href="index.jsp" class="nav-item nav-link active"><i class="fa fa-tachometer-alt me-2"></i>Dashboard</a>

    <a href="view_details.jsp" class="nav-item nav-link"><i class="fa fa-keyboard me-2"></i>View Product</a>
    <a href="upload_product.jsp" class="nav-item nav-link"><i class="fa fa-table me-2"></i>Upload Product</a>

  </div>
</nav>
