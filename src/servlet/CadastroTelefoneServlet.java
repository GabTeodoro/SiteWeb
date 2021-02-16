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

@WebServlet("/CadastroTelefoneServlet")
public class CadastroTelefoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UsuarioDao usuarioDao = new UsuarioDao();
       
    public CadastroTelefoneServlet() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user = request.getParameter("user");
		
		Usuario usuario = usuarioDao.buscarUsuario(user);
		
		request.getSession().setAttribute("usuarioTelefone", usuario);
		request.setAttribute("usuarioTelefone", usuario);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuarioTelefone.jsp");
		dispatcher.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioTelefone");
		
		String numero = request.getParameter("numero");
		String tipo = request.getParameter("tipo");
		
		System.out.println(numero);
		System.out.println(tipo);
		
	}

}
