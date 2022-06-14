//hide alert
$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();
	$("#hidOrderIDSave").val("");
	$("#ORDER")[0].reset();
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
	var type = ($("#oID").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "OrderAPI",
		type : type,
		data : $("#order").serialize(),
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
			$("#orderGrid").html(resultSet.data);
			
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
	
	$("#oID").val("");
	$("#order")[0].reset();
}

$(document).on("click", ".btnRemove", function(event) {
	
	$.ajax({
		url : "OrderAPI",
		type : "DELETE",
		data : "oID=" + event.target.value,
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
			$("#orderGrid").html(resultSet.data);
			
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
			$("#oID").val($(this).closest("tr").find('td:eq(0)').text());
			$("#orderCode").val($(this).closest("tr").find('td:eq(1)').text());
			$("#orderName").val($(this).closest("tr").find('td:eq(2)').text());
			$("#orderPrice").val($(this).closest("tr").find('td:eq(3)').text());
			$("#orderDesc").val($(this).closest("tr").find('td:eq(4)').text());
		});


// CLIENTMODEL=========================================================================
function validateItemForm() {
	
	// order code
	if ($("#orderCode").val().trim() == "") {
		return "Please insert order Code.";
	}
	
	// orderName
	if ($("#orderName").val().trim() == "") {
		return "Please insert order Name.";
	}
	
	// orderPrice
	if ($("#orderPrice").val().trim() == "") {
		return "Please insert order Price.";
	}
	
	// Customer Contact
	if ($("#orderDesc").val().trim() == "") {
		return "Please insert order description.";
	}
	
	return true;
}
