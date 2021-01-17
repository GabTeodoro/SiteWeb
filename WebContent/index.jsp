<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home</title>
<link rel="stylesheet" href="resources/css/indexStyle.css">
</head>
<body>
	<h1 style="text-align: center">Login!</h1>
	<div class="login-page">
		<div class="form">
			<form class="login-form" action="UsuarioLoginServlet" method="post">
				<table>
					<tr>
						<td><label>Login: </label></td>
						<td><input type="text" id="login" name="login" placeholder="username"></td>
					</tr>
					<tr>
						<td><label>Senha: </label></td>
						<td><input type="password" id="senha" name="senha" placeholder="password"></td>
					</tr>
					<tr>
						<td></td>
						<td><button type="submit" value="Login">Confirmar</button></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>