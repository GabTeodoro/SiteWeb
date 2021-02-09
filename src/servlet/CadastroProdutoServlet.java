package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProdutoDao;
import dao.UsuarioDao;
import model.Produto;

@WebServlet("/CadastroProdutoServlet")
public class CadastroProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Produto produto = new Produto();
	private ProdutoDao produtoDao = new ProdutoDao();

	public CadastroProdutoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String prod = request.getParameter("produto");

		if (acao.equalsIgnoreCase("listar")) {

			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroProduto.jsp");
			request.setAttribute("produtos", produtoDao.listarProduto());
			dispatcher.forward(request, response);

		} else if (acao.equalsIgnoreCase("edit")) {

			produto = produtoDao.buscarProduto(prod);

			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroProduto.jsp");
			request.setAttribute("produto", produto);
			dispatcher.forward(request, response);

		} else if (acao.equalsIgnoreCase("delete")) {

			produtoDao.deletarProduto(prod);

			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroProduto.jsp");
			request.setAttribute("produtos", produtoDao.listarProduto());
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {

			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroProduto.jsp");
			request.setAttribute("produtos", produtoDao.listarProduto());
			dispatcher.forward(request, response);

		} else {

			String codigo = request.getParameter("codigo");
			String nome = request.getParameter("nome");
			String quantidade = request.getParameter("quantidade");
			String valor = request.getParameter("valor");

			produto.setNome(nome);
			produto.setQuantidade(!quantidade.isEmpty() ? Integer.valueOf(quantidade) : 0);
			produto.setValor(!valor.isEmpty() ? Double.valueOf(valor) : 0.0);

			if (nome == null || nome.isEmpty()) {

				request.setAttribute("msg", "O campo Nome é obrigatório.");
				request.setAttribute("produto", produto);

			} else if (quantidade == null || quantidade.isEmpty()) {

				request.setAttribute("msg", "O campo Quantidade é obrigatório.");
				request.setAttribute("produto", produto);

			} else if (valor == null || valor.isEmpty()) {

				request.setAttribute("msg", "O campo Valor é obrigatório");
				request.setAttribute("produto", produto);
				
			} else if (codigo == null || codigo.isEmpty()) {

				if (!produtoDao.validarNome(nome)) {

					request.setAttribute("msg", "Produto já cadastrado.");
					request.setAttribute("produto", produto);

				} else {

					produtoDao.CadastrarProduto(produto);

				}
				
			} else if (codigo != null && !codigo.isEmpty()) {

				if (!produtoDao.validarNomeEdit(nome, codigo)) {

					request.setAttribute("msg", "Produto já cadastrado.");

				} else {

					produto.setCodigo(Long.valueOf(codigo));

					produtoDao.editarProduto(produto);
				}
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroProduto.jsp");
		request.setAttribute("produtos", produtoDao.listarProduto());
		dispatcher.forward(request, response);

	}

}
