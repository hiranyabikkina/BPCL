
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
		
			<div class="row mb-3">
			<div class="col-md-6">
			<div class="form-group row">	
			    <label class="col-form-label col-md-5 text-right">Label Code :</label>
			    <div class="col-md-7">
				<input type="text" id="bottleCode" class="form-control"/>
				</div>
				</div>
			</div>
			<div class="col-md-6" id="couponText" hidden>
			<div class="form-group row">	
				<label class="col-form-label col-md-5 text-right">Coupon Code :</label>	
				<div class="col-md-7">
				<input type="text" id="couponCode" class="form-control"/>
			</div>
			</div>
		</div>
	   </div>
			
			<div class="text-center">
				<h6 class="mb-1">Production Plan Data</h6>
			</div>
			<div class="table-responsive">
				<table class="table bottle-info table-bordered bg-white mx-auto">
					<tr>
						<td><strong>Product Code:</strong> <span class="data-value"
							id='productCode'></span></td>
						<td><strong>Product Name:</strong> <span class="data-value"
							id="productName"></span></td>
						<td><strong>Batch Number.:</strong> <span class="data-value"
							id="productBatch"></span></td>
					</tr>
					<tr>
						<td><strong>Batch Quantity:</strong> <span class="data-value"
							id="volume"></span></td>
						<td><strong>Size:</strong> <span class="data-value"
							id='sizeCode'></span>Lts</td>
						<td><strong>Units Per case:</strong> <span class="data-value"
							id="upc"></span></td>
					</tr>
					<tr>
						<td><strong>Total Completed:</strong> <span
							class="data-value" id="compQuantity"></span></td>
						<td><strong>Pack Type :</strong> <span class="data-value"
							id="packType"></span></td>
						<td><strong>Coupon Apply :</strong> <span class="data-value"
							id="isCouponApply"></span></td>
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
		var prod = new EventSource('/pailsproduction/production/plan');

		prod.onmessage = function(evt) {
			// 			alert(evt.data)
			// 			console.log(evt.data)
			var productPlan = JSON.parse(evt.data)
// 			console.log(productPlan)
			if (productPlan != null) {
				$('#productName').html(productPlan.productName);
				$('#productCode').html(productPlan.productCode);
				$('#upc').html(productPlan.unitsPerCase);
				$('#volume').html(productPlan.volume);
				$('#productBatch').html(productPlan.productBatch);
				$('#sizeCode').html(productPlan.sizeCode);
				$('#packType').html(productPlan.packType);
				$('#isCouponApply').html(productPlan.isCouponApply);
				
				if(productPlan.isCouponApply.toLowerCase()==="yes"){
					$('#couponText').prop("hidden", false);
				}else{
					$('#couponText').prop("hidden", true);
				}
			}else{
				$('#productName').html("");
				$('#productCode').html("");
				$('#upc').html("");
				$('#volume').html("");
				$('#productBatch').html("");
				$('#sizeCode').html("");
				$('#packType').html("");
				$('#isCouponApply').html("");
				$('#couponText').prop("hidden", true);
			}

		}
		
		var labelProd=new EventSource('/pailsproduction/pails/production');
		labelProd.onmessage = function(evt) {
			
			var labelCoupon = JSON.parse(evt.data);
		console.log(labelCoupon)
			if (labelProd != null) {
				$('#bottleCode').val(labelCoupon.bottleCode);
				$('#couponCode').val(labelCoupon.couponCode);
				$('#compQuantity').html(labelCoupon.compQnty);
				if(labelCoupon.bottleCodeStatus===1)
					{
					var my_css = {
				            backgroundColor: "red",
				           
				        }
				        $("#bottleCode").css(my_css);
					}
				else
					{
					var my_css = {
				            backgroundColor: "green",
				           
				        }
				        $("#bottleCode").css(my_css);
					}
				if(labelCoupon.couponCodeStatus===1)
				{
				var my_css = {
			            backgroundColor: "red",
			           
			        }
			        $("#couponCode").css(my_css);
				}
			else
				{
				var my_css = {
			            backgroundColor: "yellow",
			           
			        }
			        $("#couponCode").css(my_css);
				}
				
				
			}
		}
		
		
		
	</script>
</body>
</html>