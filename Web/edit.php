<?php
include('config.php');
if (!isset($_SESSION['role']) || $_SESSION['role'] == 'user') {
    header("Location: index.php");
}
include('backend/connect.php');

// Handle file name editing
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $id = $_POST["id"];
    $title = $_POST["title"];

    // Update file name in the database
    $sql = "UPDATE games SET title='$title' WHERE id='$id'";

    if ($conn->query($sql) === TRUE) {
        header("Location: manage_games.php");
        exit();
    } else {
        echo "Error updating file name: " . $conn->error;
    }
}

// Retrieve file details
$id = $_GET["id"];
$sql = "SELECT * FROM games WHERE id='$id'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    $row = $result->fetch_assoc();
} else {
    echo "File not found.";
    exit();
}

?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="assets/css/toastr.css" rel="stylesheet">

    <title>Text Adventure</title>
</head>

<body class="bg-gray-100">
    <?php
    include('header.php');
    ?>
    <h1 class="text-2xl font-bold mb-4">Edit File</h1>

    <form action="edit.php" method="post" class="max-w-lg mx-auto">
        <input type="hidden" name="id" value="<?php echo $row["id"]; ?>">

        <div class="mb-4">
            <label class="block text-gray-700 text-sm font-bold mb-2" for="title">New File Name:</label>
            <input required type="text" name="title" id="title" value="<?php echo $row["title"]; ?>"
                class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
        </div>

        <button type="submit"
            class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Save</button>
    </form>
    <script src="assets/js/jquery-3.7.0.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>

    <script>
        toastr.options = {
            "closeButton": false,
            "debug": false,
            "newestOnTop": false,
            "progressBar": true,
            "positionClass": "toast-top-right",
            "preventDuplicates": false,
            "onclick": null,
            "showDuration": "3000",
            "hideDuration": "1000",
            "timeOut": "5000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        }


    </script>
</body>

</html>
<?php
$conn->close();
?>