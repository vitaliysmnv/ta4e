<?php
include('config.php');
include('backend/connect.php');
?>
<meta charset="utf-8">

<script src="https://cdn.tailwindcss.com"></script>

<?php
  include('header.php');
?>

<div class="md:m-[160px] m-4 mt-[96px] h-full overflow-auto">
  <div class="font-bold text-[black] text-[1.5rem]">Game List</div>
  <div class="p-4 flex flex-col">
    <?php
    $sql = "SELECT * FROM games ORDER BY created_at DESC ";
    $result = $conn->query($sql);
    if ($result->num_rows > 0) {
      // output data of each row
      while ($row = $result->fetch_assoc()) {
        ?>
        <a class="text-blue-500 border-b-[1px] w-fit" href="play.php?game=<?php echo $row['id']; ?>">
          <?php echo $row['title']; ?>
        </a>
        <?php
      }
    } else {
      ?>
      <div class="flex justify-center">
        No game
      </div>
      <?php
    }
    ?>
  </div>
</div>


<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@700&family=Work+Sans&display=swap"
  rel="stylesheet" />

