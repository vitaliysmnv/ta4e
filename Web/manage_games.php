<?php
include('config.php');
if (!isset($_SESSION['role']) || $_SESSION['role'] == 'user') {
  header("Location: index.php");
}
include('backend/connect.php');
?>
<meta charset="utf-8">

<script src="https://cdn.tailwindcss.com"></script>
<link href="assets/css/toastr.css" rel="stylesheet">

<?php
include('header.php');
?>

<div class="m-[160px] mt-[96px] h-full overflow-auto">
  <div class="flex justify-between">
    <div class="font-bold text-[black] text-[1.5rem]">Game List</div>
    <a href="add_game.php" class="text-[1rem] text-blue-600 hover:text-[blue]">Add A New Game</a>
  </div>
  <div class="p-4 flex flex-col">
    <table class="min-w-full bg-white border border-gray-300">
      <thead>
        <tr>
          <th class="py-2 w-1/3 text-center border-b">File Name</th>
          <th class="py-2 w-1/3 text-center border-b">Title</th>
          <th class="py-2 w-1/3 text-center border-b">Actions</th>
        </tr>
      </thead>
      <tbody>
        <?php
        $sql = "SELECT * FROM games ORDER BY created_at DESC ";
        $result = $conn->query($sql);
        if ($result->num_rows > 0) {
          while ($row = $result->fetch_assoc()) {
            echo "<tr>";
            echo "<td class='py-2 w-1/3 text-center border-b'>" . $row["file_name"] . "</td>";
            echo "<td class='py-2 w-1/3 text-center border-b'>" . $row["title"] . "</td>";
            echo "<td class='py-2 w-1/3 text-center border-b'><a href='edit.php?id=" . $row["id"] . "' class='text-blue-500 hover:underline'>Edit</a> | <a href='#' class='text-red-500 hover:underline delete-file' data-file-id='" . $row["id"] . "'>Delete</a></td>";
            echo "</tr>";
          }
        } else {
          echo "<tr><td colspan='3' class='py-2 px-4 border-b text-center'>No games found</td></tr>";
        }
        ?>
      </tbody>
    </table>

  </div>
</div>

<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@700&family=Work+Sans&display=swap"
  rel="stylesheet">
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
    // Handle file deletion
    $(".delete-file").on("click", function (e) {
      e.preventDefault();
      var fileId = $(this).data("file-id");

      if (confirm("Are you sure you want to delete this file?")) {
        $.ajax({
          type: 'GET',
          url: 'backend/delete.php',
          data: { id: fileId },
          success: function (response) {
            const data = JSON.parse(response)
            if (data.success) {
              toastr["success"]("Deleted successfully!");
              $(e.target).parent().parent().remove()
            } else {
              toastr["error"](data.error);
            }
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