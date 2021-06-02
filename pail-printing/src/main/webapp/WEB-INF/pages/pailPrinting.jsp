<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>BPCL</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<base href="${pageContext.request.contextPath}">
<link href="${pageContext.request.contextPath}/images/favicon.png"
	rel="shortcut icon">

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


<script type="text/javascript">
	$(document).ready(function() {
		$('#plantLocation').val("")
		$('#productCode').val("")
		$('#productSize').val("")
		$('#productName').val("")
		$('#packType').val("")
		$('#lineUuid').val("")
		$('#totalQty').val("")
		$('#browserImage').show();
		productionPlanCheck();
		var myVar = setInterval(myTimer, 15000);
		function myTimer() {
			productionPlanCheck();
		}

		function myStopFunction() {
			clearInterval(myVar);
		}
		function productionPlanCheck() {
			$.ajax({
				url : "/pailprinting/proplan/api/get",
				type : 'GET',
				success : function(data) {
					console.log(data)
					if (data == "") {

					} else {
						$('#plantLocation').val(data.locCode)
						$('#productCode').val(data.productCode)
						$('#productSize').val(data.volume)
						$('#productName').val(data.productName)
						$('#packType').val(data.packType)
						$('#lineUuid').val(data.lineUuid)

						$('#totalQty').val(data.volume)
						myStopFunction();
						$('#browserImage').hide();
					}
				}
			})
		}

	})
	function post() {
		var dataInfo = JSON.stringify({
			plantLocation : $('#plantLocation').val(),
			lineUuid : $('#lineUuid').val(),
			productCode : $('#productCode').val(),
			productName : $('#productName').val(),
			productSize : $('#productSize').val(),
			packType : $('#packType').val(),
			totalQty : $('#totalQty').val(),

		})
		$.ajax({
			url : "/pailprinting/pail/api/insert",
			type : 'POST', // http method
			data : dataInfo,
			contentType : "application/json",
			success : function(result) {
				alert(result)
				if (result == "success") {
					window.location.href = "/pailprinting/printerStatus"
				} else {
					toastr.error(result)
				}

			}

		})
	}
</script>
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

	<div class="container">
		<div class="loading" id="browserImage" style="display: none;">
			<img src="${pageContext.request.contextPath}/images/ajax-loader.gif">
		</div>
		<div class="page-content cutstom-wdth">
			<div class="row">
				<div class="col-lg-12 col-md-12">

					<form id="form-id" method="post">


						<input class="form-control" type="text" id="lineUuid"
							name="lineUuid" readonly="readonly" hidden>
						<div class="form-group">
							<label class="col-form-label">Plant Location</label> <input
								class="form-control" type="text" id="plantLocation"
								name="plantLocation" readonly="readonly">
						</div>

						<div class="form-group">
							<label class="col-form-label">Product Code</label> <input
								readonly="readonly" class="form-control" type="text"
								id="productCode" name="productCode">
						</div>
						<div class="form-group">
							<label class="col-form-label">Product Name</label> <input
								class="form-control" type="text" id="productName"
								name="productName" readonly="readonly">
						</div>

						<div class="form-group">
							<label class="col-form-label">Product Size</label> <input
								class="form-control" type="text" maxlength="4" id="productSize"
								name="productSize" readonly="readonly">
						</div>
						<div class="form-group">
							<label class="col-form-label">Pack Type</label> <input
								class="form-control" readonly="readonly" type="text"
								id="packType" name="packType">
						</div>
						<div class="form-group">
							<label class="col-form-label">Total Qty</label> <input
								class="form-control" type="text" id="totalQty" name="totalQty"
								readonly="readonly">
						</div>

					</form>

					<div class="text-center">
						<input onclick="post()" type="button" class="btn btn-primary"
							Value="Submit">
					</div>

				</div>
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

</body>
</html>