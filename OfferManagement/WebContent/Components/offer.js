//hide alert
$(document).ready(function () {

	$("#alertSuccess").hide();
	$("#alertError").hide();
	$("#hidofferIDSave").val("");
	$("#offer")[0].reset();
});

$(document).on("click", "#btnSave", function (event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid------------------------
	var type = ($("#offID").val() == "") ? "POST" : "PUT";

	$.ajax({
		url: "offerAPI",
		type: type,
		data: $("#offer").serialize(),
		dataType: "text",
		complete: function (response, status) {
			//console.log(status);
			onItemSaveComplete(response.responseText, status);
			window.location.reload(true);
		}
	});

});

function onItemSaveComplete(response, status) {

	if (status == "success") {

		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {

			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#offerGrid").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {

			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}
	else if (status == "error") {

		$("#alertError").text("Error while saving.");
		$("#alertError").show();

	} else {

		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}

	$("#offID").val("");
	$("#offer")[0].reset();
}

$(document).on("click", ".btnRemove", function (event) {

	$.ajax({
		url: "offerAPI",
		type: "DELETE",
		data: "offID=" + event.target.value,
		dataType: "text",
		complete: function (response, status) {
			onItemDeleteComplete(response.responseText, status);
			window.location.reload(true);
		}
	});
});

function onItemDeleteComplete(response, status) {

	if (status == "success") {

		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {

			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#offerGrid").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {

			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}

	} else if (status == "error") {

		$("#alertError").text("Error while deleting.");
		$("#alertError").show();

	} else {

		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function (event) {
	$("#offID").val($(this).closest("tr").find('td:eq(0)').text());
	$("#offerCode").val($(this).closest("tr").find('td:eq(1)').text());
	$("#offerName").val($(this).closest("tr").find('td:eq(2)').text());
	$("#offerPrice").val($(this).closest("tr").find('td:eq(3)').text());
	$("#offerDesc").val($(this).closest("tr").find('td:eq(4)').text());
});


// CLIENTMODEL=========================================================================
function validateItemForm() {

	// offer code
	if ($("#offerCode").val().trim() == "") {
		return "Please insert offer Code.";
	}

	// offerName
	if ($("#offerName").val().trim() == "") {
		return "Please insert offer Name.";
	}

	// offerPrice
	if ($("#offerPrice").val().trim() == "") {
		return "Please insert offer Price.";
	}

	// offer Contact
	if ($("#offerDesc").val().trim() == "") {
		return "Please insert offer description.";
	}

	return true;
}
