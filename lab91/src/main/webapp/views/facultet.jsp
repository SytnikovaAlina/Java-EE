<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.Role"%>
<%@ page import="domain.Facultet"%>
<%
    Role r1 = new Role("Заведующий кафедрой", 1L);
    Role r2 = new Role("Декан факультета", 2L);

    Role[] roles = new Role[]{r1, r2};
    pageContext.setAttribute("roles", roles);

    Facultet p1 = new Facultet(1L, "Ivanov V.V.", "KT&IB", "8-928-345-12-33", 1L, r2);
    Facultet p2 = new Facultet(2L, "Suzi E.S.", "TD", "8-928-444-33-21", 1L, r2);
    Facultet[] persons = new Facultet[]{p1, p2};
    pageContext.setAttribute("persons", persons);
%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Факультеты</title>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/header.jsp" />

    <div class="container-fluid">
        <div class="row justify-content-start">
            <!-- ЛЕВАЯ КОЛОНКА: список сотрудников -->
            <div class="col-8 border bg-light px-4">
                <h3>Сотрудники Факультета</h3>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Код</th>
                        <th scope="col">ФИО</th>
                        <th scope="col">Факультет</th>
                        <th scope="col">Должность</th>
                        <th scope="col">Телефон</th>
                        <th scope="col">Редактировать</th>
                        <th scope="col">Удалить</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="person" items="${persons}">
                        <tr>
                            <td>${person.getId()}</td>
                            <td>${person.getName()}</td>
                            <td>${person.getFacname()}</td>
                            <td>${person.getRole()}</td>
                            <td>${person.getTel()}</td>
                            <td width="20">
                                <a href="#" role="button" class="btn btn-outline-primary">
                                    <img alt="Редактировать" src="${pageContext.request.contextPath}/images/check.png" width="20" height="20">
                                </a>
                            </td>
                            <td width="20">
                                <a href="#" role="button" class="btn btn-outline-primary">
                                    <img alt="Удалить" src="${pageContext.request.contextPath}/images/trash.png" width="20" height="20">
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- ПРАВАЯ КОЛОНКА: форма "Новый сотрудник" -->
            <div class="col-4 border px-4">
                <h3>Новый сотрудник</h3>
                <br>
                <form method="POST" action="">
                    <div class="mb-3 row">
                        <label for="staticname" class="col-sm-3 col-form-label">ФИО</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control"
                                   id="staticname" name="name"/>
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label for="staticfacname" class="col-sm-3 col-form-label">Фаультет</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control"
                                   id="staticfactname" name="facname"/>
                        </div>
                    </div>

                    <div class="mb-3 row">
                        <label for="roleSelect" class="col-sm-3 col-form-label">Должность</label>
                        <div class="col-sm-7">
                            <select id="roleSelect" name="role" class="form-control">
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
                        <label for="staticphone" class="col-sm-3 col-form-label">Телефон</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control"
                                   id="staticphone" name="phone"/>
                        </div>
                    </div>

                    <p><br>
                        <button type="submit" class="btn btn-primary">Добавить</button>
                    </p>
                </form>
            </div>
        </div>
    </div>

    <jsp:include page="/views/footer.jsp" />
</div>

<!-- jQuery + Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"
        integrity="sha384-8jhV9sqXw1spc51L+HqDuRRny47Xpa1Q9FsO"
        crossorigin="anonymous"></script>
<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsJTQexvpmTbbCpLQ60L"
        crossorigin="anonymous"></script>
</body>
</html>
