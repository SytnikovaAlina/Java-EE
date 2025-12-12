<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.Role"%>
<%@ page import="domain.Department"%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Редактирование данных кафедры</title>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Список Должностей</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
<jsp:include page="/views/header.jsp" />
<div class="container-fluid">
<div class="row justify-content-start ">
<div class="col-8 border bg-light px-4">
<h3>Список сотрудников</h3>
<table class="table">
<thead>
<th scope="col">Код</th>
<th scope="col">ФИО</th>
<th scope="col">Кафедра</th>
<th scope="col">Должность</th>
<th scope="col">Телефон</th>
</thead>
<tbody>
<c:forEach var="dep" items="${deps}">
<tr>
<td>${dep.getId()}</td>
<td>${dep.getName()}</td>
<td>${dep.getDepname()}</td>
<td>${dep.getRole()}</td>
<td>${dep.getTel()}</td>

</tr>
</c:forEach>
</tbody>
</table>
</div>
<div class="col-4 border px-4">
<form method="POST" action="">
<h3>Редактирование данных</h3>
<br>
<div class="mb-3 row">
<label for="iddep" class="col-sm-3 col-form-label">
Код сотрудника</label>
<div class="col-sm-7">
<input type="text" class="form-control" readonly
id="iddep" value="${DepartmentEdit.getId()}" />
</div>
</div>
<div class="mb-3 row">
<label for="Name" class="col-sm-3 col-form-label">
ФИО</label>
<div class="col-sm-7">
<input type="text" class="form-control" id="staticName"
name="Name" value="${DepartmentEdit.getName()}"/>
</div>
</div>
<div class="mb-3 row">
<label for="Depname" class="col-sm-3 col-form-label">
Кафедра</label>
<div class="col-sm-7">
<input type="text" class="form-control" id="staticDepname"
name="Depname" value="${DepartmentEdit.getDepname()}"/>
</div>
</div>
<div class="mb-3 row">
<label for="rolename" class="col-sm-3 col-form-label">
Должность</label>
<div class="col-sm-7">
<select name="role" class="form-control">
    <option value="">Выберите должность</option>
    <c:forEach var="role" items="${roles}">
        <option value="${role.id}">
            ${role.name}
        </option>
    </c:forEach>
</select>


</div>
</div>
<div class="mb-3 row">
<label for="phone" class="col-sm-3 col-form-label">
Телефон</label>
<div class="col-sm-7">
<input type="text" class="form-control" id="staticphone"
name="phone" value="${DepartmentEdit.getTel()}"/>
</div>
</div>
<p>
<br>
<button type="submit" class="btn btn-primary">Редактировать</button>
<a href="<c:url value='/department'/>" role="button" class="btn btn-secondary">Отменить</a>

</p>
</form>
</div>
</div>
</div>
<jsp:include page="/views/footer.jsp" />
</div>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"
ntegrity="sha384-8jhV9sqXw1spc51L+HqDuRRny47Xpa1Q9FsO" crossorigin="anonymous"></script>
<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsJTQexvpmTbbCpLQ60L" crossorigin="anonymous"></script>

</body>
</html>