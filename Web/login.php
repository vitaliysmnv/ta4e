<?php
session_start();
if (isset($_SESSION['id'])) {
    header("Location: home.php");
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
    <h1 class="font-bold text-[1.5rem] mb-8">Please login to your account</h1>
    <form id="loginForm" action="#" class="sm:min-w-[500px] min-w-[90%] flex flex-col gap-4">
        <div class="flex flex-col gap-2">
            <label for="yourUsername">Email</label>
            <div>
                <input class="w-full p-2 outline-none rounded-md border" type="email" <?php if (isset($_SESSION['registered_email'])) { ?> value="<?php echo $_SESSION['registered_email']; ?>"
                    <?php } ?> name="email" aria-label="Email" id="email" autocomplete="off" required>
            </div>
        </div>
        <div class="flex flex-col gap-2">

            <label for="yourPassword">Password</label>
            <input class="w-full p-2 outline-none rounded-md border" type="password" <?php if (isset($_SESSION['registered_psw'])) { ?> value="<?php echo $_SESSION['registered_psw']; ?>" <?php } ?>
                name="password" aria-label="Password" id="yourPassword" autocomplete="off" required>
        </div>
        <div class="flex flex-col gap-2">
            <button class="p-2 bg-[#15a57d] text-white font-bold rounded-md" type="submit">Login</button>
        </div>
        <div>
            <p class="flex justify-center">No account yet?&nbsp;<a class="text-[blue]" href="register.php">Create an
                    account</a></p>
        </div>
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

        $(document).on('submit', '#loginForm', function (e) {
            e.preventDefault();
            $.ajax({
                url: 'backend/login.php',
                type: 'POST',
                data: new FormData(document.getElementById('loginForm')),
                dataType: 'json',
                cache: false,
                contentType: false,
                processData: false,
                success: function (data) {
                    if (data.success) {
                        toastr["success"]("Logged in successfully!");
                        window.location.href = 'index.php';
                    } else {
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