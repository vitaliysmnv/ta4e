<?php
session_start();
if (isset($_POST["email"])) {
    include('connect.php');
    $sql = "SELECT * FROM users WHERE email='" . $_POST["email"] . "'";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        // output data of each row
        while ($row = $result->fetch_assoc()) {
            if (password_verify($_POST["password"], $row["password"])) {
                // Password is correct
                $_SESSION["id"] = $row["id"];
                $_SESSION["firstname"] = $row["first_name"];
                $_SESSION["lastname"] = $row["last_name"];
                $_SESSION["role"] = $row["role"];
                $_SESSION["email"] = $row["email"];
                die(json_encode(['success' => 'Success', 'error' => ""]));
            } else {
                // Password is incorrect
                die(json_encode(['success' => '', 'error' => "Invalid Password"]));
            }
        }
    } else {
        die(json_encode(['success' => '', 'error' => "Invalid email"]));
    }
    $conn->close();
} else {
    // header("Location: ../index.php");
    die(json_encode(['success' => '', 'error' => "no data"]));
}
