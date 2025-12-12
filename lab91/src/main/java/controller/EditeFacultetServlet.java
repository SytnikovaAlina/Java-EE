package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import dao.ConnectionProperty;
import dao.FacultetDbDAO;
import dao.RoleDbDAO;
import domain.Facultet;
import domain.Role;
import exception.DAOException;

@WebServlet("/editfac")
public class EditeFacultetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ConnectionProperty prop;

    public EditeFacultetServlet() throws FileNotFoundException, IOException {
        super();
        prop = new ConnectionProperty();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");

        List<Role> roles = null;
        List<Facultet> facs = null;
        Facultet editfac = null;

        RoleDbDAO daoRole = new RoleDbDAO();
        FacultetDbDAO dao = new FacultetDbDAO();

        try {
            // список должностей
            roles = daoRole.findAll();
            request.setAttribute("roles", roles);

            // список всех сотрудников кафедры
            facs = dao.findAll();
            for (Facultet fac : facs) {
                fac.setRole(daoRole.findById(fac.getIdR()));
            }
            request.setAttribute("facs", facs);

        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ошибка загрузки данных: " + e.getMessage());
        }

        // id редактируемого сотрудника
        String strId = request.getParameter("id");
        if (strId != null && !strId.isBlank()) {
            try {
                Long id = Long.parseLong(strId);
                editfac = dao.findById(id);
            } catch (DAOException | NumberFormatException e) {
                e.printStackTrace();
                request.setAttribute("error", "Ошибка загрузки сотрудника: " + e.getMessage());
            }
        }

        request.setAttribute("FacultetEdit", editfac);

        // ВСЕГДА переходим на JSP
        request.getRequestDispatcher("/views/editfac.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FacultetDbDAO dao = new FacultetDbDAO();

        String strId = request.getParameter("id");
        Long id = (strId == null || strId.isBlank()) ? null : Long.parseLong(strId);

        String name = request.getParameter("Name");      // name="Name" в JSP
        String facName = request.getParameter("Facname"); // name="Facname"
        String phone = request.getParameter("phone");     // name="phone"
        String roleStr = request.getParameter("role");    // name="role"

        Long idRole = (roleStr == null || roleStr.isBlank()) ? null : Long.parseLong(roleStr);

        // ОБЪЯВЛЯЕМ ПЕРЕМЕННУЮ ЗДЕСЬ
        Facultet editFacultet = new Facultet(name, facName, phone, idRole);
        if (id != null) {
            editFacultet.setId(id);
        }
        editFacultet.setIdR(idRole);  // чтобы update не падал на getIdR()

        try {
            dao.update(editFacultet);
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ошибка обновления: " + e.getMessage());
            doGet(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/facultet");
    }

}
