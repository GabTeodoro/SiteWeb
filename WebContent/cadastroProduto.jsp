<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Produtos</title>
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
			<center>
				<h1>Cadastre um novo produto aqui!</h1>
				<form action="CadastroProdutoServlet" method="post" id="formUser"
					onsubmit="return validarCampos()? true : false">
					<table>
						<tr>
							<td><label>Código: </label></td>
							<td><input readonly="readonly" type="text" id="codigo"
								name="codigo" placeholder="Código" value="${produto.codigo}"></td>
						</tr>
						<tr>
							<td><label>Nome: </label></td>
							<td><input type="text" id="nome" name="nome"
								placeholder="Full Name" value="${produto.nome}"></td>
						</tr>
						<tr>
							<td><label>Quantidade: </label></td>
							<td><input type="text" id="quantidade" name="quantidade"
								placeholder="Quantidade Estoque" value="${produto.quantidade}"></td>
						</tr>
						<tr>
							<td><label>Preço: </label></td>
							<td><input type="text" id="valor" name="valor"
								placeholder="Valor" value="${produto.valor}"></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="Cadastrar"
								style="width: 45%"> <input type="submit"
								value="Cancelar"
								onclick="document.getElementById('formUser').action='CadastroProdutoServlet?acao=reset'"
								style="width: 50%; background-color: #DD4B39"></td>
						</tr>
					</table>
				</form>
			</center>
		</div>
	</div>
	<h2>Lista de Produtos</h2>
	<div class="table-wrapper">
		<table class="fl-table">
			<thead>
				<tr>
					<th>Código</th>
					<th>Nome</th>
					<th>Quantidade Estoque</th>
					<th>Preço</th>
					<th>Excluir</th>
					<th>Editar</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${produtos}" var="produto">
					<tr>
						<td style="width: 100px"><c:out value="${produto.codigo}"></c:out></td>
						<td style="width: 250px"><c:out value="${produto.nome}"></c:out></td>
						<td style="width: 250px"><c:out value="${produto.quantidade}"></c:out></td>
						<td style="width: 250px"><c:out value="${produto.valor}"></c:out></td>

						<td style="width: 100px"><a
							href="CadastroProdutoServlet?acao=delete&produto=${produto.nome}"><img
								src="resources/img/exclude_icon.png" title="Excluir produto"
								width="20px" height="20px"></a></td>
						<td style="width: 100px"><a
							href="CadastroProdutoServlet?acao=edit&produto=${produto.nome}"><img
								src="resources/img/edit_icon.png" title="Editar produto"
								width="20px" height="20px"></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {

			if (document.getElementById("nome").value == '') {

				alert('O campo Nome é obrigatório.');
				return false;

			} else if (document.getElementById("quantidade").value == '') {

				alert('O campo Quantidade é obrigatório.');
				return false;
				
			} else if (document.getElementById("valor").value == '') {

				alert('O campo Valor é obrigatório.');
				return false;
			}

			return true;
		}
	</script>

</body>
</html>