<?php 

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "mydb";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname) or die("Connection Failed");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	
	if(isset($_POST['username']) && isset($_POST['password'])){
		validateCredentials($_POST['username'], $_POST['password']);
	}
}
 
function validateCredentials($username, $password){
		
	global $conn;

	$sql = "SELECT U.USER_ID FROM USER U WHERE '". $username. "' = U.USERNAME AND '". $password. "' = U.U_PASSWORD";
	$result = $conn->query($sql);
	$row = $result->fetch_assoc();

	if ($row["USER_ID"] != null) {
        echo $row["USER_ID"];
	} 
	else {
    	echo "Incorrect credentials";
	}
}

$conn->close();
?>