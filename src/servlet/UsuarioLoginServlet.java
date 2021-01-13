package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDao;
import model.Usuario;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/UsuarioLoginServlet")
public class UsuarioLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	private UsuarioDao usuarioDao = new UsuarioDao();
	
    public UsuarioLoginServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		
		try {
			if(usuarioDao.validarUsuario(login, senha)) {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("acessoLiberado.jsp");
				dispatcher.forward(request, response);
				
			}else {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("acessoNegado.jsp");
				dispatcher.forward(request, response);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e1) {
			e1.printStackTrace();
		}
		
	}

}
