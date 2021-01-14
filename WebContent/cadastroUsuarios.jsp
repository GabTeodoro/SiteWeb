<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro</title>
</head>
<body>

	<h1>Faça seu cadastro aqui!</h1>
	<form action="CadastroUsuarioServlet" method="post">
		<table>
			<tr>
				<td><label>Id: </label></td>
				<td><input readonly="readonly" type="text" id="id" name="id"
					value="${user.id}"></td>
			</tr>
			<tr>
				<td><label>Nome: </label></td>
				<td><input type="text" id="nome" name="nome"
					value="${user.nome}"></td>
			</tr>
			<tr>
				<td><label>Login: </label></td>
				<td><input type="text" id="login" name="login"
					value="${user.login}"></td>
			</tr>
			<tr>
				<td><label>Senha: </label></td>
				<td><input type="password" id="senha" name="senha"
					value="${user.senha}"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Cadastrar" style="width: 100%"></td>
			</tr>
		</table>
	</form>

	<table>
		<tr>
			<td>Id</td>
			<td>Nome</td>
			<td>Login</td>
			<td>Senha</td>
			<td>Ações</td>
			<td>Ações</td>
		</tr>
		<c:forEach items="${usuarios}" var="user">
			<tr>
				<td style="width: 150px"><c:out value="${user.id}"></c:out></td>
				<td style="width: 250px"><c:out value="${user.nome}"></c:out></td>
				<td style="width: 250px"><c:out value="${user.login}"></c:out></td>
				<td style="width: 250px"><c:out value="${user.senha}"></c:out></td>

				<td><a
					href="CadastroUsuarioServlet?acao=delete&user=${user.login}">Excluir</a></td>
				<td><a
					href="CadastroUsuarioServlet?acao=edit&user=${user.login}">Editar</a></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>