<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bem-Vindo</title>
</head>
<body>

	<h1>Paranbéns! Acesso liberado ao sistema!</h1>


	<table>
		<thead>
			<tr>
				<th><a href="CadastroUsuarioServlet?acao=listar"><img
						src="resources/img/newUser_icon.png"
						title="Cadastrar um novo usuário no sistema" width="120px"
						height="120x"></a></th>
				<th></th>
				<th></th>
				<th><a href="CadastroProdutoServlet?acao=listar"><img
						src="resources/img/produto_icon.png"
						title="Cadastrar um novo produto no sistema" width="120px"
						height="120x"></a></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><a href="CadastroUsuarioServlet?acao=listar">Cadastrar
						Usuário</a></td>
				<th></th>
				<th></th>
				<td><a href="CadastroProdutoServlet?acao=listar">Cadastrar
						Produto</a></td>
			</tr>
		</tbody>

	</table>
</body>
</html>