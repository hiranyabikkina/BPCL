<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>BPCL</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/images/favicon.png"
	rel="shortcut icon">
<link
	href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;500;600;700;800&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/toastr.css">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/toastr.js"></script>


<style type="text/css">
.bottle-info {
	width: 100%;
	font-size: 13px;
	margin-bottom: 10px;
	border-radius: 8px
}

.bottle-info .data-value {
	padding-left: 2px;
}

.matrix-table {
	border-collapse: inherit;
}

.matrix-table tr th {
	text-align: center;
	padding: 6px;
	border: none;
}

.matrix-table td {
	text-align: center;
}

.box {
	/* width: 100%;
  height: 100px; */
	padding: 20px 15px !important;
	background: #e4dd08;
	border-radius: 6px;
	/* display:flex;
  justify-content:center;
  align-items:center; */
	font-size: 20px;
	color: #fff;
	font-weight: 600;
}

.box.active {
	background: #3ab66c;
}
.box.worng {
	background: red;
}
.matrix-data {
	width: 100%;
}
</style>
</head>
<body class="bg-body">
	<!-- Navbar Starts -->
	<nav class="navbar navbar-expand-lg navbar-dark nav-header">
		<a class="top-nav d-flex" href="#"> <img
			src="${pageContext.request.contextPath}/images/logo-navbar.png"
			class="site-logo"> <span class="site-txt">Bharat
				Petroleum Corporation Limited<span class="site-sub-txt">QR
					Code Based Track & Trace System for Lubes BU</span>
		</span>
		</a>
	</nav>
	<!-- Navbar Ends -->

		<div class="container mb-5">
			<div class="page-content page-wdth-main">
				<div class="text-center">
					<!--  Main Matrix Data -->
					<div class="table-responsive matrix-data mx-auto" id="table"></div>
					<!--  Main Matrix Data -->
					<h6 class="mb-1" style="font-size: 25px">PAILS PRINTING EVENT</h6>
			</div>
			<div class="table-responsive">

				<table class="table bottle-info table-bordered bg-white mx-auto" style="width: 80%;font-size: 18px">
					<tr >
						
						<td bgcolor="yellow" colspan="2" class="text-center" style="border-radius: 3px"><span class="data-value"
							id="printerStatus" ></span></td>
						
					</tr>
					<tr>
						<td bgcolor="orange" ><strong>QR Size:</strong> <span class="data-value"
							id="couponSize"></span></td>
						<td bgcolor="orange" ><strong>Qty:</strong> <span class="data-value"
							id="qty"></span></td>
						
					</tr>
					<tr>
						<td bgcolor="green" ><strong >Print Qty:</strong> <span
							class="data-value" id="printQty"></span></td>
						<td bgcolor="yellow" ><strong>Inspected Qty: </strong> <span
							class="data-value" id="inspectionQty">1</span></td>
						
					</tr>
					<tr hidden>
						<td><strong>row :</strong> <span class="data-value" id="row"></span></td>
						<td><strong>col :</strong> <span class="data-value" id="col"></span></td>
					</tr>
				</table>

			</div>


		</div>
	</div>

	<!--  footer start here  -->
	<div class="sticky-footer">
		<div class="container-fluid">
			<div
				class="d-sm-flex justify-content-center justify-content-sm-between">
				<span
					class="footer-left text-center text-sm-left d-block d-sm-inline-block copy-right">Copyright
					© 2020 Bharat Petroleum Corporation Limited | All rights reserved.</span>
				<span
					class="footer-right float-none float-sm-right d-block mt-2 pb-3 pb-sm-0 mt-sm-0 text-center social-links">
					Powered by <a href="http://ctel.in/" class="text-white"
					target="_blank">Ctel Infosystems Pvt LTd.</a>
				</span>
			</div>
		</div>
	</div>
	<!-- footer Ends here  -->


	<script>
		$(document)
				.ready(
						function() {
							$('#printerStatus').html("")
							$('#couponSize').html("")
							$('#qty').html("")
							$('#printQty').html("")
							$('#inspectionQty').html("")
							
							const queryString = window.location.search;

							const urlParams = new URLSearchParams(queryString);

							var sse = new EventSource('/pailprinting/sse');
							sse.onmessage = function(evt) {

								var filled = JSON.parse(evt.data)							
							$('#printerStatus').html(filled.message)
							$('#couponSize').html(filled.size)
							$('#qty').html(filled.issued)
							$('#printQty').html(filled.print)
							$('#inspectionQty').html(filled.inspect)
							
								
							}

// 							var countCouponInspection = new EventSource('/countCouponInspection');

// 							countCouponInspection.onmessage = function(evt) {
// 								var productPlan = JSON.parse(evt.data)
							

// 							}
						});
	</script>
</body>
</html>