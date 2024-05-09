<meta name="viewport" content="width=device-width, initial-scale=1.0" />


<div class="text-[#fc6e51] w-full font-bold text-[1.5rem] md:text-[3rem] px-3 md:px-8 py-[1rem] relative
  flex md:flex-col flex-row items-center md:justify-center justify-between relative">
    <a href="<?php echo $root_url; ?>" class="grow text-left md:text-center truncate">
      ​Text Adventure Game for Education
    </a>
    <div class="flex md:hidden">
      <a href="#" class="menu-toggle" id="menu-toggle"><span></span></a>
    </div>
    <div id="menu-list" class="left-0 md:hidden overflow-hidden py-0 z-[1] flex flex-col absolute bg-white w-full top-[100%] transition-all h-0">
      <a href="<?php echo $root_url; ?>" class="text-white py-4 grow text-center uppercase bg-[#fc6e51]"  >Home</a>
      <?php
        if (empty($_SESSION['role']))
        $_SESSION['role'] = 'user';
      if ($_SESSION['role'] == 'admin') {
          ?>
          <a class="text-black hover:text-white py-4 grow text-center uppercase hover:bg-[#fc6e51]" href="/admin/users.php">Manage Users</a>
          <?php
      }
      if ($_SESSION['role'] == 'teacher' || $_SESSION['role'] == 'admin') {
          ?>
          <a class="text-black hover:text-white py-4 grow text-center uppercase hover:bg-[#fc6e51]" href="/manage_games.php">Manage Games</a>
          <?php
      }
      ?>
      <?php 
        if (isset($_SESSION['id'])) {
      ?>
      <a href="/logout.php" class="text-black hover:text-white py-4 grow text-center uppercase hover:bg-[#fc6e51]" >Log Out</a>
      <?php
        } else {
      ?>
        <a href="/login.php" class="text-black hover:text-white py-4 grow text-center uppercase hover:bg-[#fc6e51]" >LogIn</a>
      <?php
        }
      ?>
    </div>
    <div class="w-full md:flex hidden items-center justify-center gap-6 text-[1.3rem]">
        <div class="h-[2px] opacity-50 bg-[#bbbbbb] grow"></div>
        <a href="/" class="p-2 bg-[#fc6e51] text-white uppercase rounded-md">Home</a>
        <?php
        if ($_SESSION['role'] == 'admin') {
            ?>
            <a class="rounded-md text-black hover:text-white p-2 text-center uppercase hover:bg-[#fc6e51]" href="/admin/users.php">Manage Users</a>
            <?php
        }
        if ($_SESSION['role'] == 'teacher' || $_SESSION['role'] == 'admin') {
            ?>
            <a class="<?php echo isset($_SESSION['page']) && $_SESSION['page'] == 'managegame' ? 'text-white py-4 grow text-center uppercase bg-[#fc6e51]' : 'rounded-md text-black hover:text-white p-2 text-center uppercase hover:bg-[#fc6e51]' ?>" href="/manage_games.php">Manage Games</a>
            <?php
        }
        ?>
        <?php 
          if (isset($_SESSION['id'])) {
        ?>
        <a href="/logout.php" class="rounded-md text-black hover:text-white p-2 text-center uppercase hover:bg-[#fc6e51]" >Log Out</a>
        <?php
          } else {
        ?>
          <a href="/login.php" class="rounded-md text-black hover:text-white p-2 text-center uppercase hover:bg-[#fc6e51]" >LogIn</a>
        <?php
          }
        ?>
        <div class="h-[2px] opacity-50 bg-[#bbbbbb] grow" ></div>
    </div>
  </div>

<link href="../styles/style.css" rel="stylesheet" />

<script>
  document.getElementById('menu-toggle').addEventListener(
  'click',
  function(e) {
    e.preventDefault()
    this.classList.toggle('menu-toggle-active');
    document.getElementById('menu-list').classList.toggle('!h-auto');
  }
);

</script>