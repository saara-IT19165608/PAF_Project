$(document).ready(function()
	{
	if ($("#alertSuccess").text().trim() == "")
	{
	$("#alertSuccess").hide();
	}
	$("#alertError").hide();
	});
	
// SAVE ============================================
	$(document).on("click", "#btnSave", function(event)
	{
		// Clear alerts---------------------
		$("#alertSuccess").text("");
		$("#alertSuccess").hide();
		$("#alertError").text("");
		$("#alertError").hide();
		
		// Form validation-------------------
	    var status = validateUserForm();
		if (status != true)
		{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
		}
		
		 // If valid------------------------
		 var type = ($("#hidUserIDSave").val() == "") ? "POST" : "PUT"; 
		 $.ajax( 
		 { 
		 url : "UserAPI", 
		 type : type, 
		 data : $("#formUser").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onUserSaveComplete(response.responseText, status); 
		 } 
 	}); 
});
		
// UPDATE==========================================
	$(document).on("click", ".btnUpdate", function(event)
	{
	$("#hidUserIDSave").val($(this).data("userid"));
	$("#userName").val($(this).closest("tr").find('td:eq(0)').text());
	$("#userEmail").val($(this).closest("tr").find('td:eq(1)').text());
	$("#userPassword").val($(this).closest("tr").find('td:eq(2)').text());
	$("#userNIC").val($(this).closest("tr").find('td:eq(3)').text());
	});
	
// DELETE===========================================
	$(document).on("click", ".btnRemove", function(event)
	{ 
	 $.ajax( 
	 { 
	 url : "UserAPI", 
	 type : "DELETE", 
	 data : "userID=" + $(this).data("userid"),
	 dataType : "text", 
	 complete : function(response, status) 
	 { 
	 onUserDeleteComplete(response.responseText, status); 
	 } 
	 }); 
});
// CLIENT-MODEL================================================================
function validateUserForm()
	{
	// name
	if ($("#userName").val().trim() == "")
	{
	return "Insert user Name.";
	}
	// email
	if ($("#userEmail").val().trim() == "")
	{
	return "Insert User Email.";
	}

	// password
	if ($("#userPassword").val().trim() == "")
	{
	return "Insert user Password.";
	}
	
	// NIC
	if ($("#userNIC").val().trim() == "")
	{
	return "Insert User NIC.";
	}
	return true;
}

function onUserSaveComplete(response, status)
	{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully saved."); 
	 $("#alertSuccess").show();
	 $("#divUsersGrid").html(resultSet.data); 
	 } else if (resultSet.status.trim() == "error") 
	 { 
	 $("#alertError").text(resultSet.data); 
	 $("#alertError").show(); 
	 } 
	 } else if (status == "error") 
	 { 
	 $("#alertError").text("Error while saving."); 
	 $("#alertError").show(); 
	 } else
	 { 
	 $("#alertError").text("Unknown error while saving.."); 
	 $("#alertError").show(); 
	 } 
	 $("#hidUserIDSave").val(""); 
	 $("#formUser")[0].reset(); 
}

function onUserDeleteComplete(response, status)
	{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully deleted."); 
	 $("#alertSuccess").show();
	 $("#divUsersGrid").html(resultSet.data); 
	 } else if (resultSet.status.trim() == "error") 
	 { 
	 $("#alertError").text(resultSet.data); 
	 $("#alertError").show(); 
	 } 
	 } else if (status == "error") 
	 { 
	 $("#alertError").text("Error while deleting."); 
	 $("#alertError").show(); 
	 } else
	 { 
	 $("#alertError").text("Unknown error while deleting.."); 
	 $("#alertError").show(); 
 } 
}




