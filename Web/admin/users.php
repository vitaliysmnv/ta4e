<?php
include("../config.php");
if (!isset($_SESSION['role']) || $_SESSION['role'] != 'admin') {
    header("Location: index.php");
}
include("../backend/connect.php");
// Retrieve user list from the database
$sql = "SELECT * FROM users where role <> 'admin'";
$result = $conn->query($sql);

?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="assets/css/toastr.css" rel="stylesheet">
    <title>User Management</title>
</head>

<body class="bg-gray-100">
    <?php
    include('../header.php');
    ?>
    <div class="p-8">
        <h1 class="text-2xl font-bold mb-4">User Management</h1>

        <table class="min-w-full bg-white border border-gray-300">
            <thead>
                <tr>
                    <th class="py-2 w-1/4 border-b">ID</th>
                    <th class="py-2 w-1/4 border-b">Username</th>
                    <th class="py-2 w-1/4 border-b">Role</th>
                    <th class="py-2 w-1/4 border-b">Actions</th>
                </tr>
            </thead>
            <tbody>
                <?php
                if ($result->num_rows > 0) {
                    while ($row = $result->fetch_assoc()) {
                        echo "<tr>";
                        echo "<td class='py-2 w-1/4 text-center border-b'>" . $row["id"] . "</td>";
                        echo "<td class='py-2 w-1/4 text-center border-b'>" . $row["first_name"] . " " . $row["last_name"] . "</td>";
                        echo "<td class='py-2 w-1/4 text-center border-b capitalize'>" . $row["role"] . "</td>";
                        echo "<td class='py-2 w-1/4 text-center border-b'><a href='edit_user.php?id=" . $row["id"] . "' class='text-blue-500 hover:underline'>Edit</a> | <a href='#' class='text-red-500 hover:underline delete-user' data-user-id='" . $row["id"] . "'>Delete</a></td>";
                        echo "</tr>";
                    }
                } else {
                    echo "<tr><td colspan='4' class='py-2 w-1/4 border-b text-center'>No users found</td></tr>";
                }
                ?>
            </tbody>
        </table>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
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

        $(document).ready(function () {
            // Handle user deletion
            $(".delete-user").on("click", function (e) {
                e.preventDefault();
                var userId = $(this).data("user-id");

                if (confirm("Are you sure you want to delete this user?")) {
                    $.ajax({
                        type: 'GET',
                        url: 'delete_user.php',
                        data: { id: userId },
                        success: function (response) {
                            console.log(response);
                            // Reload the page or update the user list dynamically
                            location.reload();
                        },
                        error: function (error) {
                            console.error(error);
                            // Handle error, show an error message
                        }
                    });
                }
            });
        });


    </script>
</body>

</html>

<?php
$conn->close();
?>