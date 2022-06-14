<%@page import="model.OfferManage" %>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>offer Management - GadgetBadget</title>

			<link href="myStyle.css" rel="stylesheet" />
			<link rel="stylesheet" href="Views/bootstrap.min.css">
			<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> -->
			<script src="Components/jquery-3.5.0.min.js"></script>
			<script src="Components/offer.js"></script>

		</head>

		<body>
			<div class="container">

				<p class="font-weight-bold">
					<center>
						<h1><u><i><b>offer Management - GadgetBadget</b></i></u></h1>
					</center>
				</p>
				<br><br>

				<fieldset>

					<legend><b>Add offer Details</b></legend>
					<form id="offer" name="offer" class="boffer boffer-light p-5">

						<div class="form-outline mb-4">
							<label class="form-label" for="form6Example3"
								class="col-sm-2 col-form-label col-form-label-sm">offer Code:</label>
							<input type="text" id="offID" name="offID" hidden>
							<input type="text" id="offerCode" class="form-control" name="offerCode">
						</div>

						<div class="form-outline mb-4">
							<label class="form-label" for="form6Example3"
								class="col-sm-2 col-form-label col-form-label-sm">offer Name:</label>
							<input type="text" id="offerName" class="form-control" name="offerName">
						</div>

						_<div class="form-outline mb-4">
							<label class="form-label" for="form6Example3"
								class="col-sm-2 col-form-label col-form-label-sm">offer Price:</label>
							<input type="text" id="offerPrice" class="form-control" name="offerPrice">
						</div>

						<div class="form-outline mb-4">
							<label class="form-label" for="form6Example3"
								class="col-sm-2 col-form-label col-form-label-sm">offer Description:</label>
							<input type="text" id="offerDesc" class="form-control" name="offerDesc">
						</div>

						<br>
						<div id="alertSuccess" class="alert alert-success"></div>
						<div id="alertError" class="alert alert-danger"></div>
						<input id="btnSave" name="btnSave" type="button" value="Save"
							class="btn btn-primary btn-lg btn-block">

					</form>

				</fieldset>

				<br>

				<div class="container" id="offerGrid">
					<fieldset>
						<legend><b>View offer Details</b></legend>
						<form method="post" action="index.jsp" class="table table-striped">

							<% OfferManage viewoffer=new OfferManage(); out.print(viewoffer.readOffer()); %>
						</form>
						<br>
					</fieldset>
				</div>
			</div>
		</body>

		</html>