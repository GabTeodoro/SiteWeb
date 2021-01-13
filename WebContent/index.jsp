<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home</title>
</head>
<body>
	<h1>Login!</h1>
	<form action="UsuarioLoginServlet" method="post">
		<table>
			<tr>
				<td><label>Login: </label></td>
				<td><input type="text" id="login" name="login"></td>
			</tr>
			<tr>
				<td><label>Senha: </label></td>
				<td><input type="password" id="senha" name="senha"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Login" style="width: 100%"></td>
			</tr>
		</table>
	</form>

</body>
</html>