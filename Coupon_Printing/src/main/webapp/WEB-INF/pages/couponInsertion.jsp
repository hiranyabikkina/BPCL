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
	$(document)
			.ready(
					function() {						
						 $('#plantLocationCode').val(""),
						 $('#quantity').val(""),
					 $('#lotCode').val(""),
						 $('#couponSize').val(""),
						$('#browserImage').show();
					
						var myVar = setInterval(myTimer, 15000);
						function myTimer() {
							getLineInformation()
						}

						function myStopFunction() {
							clearInterval(myVar);
						}
						getLineInformation()
						
						function getLineInformation(){
							$.ajax({  // /couponprinting
								url : "/couponprinting/lineinfo/api/get",
								type : 'GET', // http method								
								contentType : "application/json",
								success : function(data) {
									if (data == "") {
										
									} else {
										
								$('#plantLocationCode').val(data.locCode)																	
									myStopFunction();	
										$('#browserImage').hide();
									}

								}

							})

						}
						
						
						
						$('#frominsert')
								.click(
										function() {

											if ($('#quantity').val() == "") {
												toastr
														.error("Quantity TextField is Blank")
											} else if ($('#quantity').val() > 5000
													 ) {

												toastr
														.error("Quantity TextField Should be in between 1 To 5,000")
											}else if (
													$('#quantity').val()<= 0) {

												toastr
														.error("Quantity TextField Should be in between 1 To 5,000")
											} else if ($('#lotCode').val() ==""||$('#lotCode').val()<=0) {

												toastr
														.error("LotCodeQuantity TextField is Blank Or O Value")
											}else if ($('#couponSize').val() =="") {

												toastr
														.error("Please Select DropDow CouponSize Field")
											}else {
												var dataInfo = JSON.stringify({
													plantLocationCode : $('#plantLocationCode').val(),
													quantity : $('#quantity').val(),
													lotCode : $('#lotCode').val(),
													couponSize : $('#couponSize').val(),
													
												})
												// /couponprinting
												$.ajax({
													url : "/couponprinting/coupon/insert",
													type : 'POST', // http method
													data : dataInfo,
													contentType : "application/json",
													success : function(result) {
														
														if (result == "success") {
														//	/couponprinting
															window.location.href = "/couponprinting/printerStatus"
														} else {
															toastr.error(result)
														}

													}

												})

											}
										})
					})
					
					
				
 function onlyNumberKey(evt) {
          
        // Only ASCII charactar in that range allowed
        var ASCIICode = (evt.which) ? evt.which : evt.keyCode
        if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57))
            return false;
        return true;
    }
	 function onlyNumberKey1(evt) {
         
	        // Only ASCII charactar in that range allowed
	        var ASCIICode = (evt.which) ? evt.which : evt.keyCode
	        if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57))
	            return false;
	        return true;
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

						<div class="form-group">
							<label class="col-form-label">Location Code</label> <input
								class="form-control" type="text" name="plantLocationCode"
								id="plantLocationCode" readonly="readonly">
						</div>


						<div class="form-group">
							<label class="col-form-label">Quantity</label> <input
								class="form-control" type="text" maxlength="4"
								onkeypress="return onlyNumberKey(event)" id="quantity"
								name="quantity">
						</div>
						<div class="form-group">
							<label class="col-form-label">Lot Code Quantity</label> <input
								class="form-control" type="text" id="lotCode"
								onkeypress="return onlyNumberKey1(event)" name="lotCode">
						</div>
						<div class="form-group">
							<label class="col-form-label">Coupon Size</label> <select
								name="couponSize" id="couponSize" class="form-control"
								id="couponSize">
								<option value="">Select Coupon Size</option>
								<option value="30*60">Small Coupon</option>
								<option value="52*82">Pail Coupon</option>

							</select>
						</div>


					</form>

					<div class="text-center">
						<input id="frominsert" type="button" class="btn btn-primary"
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