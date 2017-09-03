<?php
$servername = "localhost:3306";
$username = "root";
$password = "root";

// Create connection
$conn = new mysqli($servername, $username, $password);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
$books_db = mysqli_select_db($conn, "mydb") 
  or die("Could not select books");

$selectBook = $_POST["bookName"];  
  
$allOtherBooks = 	"SELECT T.TEXTBOOK_TITLE, T.TEXTBOOK_EDITION, T.TEXTBOOK_PRICE, 
							A.AUTHOR_INITIALS, A.AUTHOR_L_NAME, 
					FROM 	mydb.TEXTBOOK T, mydb.TEXTBOOK_AUTHOR T_A, mydb.AUTHOR A
					WHERE 	T.TEXTBOOK_TITLE '$selectBook' ";

$myResults = $conn->query($allOtherBooks);

if ($myResults)
{
	echo "<table width='500' border='0'>";

	echo "<tr>";
	echo "<td>";
		echo "<b>Book Name</b>"; 
	echo "</td>";
	echo "<td>";
		echo "<b>Category</b>"; 
	echo "</td>";
	echo "<td>";
		echo "<b>Price</b>"; 
	echo "</td>";
	echo "</tr>";
	
	while($row = $myResults->fetch_object())
	{ 
		echo "<tr>";
		echo "<td>";
			echo $row->BookName . " "; 
		echo "</td>";
		echo "<td>";
			echo $row->Category . " "; 
		echo "</td>";
		echo "<td>";
			echo $row->Price; 
		echo "</td>";
		echo "</tr>";
	} 
	
	echo "</table>";
}

$myResults->close();
$conn->close();
?>