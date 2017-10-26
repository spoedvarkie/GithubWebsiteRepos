<?php

class DBOperations
{
	private $conn;
	
	function construct()
	{
		require_once direname(_FILE_).'DBConnect.php';
		$db = new DBConnect();
		$this->conn = $db->connect();
	}
	
	//function destruct()
	//{
	//	mysql_close($this-connection);
	//}
	
	function createUser($username,$passw,$f_name, $l_name,$cell_num,$email)
	{
		$passw = md5($passw);
		$qry = $this->conn->prepare("INSERT INTO 'user'('USER_ID','FIRST_NAME','LAST_NAME','CONTACT_NUM',
														'EMAIL','USERNAME','U_PASSWORD')
									VALUES(NULL,?,?,?,?,?,?);");
		$qry->bind_param("ssssss",$first_name,$last_name,$contact_num,$email,$username,$password);
		
		if($qry->execute())
			return true;
			
		else
			return false;
	}
	
	function userLogin($username,$passw)
	{	//made 2 methods to verify login recommend using 1st
		//1st method
		session_start();
		$logged = false;
		if(!isset($_POST['username'],$_POST['password']))//checks that value has been entered	
			$message = "Please enter a valid username and password";		
		else		
			$passw = md5($passw);//encrypt passw using md5, could use sha1 also
			
			//echo "password " .$passw;
			//echo "\nusername " .$username;
			try
			{
				$qryLogin = $this->conn->prepare("SELECT USERNAME,U_PASSWORD 
												FROM 'user'
												WHERE username = ? AND U_PASSWORD = ?;");
				$qryLogin->bind_param($username,$passw);		
		
				$qryLogin->execute();
			
				$userID = $qryLogin->fetchColumn();
		
				if($userID == false)
				{
					$message = "Login Failed";
					
				}	
				else
				{                    
					$_SESSION['userID'] = $userID;                    
					$message = 'You are now logged in';	
					$logged= True;
				}
			}
			catch(Exception $e)//unable to access db most probably
			{            
				$message = 'Unexpected error. Please try again later.';				
			}
		echo @message;
		echo json_encode($logged);
		
		//2nd method
		//$username = $_POST['username'];
		//$passw = $_POST['password'];
		//$passw = md5($passw);
		
		//echo "password " .$passw;
		//echo "\nusername " .$username;
		
		//$qry = "SELECT * 
		//		  FROM user 
		//		  WHERE username='$username' AND password= '".md5($_POST['password'])."'");	
		//$result = mysql_query($sql);//get values returned
		//$count = mysql_num_row($result);//count rows returned
		
		//if($count==1)
		//{
		//	$_SESSION['userName'] = $username;
		//  exit();
		//}
		//else
		//	echo "Wrong username"
	}

	function getTextbook($textBookID)
	{
		//first get book details
		$qryTxtBook = $this->conn->prepare("SELECT * 
						FROM 'textbook' 
						WHERE 'TEXTBOOK_ID'=?;");
		$qryTxtBook->bind_param($textBookID);
		//$qryTxtBook->bind_param($textBookID);
		
		if ($query_run=mysql_query($qryTxtBook))
		{
			while ($Bookrows = mysql_fetch_assoc($qryTxtBook))
			{
				$title = $Bookrows['TEXTBOOK_TITLE'];
				$ed = $Bookrows['TEXTBOOK_EDITION'];
				$isbn = $Bookrows['TEXTBOOK_ISBN'];
				$price = $Bookrows['TEXTBOOK_PRICE'];
				$comment = $Bookrows['TEXTBOOK_COMMENTS'];
				$userID = $Bookrows['USER_ID'];
			
				//echo "$TEXTBOOK_TITLE$<br>$TEXTBOOK_EDITION<br>$TEXTBOOK_ISBN<br>$TEXTBOOK_PRICE<br>$TEXTBOOK_COMMENTS<br><br>";	

			
				//get details of user that added book
				$qryUser = $this->conn->prepare("SELECT 'FIRST_NAME','LAST_NAME','CONTACT_NUM','EMAIL' 
												FROM 'USER' 
												WHERE 'USER_ID'=?;");
				$qryUser->bind_param($textBookID);
			
				$Userrows = mysql_fetch_array($qryUser);
			
				$first_name = $Userrows['FIRST_NAME'];
				$last_name = $Userrows['LAST_NAME'];
				$contact_num = $Userrows['CONTAC_NUM'];
				$email = $Userrows['EMAIL'];
				
				//echo "$FIRST_NAME$<br>$LAST_NAME<br>$CONTACT_NUM<br>$EMAIL<br><br>";								
			}
		}
		
					
	}
}
?>