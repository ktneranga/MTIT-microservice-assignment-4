//hide alert
$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();
	$("#hidcustomerIDSave").val("");
	$("#customer")[0].reset();
});

$(document).on("click", "#btnSave", function(event) {

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
	var type = ($("#cID").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "customerAPI",
		type : type,
		data : $("#customer").serialize(),
		dataType : "text",
		complete : function(response, status) {
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
			$("#customerGrid").html(resultSet.data);
			
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
	
	$("#cID").val("");
	$("#customer")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) {
	
	$.ajax({
		url : "customerAPI",
		type : "DELETE",
		data : "cID=" + event.target.value,
		dataType : "text",
		complete : function(response, status) {
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
			$("#customerGrid").html(resultSet.data);
			
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
$(document).on("click",".btnUpdate",function(event)
		{
			$("#cID").val($(this).closest("tr").find('td:eq(0)').text());
			$("#customerCode").val($(this).closest("tr").find('td:eq(1)').text());
			$("#customerName").val($(this).closest("tr").find('td:eq(2)').text());
			$("#customerPrice").val($(this).closest("tr").find('td:eq(3)').text());
			$("#customerDesc").val($(this).closest("tr").find('td:eq(4)').text());
		});


// CLIENTMODEL=========================================================================
function validateItemForm() {
	
	// customer code
	if ($("#customerCode").val().trim() == "") {
		return "Please insert customer Code.";
	}
	
	// customerName
	if ($("#customerName").val().trim() == "") {
		return "Please insert customer Name.";
	}
	
	// customerPrice
	if ($("#customerPrice").val().trim() == "") {
		return "Please insert customer Price.";
	}
	
	// Customer Contact
	if ($("#customerDesc").val().trim() == "") {
		return "Please insert customer description.";
	}
	
	return true;
}
