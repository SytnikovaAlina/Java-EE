package controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import dao.ConnectionProperty;
import dao.RoleDbDAO;
import exception.DAOException;

@WebServlet("/deleterole")
public class DeleteRoleServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RoleDbDAO dao = new RoleDbDAO();
        String strId = request.getParameter("id");
        
        if (strId != null && !strId.trim().isEmpty()) {
            try {
                Long deleteId = Long.parseLong(strId);
                dao.delete(deleteId);
            } catch (NumberFormatException | DAOException e) {
                System.err.println("Ошибка удаления роли: " + e.getMessage());
               
            }
        }
        
        response.sendRedirect(request.getContextPath() + "/role");  // Вернуться на список ролей
    }
}
