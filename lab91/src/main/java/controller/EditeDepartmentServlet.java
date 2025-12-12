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
import dao.DepartmentDbDAO;
import dao.RoleDbDAO;
import domain.Department;
import domain.Role;
import exception.DAOException;

@WebServlet("/editdep")
public class EditeDepartmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ConnectionProperty prop;

    public EditeDepartmentServlet() throws FileNotFoundException, IOException {
        super();
        prop = new ConnectionProperty();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");

        List<Role> roles = null;
        List<Department> deps = null;
        Department editdep = null;

        RoleDbDAO daoRole = new RoleDbDAO();
        DepartmentDbDAO dao = new DepartmentDbDAO();

        try {
            // список должностей
            roles = daoRole.findAll();
            request.setAttribute("roles", roles);

            // список всех сотрудников кафедры
            deps = dao.findAll();
            for (Department dep : deps) {
                dep.setRole(daoRole.findById(dep.getIdR()));
            }
            request.setAttribute("deps", deps);

        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ошибка загрузки данных: " + e.getMessage());
        }

        // id редактируемого сотрудника
        String strId = request.getParameter("id");
        if (strId != null && !strId.isBlank()) {
            try {
                Long id = Long.parseLong(strId);
                editdep = dao.findById(id);
            } catch (DAOException | NumberFormatException e) {
                e.printStackTrace();
                request.setAttribute("error", "Ошибка загрузки сотрудника: " + e.getMessage());
            }
        }

        request.setAttribute("DepartmentEdit", editdep);

        // ВСЕГДА переходим на JSP
        request.getRequestDispatcher("/views/editdep.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DepartmentDbDAO dao = new DepartmentDbDAO();

        // id сотрудника (для update)
        String strId = request.getParameter("id");
        Long id = (strId == null || strId.isBlank()) ? null : Long.parseLong(strId);

        // Имена параметров должны совпадать с name в editdep.jsp
        String name = request.getParameter("Name");       // name="Name"
        String depName = request.getParameter("Depname"); // name="Depname"
        String phone = request.getParameter("phone");     // name="phone"
        String roleStr = request.getParameter("role");    // name="role"
        
        Long idRole = (roleStr == null || roleStr.isBlank()) ? null : Long.parseLong(roleStr);

        Department editDepartment = new Department(name, depName, phone, idRole);
        if (id != null) {
            editDepartment.setId(id);
        }
        editDepartment.setIdR(idRole);   // ВАЖНО: заполнить idR!


        try {
            dao.update(editDepartment);
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ошибка обновления: " + e.getMessage());
            doGet(request, response);
            return;
        }

        // После сохранения возвращаемся к списку кафедр
        response.sendRedirect(request.getContextPath() + "/department");
    }
}
