<div class="w-full flex items-center h-[64px] bg-[#1c2833] text-white justify-between">
    <a href="<?php echo $root_url; ?>" class="px-4 text-white text-[1.5rem] font-bold">Text Adventure</a>
    <div class="">
        <?php
        if ($_SESSION['role'] == 'admin') {
            ?>
            <a class="px-4 text-[1rem]" href="admin/users.php">Manage Users</a>
            <?php
        }
        if ($_SESSION['role'] == 'teacher' || $_SESSION['role'] == 'admin') {
            ?>
            <a class="px-4 text-[1rem]" href="manage_games.php">Manage Games</a>
            <?php
        }
        ?>
        <a class="px-4 text-[1rem]" href="logout.php">Log out</a>
    </div>
</div>