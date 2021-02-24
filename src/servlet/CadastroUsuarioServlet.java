package servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import dao.UsuarioDao;
import model.Usuario;

@WebServlet("/CadastroUsuarioServlet")
@MultipartConfig
public class CadastroUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	private UsuarioDao usuarioDao = new UsuarioDao();

	public CadastroUsuarioServlet() {
		super();

	}

	private byte[] converteStremParaByte(InputStream imagem) {

		// Criando uma lista de Bytes.
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();

		try {

			// Criando uma variável para ler a imagem do tipo InputStream.
			int leituraDeDados = imagem.read();

			// Enquanto tiver dados dentro da imagem..
			while (leituraDeDados != -1) {

				// Adiciona os dados dentro da lista de Bytes.
				arrayOutputStream.write(leituraDeDados);

				// Verificando se ainda há imgens.
				leituraDeDados = imagem.read();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Retornando uma lista de bytes.
		return arrayOutputStream.toByteArray();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String user = request.getParameter("user");

		if (acao.equalsIgnoreCase("delete")) {

			usuarioDao.deletarUsuario(user);

			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroUsuarios.jsp");
			request.setAttribute("usuarios", usuarioDao.listarUsuarios());
			dispatcher.forward(request, response);

		} else if (acao.equalsIgnoreCase("edit")) {

			Usuario usuario = usuarioDao.buscarUsuario(user);

			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroUsuarios.jsp");
			request.setAttribute("user", usuario);
			dispatcher.forward(request, response);

		} else if (acao.equalsIgnoreCase("listar")) {

			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroUsuarios.jsp");
			request.setAttribute("usuarios", usuarioDao.listarUsuarios());
			dispatcher.forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {

			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroUsuarios.jsp");
			request.setAttribute("usuarios", usuarioDao.listarUsuarios());
			dispatcher.forward(request, response);

		} else {

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String telefone = request.getParameter("telefone");

			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String numero = request.getParameter("numero");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String uf = request.getParameter("uf");

			usuario.setNome(nome);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setTelefone(telefone);

			usuario.setCep(cep);
			usuario.setRua(rua);
			usuario.setNumero(numero);
			usuario.setBairro(bairro);
			usuario.setCidade(cidade);
			usuario.setUf(uf);

			try {

				if (ServletFileUpload.isMultipartContent(request)) {

					Part imagemFoto = request.getPart("foto");

					if (imagemFoto != null && imagemFoto.getInputStream().available() > 0) {

						String fotoBase64 = Base64
								.encodeBase64String(converteStremParaByte(imagemFoto.getInputStream()));
						usuario.setFotoBase64(fotoBase64);
						usuario.setContentType(imagemFoto.getContentType());
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			if (login == null || login.isEmpty() || senha == null || senha.isEmpty()) {

				request.setAttribute("msg", "Os campos Login e Senha são obrigatórios.");
				request.setAttribute("user", usuario);

			} else if (id == null || id.isEmpty()) {

				if (!usuarioDao.validarLogin(login)) {

					request.setAttribute("msg", "Usuário já cadastrado. Por favor escolha outro login.");
					request.setAttribute("user", usuario);

				} else {

					usuarioDao.cadastrarUsuario(usuario);
					request.setAttribute("msg", "Usuário cadastrado com sucesso!");

				}

			} else if (id != null && !id.isEmpty()) {

				if (!usuarioDao.validarLoginEdit(login, id)) {

					request.setAttribute("msg", "Login já cadastrado. Por favor escolha outro login.");

				} else {

					usuario.setId(Long.parseLong(id));

					usuarioDao.editarUsuario(usuario);
					request.setAttribute("msg", "Usuário salvo com sucesso!");
				}
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroUsuarios.jsp");
		request.setAttribute("usuarios", usuarioDao.listarUsuarios());
		dispatcher.forward(request, response);

	}

}
