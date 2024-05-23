<?php

$servername = "143.198.216.104";
$username = "root";
$password = "";
$dbname = "ta4e";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die(json_encode(['success' => '', 'error' => "Connection failed: " . $conn->connect_error]));
}
