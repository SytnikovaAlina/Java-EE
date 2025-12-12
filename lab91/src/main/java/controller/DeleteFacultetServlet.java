package controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import dao.ConnectionProperty;
import dao.FacultetDbDAO;
import exception.DAOException;



@WebServlet( "/deletefac")
public class DeleteFacultetServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
ConnectionProperty prop;
	public DeleteFacultetServlet() throws FileNotFoundException, IOException {
		super();
		prop = new ConnectionProperty();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FacultetDbDAO dao = new FacultetDbDAO();
		
		String strId = request.getParameter("id");
		
		Long deleteid = null;
		
		if (strId != null && !strId.trim().isEmpty()) {
            try {
                Long deleteId = Long.parseLong(strId);
                dao.delete(deleteId);
            } catch (NumberFormatException | DAOException e) {
                System.err.println("Ошибка удаления роли: " + e.getMessage());
               
            }
		}
		response.sendRedirect(request.getContextPath() + "/facultet");
	}
}

