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
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		if (acao.equalsIgnoreCase("listar")) {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroProduto.jsp");
			request.setAttribute("produtos", produtoDao.listarProduto());
			dispatcher.forward(request, response);
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String codigo = request.getParameter("codigo");
		String nome = request.getParameter("nome");
		String quantidade = request.getParameter("quantidade");
		String valor = request.getParameter("valor");
		
		if (codigo == null || codigo.isEmpty()) {
			
			produto.setNome(nome);
			produto.setQuantidade(Integer.valueOf(quantidade));
			produto.setValor(Integer.valueOf(valor));
			
			produtoDao.CadastrarProduto(produto);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("cadastroProduto.jsp");
		request.setAttribute("produtos", produtoDao.listarProduto());
		dispatcher.forward(request, response);
		
	}

}
