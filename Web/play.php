<?php
include('config.php');
include('backend/connect.php');
?>
<meta charset="utf-8">

<script src="https://cdn.tailwindcss.com"></script>

<?php
include('header.php');
?>

<div class="m-[160px] mt-[96px]">
  <!-- Game content -->
  <div id="output"></div>

  <!-- Player input -->
  <div class="input">
    <span class="ml-2">> </span><input id="input" autofocus spellcheck="false">
  </div>

</div>

<!-- CSS styles -->
<link id="styles" rel="stylesheet" type="text/css" href="styles/modern.css">

<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@700&family=Work+Sans&display=swap"
  rel="stylesheet">

<!-- Include game engine -->
<script src="index.js"></script>

<!-- Include game data ('disk') -->
<?php
$sql = "SELECT * FROM games WHERE id='" . $_GET['game'] . "' ";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
  // output data of each row
  while ($row = $result->fetch_assoc()) {
    ?>
    <script src="assets/games/<?php echo $row['file_name']; ?>"></script>
    <?php
  }
}
?>

<!-- Load disk into engine -->
<script>loadDisk(demoDisk);</script>