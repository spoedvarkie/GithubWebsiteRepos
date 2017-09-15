<?php 

$servername = "localhost";
$username = "root";
$password = "Root";
$dbname = "mydb";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname) or die("Connection Failed");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	
	if(isset($_POST['userID'])){
		retrieveProfile($_POST['userID']);
	}
}
 
function retrieveProfile($userID){
		
	global $conn;

	$sql = "SELECT U.FIRST_NAME, U.LAST_NAME, U.USERNAME, U.CONTACT_NUM, U.EMAIL FROM USER U WHERE '". $userID. "' = U.USER_ID";
	$result = $conn->query($sql);
	$row = $result->fetch_assoc();

	if ($row != null) {
		echo $row["FIRST_NAME"]. ",". $row["LAST_NAME"]. ",". $row["USERNAME"]. ",". $row["CONTACT_NUM"]. ",". $row["EMAIL"]. "\r\n";
	} 
	else {
    	echo "0 results";
	}
}

$conn->close();
?>