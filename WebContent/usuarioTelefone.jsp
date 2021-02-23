<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro e lista de telefones</title>
<link rel="stylesheet" href="resources/css/cadastroStyle.css">
<link rel="stylesheet" href="resources/css/tableStyle.css">
</head>
<body>
	<a href="acessoLiberado.jsp">Início</a>
	<a href="index.jsp">Sair</a>
	
	<center>
		<h3>${msg}</h3>
	</center>

	<div id="login-box">
		<div class="left">
			<h1>Cadastrar Telefones</h1>
			<form action="CadastroTelefoneServlet" method="post" id="formUser"
				onsubmit="return validarCampos()? true : false ">
				<table>
					<tr>
						<td><label>Id: </label></td>
						<td><input readonly="readonly" type="text" id="id" name="id"
							placeholder="#ID" value="${usuarioTelefone.id}"></td>
						<td></td>
						<td><label>Login: </label></td>
						<td><input readonly="readonly" type="text" id="login"
							name="login" placeholder="Login" value="${usuarioTelefone.login}"></td>
					</tr>
					<tr>
						<td><label>Número: </label></td>
						<td><input type="text" id="numero" name="numero"
							placeholder="(00)00000000"></td>
						<td></td>
						<td><label>Tipo: </label></td>
						<td><SELECT id="tipo" name="tipo">
								<option>Celular</option>
								<option>Casa</option>
								<option>Profissional</option>
						</SELECT></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Cadastrar"
							style="width: 100%"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<h2>Lista de Telefones do Usuário</h2>
	<div class="table-wrapper">
		<table class="fl-table">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Número</th>
					<th>Tipo</th>
					<th>Excluir</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${telefones}" var="phone">
					<tr>
						<td style="width: 100px"><c:out value="${phone.id}"></c:out></td>
						<td style="width: 250px"><c:out value="${phone.numero}"></c:out></td>
						<td style="width: 250px"><c:out value="${phone.tipo}"></c:out></td>
						<td style="width: 100px"><a
							href="CadastroTelefoneServlet?acao=deleteTelefone&telefoneId=${phone.id}"><img
								src="resources/img/exclude_icon.png" title="Excluir usuário"
								width="20px" height="20px"></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {

			if (document.getElementById("login").value == '') {
				alert('O campo Login é obrigatório.');
				return false;

			} else if (document.getElementById("numero").value == '') {
				alert('O campo Número é obrigatório.');
				return false;

			}

			return true;
		}
	</script>
</body>
</html>