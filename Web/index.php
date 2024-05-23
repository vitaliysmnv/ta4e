<?php
session_start();
?>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<script src="https://cdn.tailwindcss.com"></script>
<?php
  $webPath = $_SERVER['PHP_SELF'];
  $_SESSION['rootpath'] = dirname($webPath);
  if ($_SESSION['rootpath']=="\\")
    $_SESSION['rootpath'] = "";
  include('header.php');
?>

  <div class="relative w-full ">
    <img src="assets/img/bg.webp" alt="bg" class="w-full h-auto" />
    <div class="absolute font-bold text-[#fff] top-[30%] text-center w-[90%] ml-[5%] text-[1.7rem] md:text-[3.2rem]">
      Unlock Learning Through Epic Adventures
    </div>
  </div>
  <div class="px-3 md:px-8 mt-10 container mx-auto">
    <div class="text-[1.9rem] md:text-[3.4rem] text-[#fc6e51] text-center mb-6">
      Welcome to A New Era of Learning
    </div>
    <div class="text-[1rem] text-grey text-center md:text-[1.5rem]">
      At Text Adventure Game for Education, we believe in transforming the traditional education model into an engaging, interactive journey. Founded by students at Oregon State University, we harness the power of text-based adventure games to enhance learning outcomes and make education an unforgettable adventure.
    </div>
  </div>
  <hr class="my-16 w-[90%] mx-auto" />
  <div class="px-3 md:px-8 mb-16 mx-auto container">
    <div class="text-[1.9rem] md:text-[3.4rem] text-[#fc6e51] text-center mb-6">
      About us
    </div>
    <div class="text-[1rem] text-grey text-center md:text-[1.5rem]">
      Founded by passionate students at Oregon State University, Text Adventure Game for Education is at the forefront of educational innovation. We leverage the immersive power of text-based adventures to foster a deeper connection with learning materials. Our mission is to transform traditional education into an engaging, interactive experience that prepares students for real-world challenges through critical thinking and problem-solving skills. Through our unique approach, we aim to make learning an adventure that students look forward to.
    </div>
  </div>
  <hr class="mt-16 w-[90%] mx-auto " />
  <footer class="px-3 md:px-8 py-6">
  © 2024 Text Adventure Game for Education
  </footer>

<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding:wght@700&family=Work+Sans&display=swap"
  rel="stylesheet">


