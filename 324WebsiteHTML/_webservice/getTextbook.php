<?php

require_once 'DBOperations.php';
$responce = array();

if($_SERVER['REQUEST_METHOD'] == 'POST')
{
	if(isset($_POST['textBookID']))
	{
		$db = new DBOperations();
		
		if($db->getTextbook($_POST['textBookID']))
			{
				$response['error'] = false;
				$response['message'] = "Textbook found";
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