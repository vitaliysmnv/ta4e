<?php
include("../config.php");
if (!isset($_SESSION['role']) || $_SESSION['role'] != 'admin') {
    header("Location: index.php");
}
include("../backend/connect.php");

// Handle user role update
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $id = $_POST["id"];
    $newRole = $_POST["newRole"];

    // Update user role in the database
    $sql = "UPDATE users SET role='$newRole' WHERE id='$id'";

    if ($conn->query($sql) === TRUE) {
        header("Location: users.php");
        exit();
    } else {
        echo "Error updating user role: " . $conn->error;
    }
}

// Retrieve user details
$id = $_GET["id"];
$sql = "SELECT * FROM users WHERE id='$id'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    $row = $result->fetch_assoc();
} else {
    echo "User not found.";
    exit();
}

?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <title>Edit User</title>
</head>

<body class="bg-gray-100">
    <?php
    include('header.php');
    ?>
    <div class="p-8">

        <h1 class="text-2xl font-bold mb-4 pt-8 px-8">Edit User</h1>

        <form action="edit_user.php" method="post" class="max-w-lg mx-auto pb-8 px-8">
            <input type="hidden" name="id" value="<?php echo $row["id"]; ?>">

            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="newRole">New Role:</label>
                <select name="newRole" id="newRole"
                    class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
                    <option value="user" <?php echo ($row["role"] == "user") ? "selected" : ""; ?>>User</option>
                    <option value="teacher" <?php echo ($row["role"] == "teacher") ? "selected" : ""; ?>>Teacher</option>
                </select>
            </div>

            <button type="submit"
                class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Save</button>
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</body>

</html>

<?php
$conn->close();
?>