<?php
session_start();
if (isset($_SESSION["id"])) {
    include('connect.php');


    // Handle file upload
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $title = $_POST["title"];
        $createdBy = $_SESSION['id'];

        $fileName = uniqid() . ".js";
        $filePath = "../assets/games/" . $fileName;

        if (move_uploaded_file($_FILES["file"]["tmp_name"], $filePath)) {
            // Insert data into the database
            $sql = "INSERT INTO games (created_by, file_name, title) VALUES ('$createdBy', '$fileName', '$title')";

            if ($conn->query($sql) === TRUE) {
                die(json_encode(['success' => 'Success', 'error' => ""]));
            } else {
                die(json_encode(['success' => '', 'error' => "Error: " . $sql . "<br>" . $conn->error]));
            }
        } else {
            die(json_encode(['success' => '', 'error' => "Sorry, there was an error uploading your photo."]));
        }
    }

    $conn->close();
} else {
    die(json_encode(['success' => '', 'error' => "Your session was expired. Please login again."]));
}