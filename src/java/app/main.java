/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Department;
import models.Product;

/**
 *
 * @author rogeliotorres
 */
public class main extends HttpServlet {
    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Database db = new Database();
        
        //Get the active deparments
        ResultSet rs = db.ExecQuery("Select * from departments where active=1", null);
            
        List<Department> departmentList = new ArrayList();
            
        try
        { 
            while(rs.next())
            {
                Department tmpDepartment = new Department();
                tmpDepartment.setId(rs.getInt("id"));
                tmpDepartment.setName(rs.getString("name"));
                departmentList.add(tmpDepartment);
            }       
        }
        catch (SQLException ex)
        {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("departments", departmentList);
        
        //get the products form the department
        Integer department = 1;
        if(request.getParameter("department")!=null) department = Integer.parseInt(request.getParameter("department"));
        ResultSet rsProducts = db.ExecQuery("Select * from Products where DepartmentId=?", new Object[]{department});
        
        List<Product> products = new ArrayList();
        if(rsProducts!=null)
        {
            try {
                while(rsProducts.next())
                {
                    
                    products.add(new Product(rsProducts.getInt("Id"),
                                                          rsProducts.getInt("DepartmentId"),
                                                          "",
                                                          rsProducts.getString("Name"),
                                                          rsProducts.getString("Description"),
                                                          rsProducts.getFloat("Price"),
                                                          rsProducts.getInt("Quantity"),
                                                          rsProducts.getBoolean("Active")));
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        request.setAttribute("products", products);
        
        db.CloseConnection();
        
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/main_view.jsp");
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
