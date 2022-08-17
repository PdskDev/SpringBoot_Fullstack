'use strict';

/** Processing when loading the screen */
jQuery(function($){
	
	/** Update button processing */
	$('#btn-update').click(function(event) {
		updateUser();
	});
	
	/** Delete button processing */
	$('#btn-delete').click(function(event){
		deleteUser();
	});

});

/**User update processing */
function updateUser(){
	
	//Get the value of form
	var formData = $('#user-detail-form').serializeArray();
	
	//Ajax communication
	$.ajax({
		type: "PUT",
		cache: false,
		url: '/user/update',
		data: formData,
		dataType: 'json',
	}).done(function(data){
		
		//alert success
		alert("Updated user");
		
		//Redirect to user list screen
		window.location.href = '/user/list';
	}).fail(function(jqXHR, textStatus, errorThrown){
		
		//Ajax failed
		alert("Failed to update user");
	}).always(function(){
		
		//Process to always execute
	});
}

/**User delete processing */
function deleteUser(){
	
	//Get the value of form
	var formData = $('#user-detail-form').serializeArray();
	
	//Ajax communication
	$.ajax({
		type: "DELETE",
		cache: false,
		url: '/user/delete',
		data: formData,
		dataType: 'json',
	}).done(function(data){
		
		//alert success
		alert("Deleted user");
		
		//Redirect to user list screen
		window.location.href = '/user/list';
	}).fail(function(jqXHR, textStatus, errorThrown){
		
		//Ajax failed
		alert("Failed to delete user");
	}).always(function(){
		
		//Process to always execute
	});
}


