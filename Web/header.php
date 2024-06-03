<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="/styles/style.css" rel="stylesheet" />
<?php
    $menuItems = array(
        'Home' => $_SESSION['rootpath'] . "/index.php"
    );
    if (empty($_SESSION['role'])){
        $_SESSION['role'] = 'user';
    }
    if ($_SESSION['role'] == 'admin'){
        $menuItems['Manage Users'] = $_SESSION['rootpath'] . "/admin/users.php";
    }
    if ($_SESSION['role'] == 'teacher' || $_SESSION['role'] == 'admin'){
        $menuItems['Manage Games'] = $_SESSION['rootpath'] . "/manage_games.php";
    }
    if (isset($_SESSION['id'])){
        $menuItems['Play'] = $_SESSION['rootpath'] . '/home.php';
        $menuItems['Log Out'] = $_SESSION['rootpath'] . "/logout.php";
    }
    else{
        $menuItems['Log In'] = $_SESSION['rootpath'] . "/login.php";
    }
?>
<style>
    .active {
        font-weight: bold;
        color: white;
        background-color:#fc6e51
    }
</style>
<div class="text-[#fc6e51] w-full font-bold text-[1.5rem] md:text-[3rem] px-3 md:px-8 py-[1rem] relative
  flex md:flex-col flex-row items-center md:justify-center justify-between relative">
    <a href="<?php echo $_SESSION['rootpath'] . "/index.php"?>" class="grow text-left md:text-center truncate text-[#fc6e51]">
      ​Text Adventure Game for Education
    </a>
    <div class="flex md:hidden">
      <a href="#" class="menu-toggle" id="menu-toggle"><span></span></a>
    </div>
    <div id="menu-list" class="left-0 md:hidden overflow-hidden py-0 z-[1] flex flex-col absolute bg-white w-full top-[100%] transition-all h-0">
    <?php
        $activePage = basename($_SERVER['PHP_SELF']);
        if ($activePage == "play.php")
            $activePage = "home.php";
        foreach ($menuItems as $menuItem => $url){
            $isActive = ($activePage == basename($url)) ? 'active' : '';
            if ($isActive)
                echo "<a href='$url' class='text-white py-4 grow text-center uppercase bg-[#fc6e51]'>$menuItem</a>";
            else
                echo "<a href='$url' class='rounded-md text-black hover:text-white p-2 text-center uppercase hover:bg-[#fc6e51]'>$menuItem</a>";
        }
    ?>
    </div>
    <div class="w-full md:flex hidden items-center justify-center gap-6 text-[1.3rem]">
        <div class="h-[2px] opacity-50 bg-[#bbbbbb] grow"></div>
    <?php
        $activePage = basename($_SERVER['PHP_SELF']);
        if ($activePage == "play.php")
            $activePage = "home.php";
        foreach ($menuItems as $menuItem => $url){
            $isActive = ($activePage == basename($url)) ? 'active' : '';
            if ($isActive)
                echo "<a href='$url' class='p-2 bg-[#fc6e51] text-white uppercase rounded-md'>$menuItem</a>";
            else
                echo "<a href='$url' class='rounded-md text-black hover:text-white p-2 text-center uppercase hover:bg-[#fc6e51]'>$menuItem</a>";
        }
    ?>
    <div class="h-[2px] opacity-50 bg-[#bbbbbb] grow" ></div>
    </div>
  </div>

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