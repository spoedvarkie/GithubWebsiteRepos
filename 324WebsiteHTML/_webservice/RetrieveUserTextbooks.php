<?php 

$servername = "localhost";
$username = "root";
$password = "Root";
$dbname = "mydb";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname) or die("Connection Failed");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	
	if(isset($_POST['userID'])){
		retrieveUserTextbooks($_POST['userID']);
	}
}
 
function retrieveUserTextbooks($userID){
		
	global $conn;

	$sql = "SELECT DISTINCT T.TEXTBOOK_TITLE, T.TEXTBOOK_EDITION, T.TEXTBOOK_PRICE, A.AUTHOR_INITIALS, A.AUTHOR_L_NAME, T.TEXTBOOK_ID FROM textbook T, author A, textbook_author TA, USER U WHERE T.USER_ID = '". $userID. "' AND T.TEXTBOOK_ID = TA.TEXTBOOK_ID AND TA.AUTHOR_ID = A.AUTHOR_ID";
	$result = $conn->query($sql);

	if ($result->num_rows > 0) {
    // output data of each row
    	while($row = $result->fetch_assoc()) {
        	echo $row["TEXTBOOK_TITLE"]. ",". $row["TEXTBOOK_EDITION"]. ",". $row["TEXTBOOK_PRICE"]. ",". $row["AUTHOR_INITIALS"]. ". ". $row["AUTHOR_L_NAME"]. ",". $row["TEXTBOOK_ID"]. "\r\n";
    	}
	} 
	else {
    	echo "0 results";
	}
}

$conn->close();
?>