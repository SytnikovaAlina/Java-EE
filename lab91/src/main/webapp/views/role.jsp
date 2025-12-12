<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.Role"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>


<!DOCTYPE html>
<html>
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
                    <h3>Список должностей</h3>
                    
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Код</th>
                                <th scope="col">Должность</th>
                                <th scope="col">Редактировать</th>
                                <th scope="col">Удалить</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="role" items="${roles}">
                                <tr>
                                    <td>${role.getId()}</td>
                                    <td>${role.getName()}</td>
                                    <td><a href="<c:url value="/editrole?id=${role.getId()}" />" class="btn btn-outline-primary"><img alt="Редактировать" src="${pageContext.request.contextPath}/images/check.png" width="20" height="20"></a></td>
                                    <td><a href="<c:url value="/deleterole?id=${role.getId()}" />" class="btn btn-outline-primary"><img alt="Удалить" src="${pageContext.request.contextPath}/images/trash.png" 
                                    		onclick="return confirm('Удалить должность с кодом:'+ ${role.getId()}+'?')" width="20" height="20"></a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-4 border px-4">
                    <form method="POST" action="">
                        <h3>Новая должность</h3>
                        <div class="mb-3">
                            <label for="inputRole" class="col-sm-3 col-form-label">Должность</label>
                            <div class="col-sm-6">
                                <input type="text" name="inputRole" class="form-control" id="inputRole" required />
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Добавить</button>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="/views/footer.jsp" />
    </div>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"
    integrity="sha384-8jhV9sqXw1spc51L+HqDuRRny47Xpa1Q9FsO" crossorigin="anonymous"></script>
    <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsJTQexvpmTbbCpLQ60L" crossorigin="anonymous"></script>
</body>
</html>
    