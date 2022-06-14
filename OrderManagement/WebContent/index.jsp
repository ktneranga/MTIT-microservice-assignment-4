<%@page import="model.OrderManage" %>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>Order Management - GadgetBadget</title>

			<link href="myStyle.css" rel="stylesheet" />
			<link rel="stylesheet" href="Views/bootstrap.min.css">
			<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> -->
			<script src="Components/jquery-3.5.0.min.js"></script>
			<script src="Components/order.js"></script>

		</head>

		<body>
			<div class="container">

				<p class="font-weight-bold">
					<center>
						<h1><u><i><b>order Management - GadgetBadget</b></i></u></h1>
					</center>
				</p>
				<br><br>

				<fieldset>

					<legend><b>Add order Details</b></legend>
					<form id="order" name="order" class="border border-light p-5">

						<div class="form-outline mb-4">
							<label class="form-label" for="form6Example3"
								class="col-sm-2 col-form-label col-form-label-sm">order Code:</label>
							<input type="text" id="oID" name="oID" hidden>
							<input type="text" id="orderCode" class="form-control" name="orderCode">
						</div>

						<div class="form-outline mb-4">
							<label class="form-label" for="form6Example3"
								class="col-sm-2 col-form-label col-form-label-sm">order Name:</label>
							<input type="text" id="orderName" class="form-control" name="orderName">
						</div>

						_<div class="form-outline mb-4">
							<label class="form-label" for="form6Example3"
								class="col-sm-2 col-form-label col-form-label-sm">order Price:</label>
							<input type="text" id="orderPrice" class="form-control" name="orderPrice">
						</div>

						<div class="form-outline mb-4">
							<label class="form-label" for="form6Example3"
								class="col-sm-2 col-form-label col-form-label-sm">order Description:</label>
							<input type="text" id="orderDesc" class="form-control" name="orderDesc">
						</div>

						<br>
						<div id="alertSuccess" class="alert alert-success"></div>
						<div id="alertError" class="alert alert-danger"></div>
						<input id="btnSave" name="btnSave" type="button" value="Save"
							class="btn btn-primary btn-lg btn-block">

					</form>

				</fieldset>

				<br>

				<div class="container" id="orderGrid">
					<fieldset>
						<legend><b>View Order Details</b></legend>
						<form method="post" action="index.jsp" class="table table-striped">

							<%
							OrderManage vieworder=new OrderManage(); out.print(vieworder.readOrders());
							%>
						</form>
						<br>
					</fieldset>
				</div>
			</div>
		</body>

		</html>