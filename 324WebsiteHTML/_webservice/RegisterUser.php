<?php 

$servername = "localhost";
$username = "root";
$password = "Root";
$dbname = "mydb";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname) or die("Connection Failed");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	
	if(isset($_POST['firstName']) && isset($_POST['lastName']) && isset($_POST['contactNum']) && isset($_POST['email']) && isset($_POST['username']) && isset($_POST['password'])){
			registerUser($_POST['firstName'], $_POST['lastName'], $_POST['username'], $_POST['password'], $_POST['contactNum'], $_POST['email']);
	}
}
 
function registerUser($firstName, $lastName, $username, $password, $contactNum, $email){
		
	global $conn;

	$stmt = $conn->prepare("INSERT INTO `user`(`USER_ID`, `FIRST_NAME`, `LAST_NAME`, `CONTACT_NUM`, `EMAIL`, `USERNAME`, `U_PASSWORD`) VALUES (NULL,?,?,?,?,?,?);");
	$stmt->bind_param("ssssss", $firstName, $lastName, $contactNum, $email, $username, $password);
	if($stmt->execute()){
		$sql = "SELECT U.USER_ID FROM USER U WHERE '". $username. "' = U.USERNAME";
		$result = $conn->query($sql);
		$row = $result->fetch_assoc();
		echo $row['USER_ID'];
	}
	else{
		echo "Unsuccessful";
	}

}

$conn->close();
?>