<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Usuários</title>
<link rel="stylesheet" href="resources/css/cadastroStyle.css">
<link rel="stylesheet" href="resources/css/tableStyle.css">
<!-- Adicionando JQuery para consultar o CEP -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>

</head>
<body>

	<a href="acessoLiberado.jsp">Início</a>
	<a href="index.jsp">Sair</a>
	<center>
		<h3>${msg}</h3>
	</center>

	<div id="login-box">
		<div class="left">
			<h1>Faça seu cadastro aqui!</h1>
			<form action="CadastroUsuarioServlet" method="post" id="formUser"
				onsubmit="return validarCampos()? true : false; "
				enctype="multipart/form-data">
				<table>
					<tr>
						<td><label>Foto: </label></td>
						<td colspan="6"><input type="file" name="foto" id="foto"
							value="Foto"></td>
					</tr>
					<tr>
						<td><label>Id: </label></td>
						<td><input readonly="readonly" type="text" id="id" name="id"
							placeholder="Id" value="${user.id}"></td>
						<td></td>
						<td><label>CEP: </label></td>
						<td colspan="3"><input type="text" id="cep" name="cep"
							placeholder="00000000" onblur="consultaCep();"
							value="${user.cep}"></td>
					</tr>
					<tr>
						<td><label>Nome: </label></td>
						<td><input type="text" id="nome" name="nome"
							placeholder="Full Name" value="${user.nome}"></td>
						<td></td>
						<td><label>Rua: </label></td>
						<td colspan="3"><input type="text" id="rua" name="rua"
							placeholder="Logradouro" value="${user.rua}"></td>
					</tr>
					<tr>
						<td><label>Login: </label></td>
						<td><input type="text" id="login" name="login"
							placeholder="E-mail" value="${user.login}"></td>
						<td></td>
						<td><label>Bairro: </label></td>
						<td colspan="3"><input type="text" id="bairro" name="bairro"
							placeholder="Bairro" value="${user.bairro}"></td>
					</tr>
					<tr>
						<td><label>Senha: </label></td>
						<td><input type="password" id="senha" name="senha"
							placeholder="Password" value="${user.senha}"></td>
						<td></td>
						<td><label>Cidade: </label></td>
						<td colspan="3"><input type="text" id="cidade" name="cidade"
							placeholder="Cidade" value="${user.cidade}"></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td><label>UF: </label></td>
						<td><input type="text" id="uf" name="uf" placeholder="UF"
							style="width: 100%" value="${user.uf}"></td>
						<td><label>Número: </label></td>
						<td><input type="text" id="numero" name="numero"
							placeholder="N°" style="width: 100%" value="${user.numero}"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Cadastrar"
							style="width: 100%"></td>
						<td></td>
						<td></td>
						<td><input type="submit" value="Cancelar"
							onclick="document.getElementById('formUser').action='CadastroUsuarioServlet?acao=reset'"
							style="width: 250%; background-color: #DD4B39"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<h2>Lista de Usuários</h2>
	<div class="table-wrapper">
		<table class="fl-table">
			<thead>
				<tr>
					<th>Id</th>
					<th>Foto</th>
					<th>Nome</th>
					<th>Login</th>
					<th>Senha</th>
					<th>Excluir</th>
					<th>Editar</th>
					<th>Telefones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${usuarios}" var="user">
					<tr>
						<td style="width: 100px"><c:out value="${user.id}"></c:out></td>
						<td style="width: 150px"><a
							href="CadastroUsuarioServlet?acao=downloadFoto&user=${user.login}"><img
								src='<c:out value="${user.fotoUser}"></c:out>' width="20px"
								height="20px" title="Imagem usuário" /></a></td>
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
						<td style="width: 100px"><a
							href="CadastroTelefoneServlet?acao=addTelefone&user=${user.login}"><img
								src="resources/img/phone_icon.png"
								title="Consultar e adicionar Telefones" width="20px"
								height="20px"></a></td>
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

			} else if (document.getElementById("senha").value == '') {
				alert('O campo Senha é obrigatório.');
				return false;

			}

			return true;
		}

		function consultaCep() {

			var cep = $("#cep").val();

			//Consulta o webservice viacep.com.br/
			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?",
					function(dados) {

						if (!("erro" in dados)) {
							//Atualiza os campos com os valores da consulta.
							$("#rua").val(dados.logradouro);
							$("#bairro").val(dados.bairro);
							$("#cidade").val(dados.localidade);
							$("#uf").val(dados.uf);
						} //end if.
						else {
							//CEP pesquisado não foi encontrado.
							$('#cep').val('');
							$("#rua").val('');
							$("#bairro").val('');
							$("#cidade").val('');
							$("#uf").val('');
							alert("CEP não encontrado.");
						}
					});

		}
	</script>
</body>
</html>