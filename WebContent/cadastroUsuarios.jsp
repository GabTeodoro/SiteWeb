<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro</title>
<link rel="stylesheet" href="resources/css/cadastroStyle.css">
<link rel="stylesheet" href="resources/css/tableStyle.css">
</head>
<body>

	<div id="login-box">
		<div class="left">
			<h1>Faça seu cadastro aqui!</h1>
			<form action="CadastroUsuarioServlet" method="post" id="formUser">
				<table>
					<tr>
						<td><label>Id: </label></td>
						<td><input readonly="readonly" type="text" id="id" name="id"
							placeholder="Id" value="${user.id}"></td>
					</tr>
					<tr>
						<td><label>Nome: </label></td>
						<td><input type="text" id="nome" name="nome"
							placeholder="Full Name" value="${user.nome}"></td>
					</tr>
					<tr>
						<td><label>Login: </label></td>
						<td><input type="text" id="login" name="login"
							placeholder="E-mail" value="${user.login}"></td>
					</tr>
					<tr>
						<td><label>Senha: </label></td>
						<td><input type="password" id="senha" name="senha"
							placeholder="Password" value="${user.senha}"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Cadastrar"
							style="width: 100%"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Cancelar"
							onclick="document.getElementById('formUser').action='CadastroUsuarioServlet?acao=reset'"
							style="width: 100%; background-color: #DD4B39"></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="right">
			<h1>Faça seu login usando uma rede social</h1>

			<button class="social-signin facebook">Log in with facebook</button>
			<button class="social-signin twitter">Log in with Twitter</button>
			<button class="social-signin google">Log in with Google+</button>
		</div>
		<div class="or">OR</div>
	</div>
	<h2>Lista de Usuários</h2>
	<div class="table-wrapper">
		<table class="fl-table">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nome</th>
					<th>Login</th>
					<th>Senha</th>
					<th>Excluir</th>
					<th>Editar</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${usuarios}" var="user">
					<tr>
						<td style="width: 100px"><c:out value="${user.id}"></c:out></td>
						<td style="width: 250px"><c:out value="${user.nome}"></c:out></td>
						<td style="width: 250px"><c:out value="${user.login}"></c:out></td>
						<td style="width: 250px"><c:out value="${user.senha}"></c:out></td>

						<td style="width: 100px"><a
							href="CadastroUsuarioServlet?acao=delete&user=${user.login}"><img
								src="resources/img/exclude_icon.png" title="Excluir usuário"
								width="20px" height="20px"></a></td>
						<td style="width: 100px"><a
							href="CadastroUsuarioServlet?acao=edit&user=${user.login}"><img
								src="resources/img/edit_icon.png" title="Editar usuário"
								width="20px" height="20px"></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>