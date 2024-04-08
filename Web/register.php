<?php
session_start();
if (isset($_SESSION['id'])) {
    header("Location: index.php");
}
?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Text Adventure</title>
    <meta content="Login page of Text Adventure" name="description">
    <meta content="Login-Text Adventure" name="keywords">
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="assets/css/toastr.css" rel="stylesheet">
</head>

<body class="flex flex-col justify-center items-center overflow-auto w-screen h-screen">

    <div>

        <div class="text-center mb-8">
            <h1 class="font-bold text-[1.5rem]">Create an Account</h1>
            <p>Enter your personal details to create account</p>
        </div>

        <form id="registerForm" class="sm:min-w-[500px] min-w-[90%] flex flex-col gap-4">
            <div class="flex flex-col gap-2">
                <label for="firstName">First Name</label>
                <input class="w-full p-2 outline-none rounded-md border" type="text" name="firstname" id="firstName"
                    required>
            </div>
            <div class="flex flex-col gap-2">
                <label for="lastName">Last Name</label>
                <input class="w-full p-2 outline-none rounded-md border" type="text" name="lastname" id="lastName"
                    required>
            </div>

            <div class="flex flex-col gap-2">
                <label for="yourEmail">Your Email</label>
                <input class="w-full p-2 outline-none rounded-md border" type="email" name="email" id="yourEmail"
                    required>
            </div>

            <div class="flex flex-col gap-2">
                <label for="yourPassword">Password</label>
                <input class="w-full p-2 outline-none rounded-md border" type="password" name="password"
                    id="yourPassword" required>
            </div>

            <div class="flex flex-col gap-2">
                <div class="flex items-center justify-start gap-[5px]">
                    <input class="" name="terms" type="checkbox" value="" id="acceptTerms" required>
                    <label for="acceptTerms">I agree and accept the <a href="#">terms and
                            conditions</a></label>
                </div>
            </div>
            <div>
                <button class="p-2 w-full bg-[#15a57d] text-white font-bold rounded-md" type="submit">Create
                    Account</button>
            </div>
            <div>
                <p class="flex justify-center">Already have an account?&nbsp;<a class="text-[blue]" href="login.php">Log
                        in</a></p>
            </div>
        </form>

    </div>
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

        $(document).on('submit', '#registerForm', function (e) {
            e.preventDefault();
            $.ajax({
                url: 'backend/register.php',
                type: 'POST',
                data: new FormData(document.getElementById('registerForm')),
                dataType: 'json',
                cache: false,
                contentType: false,
                processData: false,
                success: function (data) {
                    if (data.success) {
                        toastr["success"]("Registered successfully!");
                        setTimeout(() => {
                            window.location.href = "login.php";
                        }, 5000);
                    } else {
                        console.log(data)
                        toastr["error"](data.error);
                    }
                },
                error: function (err) {
                    console.log('err', err)
                    // Log the error, show an alert, whatever works for you
                }
            });
        })
    </script>

</body>

</html>