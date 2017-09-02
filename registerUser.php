<?php

require_once '../includes/DBOperations.php';
$response = array();

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	
	if(isset($_POST['first_name']) and isset($_POST['last_name']) and
		isset($_POST['contact_num']) and isset($_POST['email']) and
		isset($_POST['username']) and isset($_POST['password'])){
		
		//operate the data further
		$db = new DBOperations();

		if($db->createUser($_POST['first_name'], $_POST['last_name'], $_POST['contact_num'], $_POST['email'], $_POST['username'], $_POST['password'])){
			$response['error'] = false;
			$response['message'] = "User registered successfully";
		}
		else{
			$response['error'] = true;
			$response['message'] = "Some error occurred. Please try again.";
		}

	}
	else{
		$response['error'] = true;
		$response['message'] = "Required fields are missing";
	}
}
else{
	$response['error'] = true;
	$response['message'] = "Invalid Request";
}

echo json_encode($response);