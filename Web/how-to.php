<?php
include('config.php');
include('backend/connect.php');
?>
<meta charset="utf-8">

<script src="https://cdn.tailwindcss.com"></script>

<?php
include('header.php');
?>

<div class="md:m-[160px] m-4 mt-[96px]">
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
<script src="game-disks/demo-disk.js"></script>

<!-- Load disk into engine -->
<script>loadDisk(demoDisk);</script>