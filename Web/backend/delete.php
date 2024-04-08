<?php
include('connect.php');

// Handle file deletion
if ($_SERVER["REQUEST_METHOD"] == "GET") {
    $id = $_GET["id"];

    // Retrieve file details
    $sql = "SELECT * FROM games WHERE id='$id'";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();

        // Delete file from the server
        $filePath = "../assets/games/" . $row["file_name"];
        if (file_exists($filePath)) {
            unlink($filePath);
        }

        // Delete file record from the database
        $sqlDelete = "DELETE FROM games WHERE id='$id'";

        if ($conn->query($sqlDelete) === TRUE) {
            die(json_encode(['success' => 'File deleted successfully!', 'error' => ""]));
        } else {
            die(json_encode(['success' => '', 'error' => "Error deleting file: " . $conn->error]));
        }
    } else {
        die(json_encode(['success' => '', 'error' => "File not found."]));
    }
}

$conn->close();