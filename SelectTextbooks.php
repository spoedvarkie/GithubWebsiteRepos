<?php 

$servername = "localhost";
$username = "root";
$password = "Root";
$dbname = "mydb";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname) or die("Connection Failed");
 

$sql = "SELECT T.TEXTBOOK_TITLE, T.TEXTBOOK_EDITION, T.TEXTBOOK_PRICE, A.AUTHOR_INITIALS, A.AUTHOR_L_NAME FROM textbook T, author A, textbook_author TA WHERE T.TEXTBOOK_ID = TA.TEXTBOOK_ID AND TA.AUTHOR_ID = A.AUTHOR_ID";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo "Title: " . $row["TEXTBOOK_TITLE"]. "<br>". "Edition: " . $row["TEXTBOOK_EDITION"]. "<br>". "Price: R". $row["TEXTBOOK_PRICE"]. "<br>". "Author: ". $row["AUTHOR_INITIALS"]. ". ". $row["AUTHOR_L_NAME"]. "<br><br>";
    }
} 
else {
    echo "0 results";
}
$conn->close();
?>