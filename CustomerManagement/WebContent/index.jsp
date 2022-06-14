<%@page import="model.CustomerManage" %>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>customer Management - GadgetBadget</title>

			<link href="myStyle.css" rel="stylesheet" />
			<link rel="stylesheet" href="Views/bootstrap.min.css">
			<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> -->
			<script src="Components/jquery-3.5.0.min.js"></script>
			<script src="Components/customer.js"></script>

		</head>

		<body>
			<div class="container">

				<p class="font-weight-bold">
					<center>
						<h1><u><i><b>customer Management - GadgetBadget</b></i></u></h1>
					</center>
				</p>
				<br><br>

				<fieldset>

					<legend><b>Add customer Details</b></legend>
					<form id="customer" name="customer" class="bcustomer bcustomer-light p-5">

						<div class="form-outline mb-4">
							<label class="form-label" for="form6Example3"
								class="col-sm-2 col-form-label col-form-label-sm">customer Code:</label>
							<input type="text" id="cID" name="cID" hidden>
							<input type="text" id="customerCode" class="form-control" name="customerCode">
						</div>

						<div class="form-outline mb-4">
							<label class="form-label" for="form6Example3"
								class="col-sm-2 col-form-label col-form-label-sm">customer Name:</label>
							<input type="text" id="customerName" class="form-control" name="customerName">
						</div>

						_<div class="form-outline mb-4">
							<label class="form-label" for="form6Example3"
								class="col-sm-2 col-form-label col-form-label-sm">customer Price:</label>
							<input type="text" id="customerPrice" class="form-control" name="customerPrice">
						</div>

						<div class="form-outline mb-4">
							<label class="form-label" for="form6Example3"
								class="col-sm-2 col-form-label col-form-label-sm">customer Description:</label>
							<input type="text" id="customerDesc" class="form-control" name="customerDesc">
						</div>

						<br>
						<div id="alertSuccess" class="alert alert-success"></div>
						<div id="alertError" class="alert alert-danger"></div>
						<input id="btnSave" name="btnSave" type="button" value="Save"
							class="btn btn-primary btn-lg btn-block">

					</form>

				</fieldset>

				<br>

				<div class="container" id="customerGrid">
					<fieldset>
						<legend><b>View customer Details</b></legend>
						<form method="post" action="index.jsp" class="table table-striped">

							<%
							CustomerManage viewcustomer=new CustomerManage(); out.print(viewcustomer.readCustomer());
							%>
						</form>
						<br>
					</fieldset>
				</div>
			</div>
		</body>

		</html>