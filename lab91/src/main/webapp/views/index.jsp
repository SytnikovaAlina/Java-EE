<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><!DOCTYPE html>

<html lang="ru">
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <meta charset="UTF-8">
    <!-- Настройка viewport -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Управление персоналом</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/header.jsp" />
    <div class="container">
        <br><br><br>
        <div class="list-group text-center py-3 px-3">
            <h2>Функции системы</h2>
            <ul class="list-group">
			    <li class="list-group-item list-group-item-primary">
			        <a href="${pageContext.request.contextPath}/department">Кафедры</a>
			    </li>
			    <li class="list-group-item list-group-item-primary">
			        <a href="${pageContext.request.contextPath}/facultet">Факультеты</a>
			    </li>
			    <li class="list-group-item list-group-item-primary">
			        <a href="${pageContext.request.contextPath}/role">Должности</a>
			    </li>
			</ul>

        </div>
        <br><br>
    </div>
   <jsp:include page="/views/footer.jsp" />
</div>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha384-8jhV9sqXw1spc51L+HqDuRRny47Xpa1Q9FsO" crossorigin="anonymous"></script>
<!-- Bootstrap JS + Popper JS -->
<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsJTQexvpmTbbCpLQ60L" crossorigin="anonymous"></script>
</body>
</html>