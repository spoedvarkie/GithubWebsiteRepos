<?php

require_once 'DBOperations.php';
$responce = array();

if($_SERVER['REQUEST_METHOD'] == 'POST')
{
	if(isset($_POST['username']) and isset($_POST['password']))
	{
		$db = new DBOperations();
		
		if($db->userLogin($_POST['username'],$_POST['password']))
			{
				$response['error'] = false;
				$response['message'] = "User log in succeded.";
				return $_POST['username'] AND $_POST['password'];
			}
			else
			{
				$response['error'] = true;
				$response['message'] = "An error has occured. Please retry.";
			}
	}
	else
	{
		$response['error'] = true;
		$response['message'] = "Missing values. Please complete all fields.";
	}
}
else
{
	$response['error'] = true;
	$response['message'] = "Request is invalid";
}

echo json_encode($response);
?>