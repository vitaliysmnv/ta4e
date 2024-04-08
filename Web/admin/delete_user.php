<?php
include('../backend/connect.php');
// Handle user deletion
if ($_SERVER["REQUEST_METHOD"] == "GET") {
    $id = $_GET["id"];

    // Retrieve user details
    $sql = "SELECT * FROM users WHERE id='$id'";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {

        // Delete user record from the database
        $sqlDelete = "DELETE FROM users WHERE id='$id'";

        if ($conn->query($sqlDelete) === TRUE) {
            die(json_encode(['success' => 'User deleted successfully!', 'error' => ""]));
        } else {
            die(json_encode(['success' => '', 'error' => "Error deleting user: " . $conn->error]));
        }
    } else {
        die(json_encode(['success' => '', 'error' => "User not found."]));
    }
}

$conn->close();
?>