<?php

class DBConnect
{
	private $conn;
	
	function construct()
	{
		
	}
	
	function connect()
	{
		include_once dirname(_FILE_).'Constants.php';
		$this->conn = new mysqli(DB_HOST,DB_USER,DB_PASSWORD, DB_NAME);
		
		if(mysqli_connect_errorno())
		{
			echo "Failed to connect with database".mysqli.connect_err();
		}
		
		return $this->conn;
	}
}
