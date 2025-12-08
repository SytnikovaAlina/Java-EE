<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.Role"%>
<%
Role r1 = new Role("Заведующий кафедрой", 1l);
Role r2 = new Role("Декан факультета", 2l);

Role[] roles = new Role[]{r1, r2};
int length = roles.length;
pageContext.setAttribute("roles", roles);
%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Должности</title>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Roles</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    
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
						 <th scope="col">Код</th>
						 <th scope="col">Должность</th>
						 <th scope="col"> Редактировать</th>
						 <th scope="col">Удалить</th>
					 </thead>
	 				 <tbody>
						 <c:forEach var="role" items="${roles}">
							 <tr>
								 <td>${role.getId()}</td>
								 <td>${role.getName()}</td>
								 <td width="20"><a href="#" role="button"
								 class="btn btn-outline-primary">
								 <img alt="Редактировать"
								 src="${pageContext.request.contextPath}/images/check.png" width="20" height="20"></a></td>
								 <td width="20"><a href="#" role="button"
								 class="btn btn-outline-primary">
								 <img alt="Удалить"
								 src="${pageContext.request.contextPath}/images/trash.png" width="20" height="20"></a></td>
							 </tr>
					 	 </c:forEach>
					 </tbody>
	 			</table>
 			</div>
 		<div class="col-4 border px-4">
	 		<form method="POST" action="">
				 <h3>Новая должность</h3>
				 <div class="mb-3">
				 <br> <label for="inputRole"
				 class="col-sm-3 col-form-label">Должность</label>
				 <div class="col-sm-6">
				 <input type="text" name="inputRole"
				 class="form-control" id="personRole" />
			 	</div>
			 </div>
			 <p>
			 	<br> <br> <br>
			 	<button type="submit"
			 	class="btn btn-primary">Добавить</button>
			 	<br>
			 </p>
	 		</form>
 			</div>
 		</div>
 	</div>
 	<jsp:include page="/views/footer.jsp" />
 	</div>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha384-8jhV9sqXw1spc51L+HqDuRRny47Xpa1Q9FsO" crossorigin="anonymous"></script>
<!-- Bootstrap JS + Popper JS -->
<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsJTQexvpmTbbCpLQ60L" crossorigin="anonymous"></script> 	
</body>
</html>
 