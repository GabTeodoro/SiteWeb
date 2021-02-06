package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProdutoDao;
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

			if (codigo == null || codigo.isEmpty()) {

				produto.setNome(nome);
				produto.setQuantidade(Integer.valueOf(quantidade));
				produto.setValor(Double.valueOf(valor));

				produtoDao.CadastrarProduto(produto);
			} else if (codigo != null && !codigo.isEmpty()) {

				produto.setCodigo(Long.valueOf(codigo));
				produto.setNome(nome);
				produto.setQuantidade(Integer.valueOf(quantidade));
				produto.setValor(Double.valueOf(valor));

				produtoDao.editarProduto(produto);

			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroProduto.jsp");
		request.setAttribute("produtos", produtoDao.listarProduto());
		dispatcher.forward(request, response);

	}

}
