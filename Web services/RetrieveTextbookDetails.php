<?php 

$servername = "localhost";
$username = "root";
$password = "Root";
$dbname = "mydb";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname) or die("Connection Failed");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	
	if(isset($_POST['textbookID'])){
		retrieveUserTextbooks($_POST['textbookID']);
	}
}
 
function retrieveUserTextbooks($textbookID){
		
	global $conn;

	$sql = "SELECT DISTINCT T.TEXTBOOK_TITLE, T.TEXTBOOK_EDITION, T.TEXTBOOK_PRICE, T.TEXTBOOK_COMMENTS, A.AUTHOR_INITIALS, A.AUTHOR_L_NAME, T.USER_ID, U.USERNAME FROM textbook T, author A, textbook_author TA, USER U WHERE T.TEXTBOOK_ID = '". $textbookID. "' AND T.TEXTBOOK_ID = TA.TEXTBOOK_ID AND TA.AUTHOR_ID = A.AUTHOR_ID AND T.USER_ID = U.USER_ID";
	$result = $conn->query($sql);
	$row = $result->fetch_assoc();

	if ($row != null) {
        echo $row["TEXTBOOK_TITLE"]. ";". $row["TEXTBOOK_EDITION"]. ";". $row["TEXTBOOK_PRICE"]. ";". $row["AUTHOR_INITIALS"]. ". ". $row["AUTHOR_L_NAME"]. ";". $row["TEXTBOOK_COMMENTS"]. ";". $row["USERNAME"]. ";". $row["USER_ID"];
	} 
	else {
    	echo "0 results";
	}
}

$conn->close();
?>