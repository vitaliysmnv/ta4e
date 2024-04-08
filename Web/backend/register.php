<?php
session_start();
if (isset($_POST["firstname"])) {
    include('connect.php');
    $isAdmin = 'user';
    if (isset($_POST['isAdmin'])) {
        $isAdmin = 'admin';
    }
    $sqlc = "SELECT * FROM users WHERE email='" . $_POST['email'] . "'";
    $resultc = $conn->query($sqlc);
    if ($resultc->num_rows > 0) {
        die(json_encode(['success' => '', 'error' => "That email is duplicated."]));
    } else {
        $hashed_password = password_hash($_POST['password'], PASSWORD_DEFAULT);
        $sql = "INSERT INTO `users` (`id`, `email`, `first_name`, `last_name`, `role`, `password`, `is_allowed`) VALUES (NULL, '" . $_POST['email'] . "', '" . $_POST['firstname'] . "', '" . $_POST['lastname'] . "', '" . $isAdmin . "', '" . $hashed_password . "', 'n');";

        if ($conn->query($sql) === TRUE) {
            $_SESSION['registered_email'] = $_POST['email'];
            $_SESSION['registered_psw'] = $_POST['password'];
            die(json_encode(['success' => 'Success', 'error' => ""]));
        } else {
            die(json_encode(['success' => '', 'error' => "Error: " . $sql . "<br>" . $conn->error]));
        }
    }

    $conn->close();
} else {
    die(json_encode(['success' => '', 'error' => "no data"]));
}