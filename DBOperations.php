<?php

	class DBOperations{
		private $con;

		function __construct(){
			require_once dirname(__FILE__).'/DBConnect.php';

			$db = new DBConnect();

			$this->con = $db->connect();
		}

		/*CRUD -> C -> CREATE */

		function createUser($first_name, $last_name, $contact_num, $email, $username, $password){
			$password = md5($password);
			$stmt = $this->con->prepare("INSERT INTO `user`(`USER_ID`, `FIRST_NAME`, `LAST_NAME`, `CONTACT_NUM`, `EMAIL`, `USERNAME`, `U_PASSWORD`) VALUES (NULL,?,?,?,?,?,?);");

			$stmt->bind_param("ssssss", $first_name, $last_name, $contact_num, $email, $username, $password);

			if($stmt->execute()){
				return true;
			}
			else{
				return false;
			}
		}
	}