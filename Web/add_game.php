<?php
include('config.php');
if (!isset($_SESSION['role']) || $_SESSION['role'] == 'user') {
    header("Location: index.php");
}
include('backend/connect.php');
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
    <div class="flex justify-end w-full px-8 py-4">
        <a href="how-to.php" class="text-blue-500 hover:underline">How to make a new game...</a>
    </div>
    <form id="fileUploadForm" class="max-w-lg mx-auto mt-8" enctype="multipart/form-data">
        <div class="mb-4">
            <label class="block text-gray-700 text-sm font-bold mb-2" for="file">Select File:</label>
            <input type="file" name="file" id="file"
                class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
        </div>

        <div class="mb-4">
            <label class="block text-gray-700 text-sm font-bold mb-2" for="title">File Title:</label>
            <input type="text" name="title" id="title"
                class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
        </div>

        <button type="button" id="uploadBtn"
            class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Upload</button>
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
        $(document).ready(function () {
            $("#uploadBtn").on("click", function () {
                var formData = new FormData($("#fileUploadForm")[0]);

                $.ajax({
                    type: 'POST',
                    url: 'backend/upload.php',
                    data: formData,
                    contentType: false,
                    processData: false,
                    success: function (response) {
                        const data = JSON.parse(response)
                        if (data.success) {
                            toastr["success"]("Saved successfully!");
                            $("#fileUploadForm")[0].reset();
                        } else {
                            toastr["error"](data.error);
                        }
                    },
                    error: function (error) {
                        console.error(error);
                        // Handle error, show an error message
                    }
                });
            });
        });

    </script>
</body>

</html>