package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDao;
import model.Usuario;

/**
 * Servlet implementation class CadastroUsuarioServlet
 */
@WebServlet("/CadastroUsuarioServlet")
public class CadastroUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	private UsuarioDao usuarioDao = new UsuarioDao();

	public CadastroUsuarioServlet() {
		super();

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

			if (id == null || id.isEmpty() && !usuarioDao.validarLogin(login)) {

				request.setAttribute("msg", "Usuário já cadastrado. Por favor escolha outro login.");

			}

			if (id == null || id.isEmpty() && usuarioDao.validarLogin(login)) {

				usuario.setNome(nome);
				usuario.setLogin(login);
				usuario.setSenha(senha);

				usuarioDao.cadastrarUsuario(usuario);

			} else if (id != null && !id.isEmpty()) {

				usuario.setId(Long.parseLong(id));
				usuario.setNome(nome);
				usuario.setLogin(login);
				usuario.setSenha(senha);

				usuarioDao.editarUsuario(usuario);
			}

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroUsuarios.jsp");
		request.setAttribute("usuarios", usuarioDao.listarUsuarios());
		dispatcher.forward(request, response);

	}

}
