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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Product;

/**
 *
 * @author rogeliotorres
 */
public class realizarCompra extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        
        if(session!=null&&session.getAttribute("logged_in")!=null)
        {
            if(request.getParameter("userId")!=null)
            {
                Database db = new Database();
                Float totalAmount = 0.0F;
                
                //obtenemos el carrito
                ResultSet rsCarrito = db.ExecQuery("select * from ShoppingCart S join products P on S.ProductId=P.Id where UserId = ?", new Object[]{request.getParameter("userId")});
                
                List<Product> products = new ArrayList();
                                
                try {   
                    
                    while(rsCarrito.next())
                    {
                        products.add(new Product(rsCarrito.getInt("Id"),
                                      rsCarrito.getInt("DepartmentId"),
                                      "",
                                      rsCarrito.getString("Name"),
                                      rsCarrito.getString("Description"),
                                      rsCarrito.getFloat("Price"),
                                      rsCarrito.getInt("Quantity"),
                                      rsCarrito.getBoolean("Active"),
                                      "",
                                      rsCarrito.getInt("AmountProduct")));
                        
                        totalAmount += rsCarrito.getInt("AmountProduct")*rsCarrito.getFloat("Price");
                    }
                    
                    if(totalAmount>0)
                    {
                        db.ExecUpdate("insert into sales values(?,?)", new Object[]{session.getAttribute("user_id"),totalAmount});//id, UserId, TotalAmount
                        //Get the last sale
                        ResultSet rsLastSale = db.ExecQuery("Select max(Id) as maximo from sales", null);
                        
                        Integer maxId = 0;
                        if(rsLastSale.next())
                        {
                            maxId = rsLastSale.getInt("maximo");
                            
                            //Insertamos los productos de la compra en el detalle
                            for(Product product:products)
                            {
                                db.ExecUpdate("insert into detailSales values(?,?,?,?)", new Object[]{maxId,product.getId(),product.getCantidad(),product.getPrice()}); //SaleId, ProductId, AmountProduct,PurchasePrice
                            }
                        }
                        
                        //borramos el carrito
                        for(Product product:products)
                        {
                            db.ExecUpdate("Delete from ShoppingCart where UserId = ?", new Object[]{request.getParameter("userId")});
                        }
                        
                        response.sendRedirect("mostrarCompra?id="+String.valueOf(maxId));
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(realizarCompra.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                db.CloseConnection();
            }
        }
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
