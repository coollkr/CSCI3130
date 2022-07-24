<?php
	/*
	 *	db.php
	 *	Connects to the Database on localhost:3306.
	 */
	$host = "localhost:3306";
	$username = "root";
	$password = "root";
	$dbname = "ga_db";

	$dbconn  = new mysqli($host, $username, $password, $dbname);

	if ($dbconn->connect_error) {
		die("Nooooooooo!" . $dbconn->connect_error);
	}
	// 	else {
	// 	echo "<p>Connected!</p>";
	// }

?>