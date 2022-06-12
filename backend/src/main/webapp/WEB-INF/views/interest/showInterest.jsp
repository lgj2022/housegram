<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- FontAwesome Link -->
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.15.3/css/all.css"
      integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk"
      crossorigin="anonymous"
    />
    <!-- Bootstrap Link -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
      crossorigin="anonymous"
    />
    <!-- CSS Link -->
    <link rel="stylesheet" href="../css/reset.css" />
    <link rel="stylesheet" href="../css/showBookmark.css" />
    <link rel="stylesheet" href="../css/navbar.css" />
    <!-- Google Font font-family: 'Lobster', cursive;-->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Lobster&display=swap"
      rel="stylesheet"
    />
    <link
      href="https://fonts.googleapis.com/css2?family=Cookie&family=Great+Vibes&family=Lobster&display=swap"
      rel="stylesheet"
    />
    <!-- JS link -->
    <title>선호 지역 둘러보기</title>
  </head>
  <body>
    <nav>
      <div class="navbar">
        <div class="logo">
          <a href="../initPage.do"><i class="fas fa-leaf"></i>Happy House</a>
        </div>
        <ul class="menu">
          <li class="menu-item"><a href="./notice.html">공지 사항</a></li>
          <li class="menu-item addBookmark"><a href="#">관심 지역 설정</a></li>
          <li class="menu-item">
            <a href="./showBookmark.html">관심 지역 둘러보기</a>
          </li>
        </ul>
        <div class="user-account-container">
          <ul class="user-account-btn-list">
            <li class="user-account-btn active">
              <a href="./userInfo.html">유저정보</a>
            </li>
          </ul>
        </div>
        <i class="fas fa-bars"></i>
      </div>
    </nav>
    <select
      class="form-select"
      id="address"
      aria-label="Default select example"
      style="margin: 20px 0 20px 0; width: 100%"
    >
      <option selected>선호 지역을 선택하십시오</option>
    </select>
    <div
      id="map"
      style="width: 100%; height: 350px; margin: auto"
      class="mb-3"
    ></div>
    <div class="list-group" id="info"></div>
    <footer>Copyright by LEE MINWOO & KIM DAEWON in SSAFY 7th.</footer>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
      crossorigin="anonymous"
    ></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2a64d28290adc97c74e441e113c6f39e&libraries=services"
    ></script>
    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2a64d28290adc97c74e441e113c6f39e"
    ></script>
    <script src="../js/showBookmark.js"></script>
    <script src="./js/secondNav.js"></script>
  </body>
</html>
