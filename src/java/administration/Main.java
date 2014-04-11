/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administration;

import app.Database;
import java.io.IOException;
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
import models.User;

/**
 *
 * @author Armando
 */
public class Main extends HttpServlet
{

    private boolean foward = true;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest ( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        HttpSession session = request.getSession ();

        if ( session.getAttribute ( "user_id" ) != null || session.getAttribute ( "logged_in" ) != null || session.getAttribute ( "name" ) != null || session.getAttribute ( "rol" ) != null )
        {
            if ( session.getAttribute ( "rol" ).equals ( 1 ) )
            {
                RequestDispatcher rd = null;
                if ( request.getParameter ( "section" ) != null )
                {
                    switch ( request.getParameter ( "section" ) )
                    {
                        case "Profile":
                            Profile ( session, request );
                            foward = true;
                            rd = request.getRequestDispatcher ( "../WEB-INF/jsp/administration/profile.jsp" );
                            break;

                        case "Departments":
                            Departments ( request );
                            foward = true;
                            rd = request.getRequestDispatcher ( "../WEB-INF/jsp/administration/departments.jsp" );
                            break;

                        case "AddDepartment":
                            if ( request.getParameter ( "departmentName" ) != null )
                            {
                                AddDepartment ( request, response );
                            }
                            foward = false;
                            break;

                        case "activeDepartment":
                        case "deleteDepartment":
                            if ( request.getParameter ( "derpartmentId" ) != null )
                            {
                                StatusDepartment ( request, response );

                            }
                            foward = false;
                            break;

                        case "Products":
                            if ( request.getParameter ( "add" ) != null && request.getParameter ( "add" ).equals ( "true" ) )
                            {
                                foward = true;
                                ListDepartments ( request, response );
                                rd = request.getRequestDispatcher ( "../WEB-INF/jsp/administration/product_info.jsp" );
                            }
                            else if ( request.getParameter ( "edit" ) != null )
                            {
                                ProductoInfo ( request, response );
                                ListDepartments ( request, response );
                                foward = true;
                                rd = request.getRequestDispatcher ( "../WEB-INF/jsp/administration/product_info.jsp" );
                            }
                            else
                            {
                                Products ( request, response );
                                ListDepartments ( request, response );
                                foward = true;
                                rd = request.getRequestDispatcher ( "../WEB-INF/jsp/administration/products.jsp" );
                            }
                            break;

                        case "AddProduct":
                        case "EditProduct":
                            AddUpdateProduct ( request, response );
                            foward = false;
                            break;

                        case "Clients":
                            if ( request.getParameter ( "Active" ) != null || request.getParameter ( "Inactive" ) != null )
                            {
                                ClientStatus ( request, response );
                                foward = false;
                                rd = request.getRequestDispatcher ( "../WEB-INF/jsp/administration/client_info.jsp" );
                            }
                            else
                            {
                                Clients ( request, response );
                                foward = true;
                                rd = request.getRequestDispatcher ( "../WEB-INF/jsp/administration/clients.jsp" );
                            }
                            break;

                        case "AdminUsers":
                            if ( request.getParameter ( "add" ) != null && request.getParameter ( "add" ).equals ( "true" ) )
                            {
                                foward = true;
                                rd = request.getRequestDispatcher ( "../WEB-INF/jsp/administration/admin_user_info.jsp" );
                            }
                            else if ( request.getParameter ( "edit" ) != null )
                            {
                                AdminUserInfo ( request, response );
                                foward = true;
                                rd = request.getRequestDispatcher ( "../WEB-INF/jsp/administration/admin_user_info.jsp" );
                            }
                            else
                            {
                                AdminUsers ( request, response );
                                foward = true;
                                rd = request.getRequestDispatcher ( "../WEB-INF/jsp/administration/admin_users.jsp" );
                            }
                            break;

                        case "AddAdminUser":
                        case "EditAdminUser":
                            AddUpdateAdminUser ( request, response );
                            foward = false;
                            break;

                    }
                }
                else
                {
                    Profile ( session, request );
                    foward = true;
                    rd = request.getRequestDispatcher ( "../WEB-INF/jsp/administration/profile.jsp" );
                }

                if ( foward )
                {
                    rd.forward ( request, response );
                }
            }
            else
            {
                response.sendRedirect ( "../" );
            }
        }
        else
        {
            response.sendRedirect ( "../" );
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet ( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        processRequest ( request, response );
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost ( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        processRequest ( request, response );
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo ()
    {
        return "Short description";
    }// </editor-fold>

    private void Profile ( HttpSession session, HttpServletRequest request )
    {
        User user = new User ();
        Database db = new Database ();
        ResultSet rs = null;
        String query = "SELECT * FROM Users WHERE id = ?";
        Object[] args = new Object[]
        {
            session.getAttribute ( "user_id" )
        };

        if ( request.getParameter ( "user_email" ) != null )
        {
            if ( request.getParameter ( "new_password" ).equals ( request.getParameter ( "new_password2" ) ) )
            {
                int postalcode = Integer.parseInt ( request.getParameter ( "user_postalCode" ) );
                String update;
                Object[] update_args;
                if ( !request.getParameter ( "new_password" ).isEmpty () )
                {
                    update = "UPDATE Users "
                            + "SET Name = ?, LastName = ?, Address = ?, PostalCode = ?, Phone = ?, Email = ?, Password = ? "
                            + "WHERE Id = ?";

                    update_args = new Object[]
                    {
                        request.getParameter ( "user_name" ),
                        request.getParameter ( "user_lastname" ),
                        request.getParameter ( "user_address" ),
                        postalcode,
                        request.getParameter ( "user_phone" ),
                        request.getParameter ( "user_email" ),
                        request.getParameter ( "new_password" ),
                        session.getAttribute ( "user_id" )
                    };
                }
                else
                {
                    update = "UPDATE Users "
                            + "SET Name = ?, LastName = ?, Address = ?, PostalCode = ?, Phone = ?, Email = ? "
                            + "WHERE Id = ?";

                    update_args = new Object[]
                    {
                        request.getParameter ( "user_name" ),
                        request.getParameter ( "user_lastname" ),
                        request.getParameter ( "user_address" ),
                        postalcode,
                        request.getParameter ( "user_phone" ),
                        request.getParameter ( "user_email" ),
                        session.getAttribute ( "user_id" )
                    };
                }
                db.ExecUpdate ( update, update_args );
                request.setAttribute ( "invalid_new_password", false );
            }
            else
            {
                request.setAttribute ( "invalid_new_password", true );
            }
        }

        rs = db.ExecQuery ( query, args );
        try
        {
            while ( rs.next () )
            {
                user = new User ( rs.getInt ( "id" ), rs.getString ( "name" ), rs.getString ( "lastname" ), rs.getString ( "address" ), rs.getInt ( "postalcode" ), rs.getString ( "phone" ), rs.getString ( "email" ), rs.getString ( "username" ), rs.getString ( "password" ), rs.getInt ( "rol" ) );
            }
        }
        catch ( SQLException ex )
        {
            Logger.getLogger ( Main.class.getName () ).log ( Level.SEVERE, null, ex );
            db.CloseConnection ();
        }
        db.CloseConnection ();
        request.setAttribute ( "user_profile", user );
    }

    private void Departments ( HttpServletRequest request )
    {
        List<String[]> departments = new ArrayList<String[]> ();
        Database db = new Database ();
        ResultSet rs;
        String query;

        query = "SELECT * FROM vw_departments\n"
                + "WHERE Status = ?;";

        int status;
        if ( request.getParameter ( "departmentStatus" ) == null )
        {
            status = 1;
        }
        else
        {
            status = Integer.parseInt ( request.getParameter ( "departmentStatus" ) );
        }

        try
        {
            rs = db.ExecQuery ( query, new Object[]
            {
                status
            } );
            while ( rs.next () )
            {
                departments.add ( new String[]
                {
                    Integer.toString ( rs.getInt ( 1 ) ), rs.getString ( 2 ), Integer.toString ( rs.getInt ( 3 ) ), Integer.toString ( rs.getInt ( 4 ) )
                } );
            }
        }
        catch ( SQLException ex )
        {
            Logger.getLogger ( Main.class.getName () ).log ( Level.SEVERE, null, ex );
            db.CloseConnection ();
        }
        db.CloseConnection ();
        request.setAttribute ( "departments", departments );
        request.setAttribute ( "departmentStatus", status );
    }

    private void AddDepartment ( HttpServletRequest request, HttpServletResponse response )
    {
        Database db = new Database ();
        String query = "INSERT INTO Departments (Name) VALUES (?)";
        Object[] args = new Object[]
        {
            request.getParameter ( "departmentName" )
        };

        try
        {
            db.ExecUpdate ( query, args );
            response.sendRedirect ( "main?section=Departments" );
        }
        catch ( Exception ex )
        {
            Logger.getLogger ( Main.class.getName () ).log ( Level.SEVERE, null, ex );
            db.CloseConnection ();
        }

        db.CloseConnection ();
    }

    private void StatusDepartment ( HttpServletRequest request, HttpServletResponse response )
    {
        Database db = new Database ();
        int id = Integer.parseInt ( request.getParameter ( "derpartmentId" ) );
        int products = 1;
        String query;
        ResultSet rs;

        try
        {
            if ( request.getParameter ( "section" ).equals ( "deleteDepartment" ) )
            {
                query = "SELECT * FROM vw_departments\n"
                        + "WHERE Id = ?;";
                rs = db.ExecQuery ( query, new Object[]
                {
                    id
                } );
                while ( rs.next () )
                {
                    products = rs.getInt ( 3 );
                }
                if ( products == 0 )
                {
                    query = "UPDATE Departments SET Active = ? WHERE Id = ?";
                    db.ExecUpdate ( query, new Object[]
                    {
                        0, id
                    } );
                    response.sendRedirect ( "main?section=Departments&departmentStatus=1" );
                }
            }
            else
            {
                query = "UPDATE Departments SET Active = ? WHERE Id = ?";
                db.ExecUpdate ( query, new Object[]
                {
                    1, id
                } );
                response.sendRedirect ( "main?section=Departments&departmentStatus=0" );
            }

        }
        catch ( SQLException ex )
        {
            Logger.getLogger ( Main.class.getName () ).log ( Level.SEVERE, null, ex );
            db.CloseConnection ();
        }
        catch ( IOException ex )
        {
            Logger.getLogger ( Main.class.getName () ).log ( Level.SEVERE, null, ex );
            db.CloseConnection ();
        }
        db.CloseConnection ();
    }

    private void Products ( HttpServletRequest request, HttpServletResponse response )
    {
        Database db = new Database ();
        String query;
        String department;
        ResultSet rs;
        List<Product> products = new ArrayList<> ();

        if ( request.getParameter ( "department" ) != null )
        {
            department = request.getParameter ( "department" );
            query = "SELECT * FROM Products"
                    + " Where DepartmentId = ?";

            rs = db.ExecQuery ( query, new Object[]
            {
                Integer.parseInt ( department )
            } );
            try
            {
                while ( rs.next () )
                {
                    Product tmp = new Product ();
                    tmp.setId ( rs.getInt ( "Id" ) );
                    tmp.setDepartmentId ( rs.getInt ( "DepartmentId" ) );
                    tmp.setName ( rs.getString ( "Name" ) );
                    tmp.setDescription ( rs.getString ( "Description" ).trim () );
                    tmp.setPrice ( rs.getFloat ( "Price" ) );
                    tmp.setQuantity ( rs.getInt ( "Quantity" ) );
                    tmp.setActive ( rs.getBoolean ( "Active" ) );
                    products.add ( tmp );
                }
            }
            catch ( SQLException ex )
            {
                Logger.getLogger ( Main.class.getName () ).log ( Level.SEVERE, null, ex );
                db.CloseConnection ();
            }
        }
        db.CloseConnection ();
        request.setAttribute ( "products", products );
    }

    private void ListDepartments ( HttpServletRequest request, HttpServletResponse response )
    {
        Database db = new Database ();
        String query;
        ResultSet rs;
        List<Department> departments = new ArrayList<> ();

        query = "SELECT * FROM Departments Where Active = 1";
        rs = db.ExecQuery ( query, null );
        try
        {
            while ( rs.next () )
            {
                Department tmp = new Department ();
                tmp.setId ( rs.getInt ( "Id" ) );
                tmp.setName ( rs.getString ( "Name" ) );
                tmp.setActive ( rs.getInt ( "Active" ) );
                departments.add ( tmp );
            }
        }
        catch ( SQLException ex )
        {
            Logger.getLogger ( Main.class.getName () ).log ( Level.SEVERE, null, ex );
            db.CloseConnection ();
        }
        db.CloseConnection ();
        request.setAttribute ( "departments", departments );
    }

    private void ProductoInfo ( HttpServletRequest request, HttpServletResponse response )
    {
        Database db = new Database ();
        String query;
        String id;
        ResultSet rs;
        Product tmp = new Product ();

        id = request.getParameter ( "edit" );
        query = "SELECT * FROM Products WHERE Id = ?";
        rs = db.ExecQuery ( query, new Object[]
        {
            Integer.parseInt ( id )
        } );
        try
        {
            while ( rs.next () )
            {
                tmp.setId ( rs.getInt ( "Id" ) );
                tmp.setDepartmentId ( rs.getInt ( "DepartmentId" ) );
                tmp.setName ( rs.getString ( "Name" ) );
                tmp.setDescription ( rs.getString ( "Description" ).trim () );
                tmp.setPrice ( rs.getFloat ( "Price" ) );
                tmp.setQuantity ( rs.getInt ( "Quantity" ) );
                tmp.setActive ( rs.getBoolean ( "Active" ) );
            }
        }
        catch ( SQLException ex )
        {
            Logger.getLogger ( Main.class.getName () ).log ( Level.SEVERE, null, ex );
        }
        db.CloseConnection ();
        request.setAttribute ( "product", tmp );
    }

    private void AddUpdateProduct ( HttpServletRequest request, HttpServletResponse response )
    {
        Database db = new Database ();
        String query;
        Object[] args;

        if ( request.getParameter ( "productId" ) == null )
        {
            query = "INSERT INTO Products VALUES (?,?,?,?,?,?)";
            args = new Object[]
            {
                Integer.parseInt ( request.getParameter ( "productDepartment" ) ),
                request.getParameter ( "productName" ).trim (),
                request.getParameter ( "productDescription" ).trim (),
                Float.parseFloat ( request.getParameter ( "productPrice" ) ),
                Integer.parseInt ( request.getParameter ( "productQuantity" ) ),
                Integer.parseInt ( request.getParameter ( "productStatus" ) )
            };

            db.ExecUpdate ( query, args );
        }
        else
        {
            query = "UPDATE Products "
                    + "SET DepartmentId = ?, Name = ?, Description = ?, Price = ?, Quantity = ?, Active = ? "
                    + "Where Id = ?";
            args = new Object[]
            {
                Integer.parseInt ( request.getParameter ( "productDepartment" ) ),
                request.getParameter ( "productName" ).trim (),
                request.getParameter ( "productDescription" ).trim (),
                Float.parseFloat ( request.getParameter ( "productPrice" ) ),
                Integer.parseInt ( request.getParameter ( "productQuantity" ) ),
                Integer.parseInt ( request.getParameter ( "productStatus" ) ),
                Integer.parseInt ( request.getParameter ( "productId" ) )
            };

            db.ExecUpdate ( query, args );
        }

        try
        {
            response.sendRedirect ( "main?section=Products&department=" + request.getParameter ( "productLastDepartment" ) );
        }
        catch ( IOException ex )
        {
            Logger.getLogger ( Main.class.getName () ).log ( Level.SEVERE, null, ex );
        }
    }

    private void AdminUsers ( HttpServletRequest request, HttpServletResponse response )
    {
        Database db = new Database ();
        String query;
        ResultSet rs;
        List<User> users = new ArrayList<> ();

        query = "SELECT * FROM Users WHERE Rol = 1";
        rs = db.ExecQuery ( query, null );

        try
        {
            while ( rs.next () )
            {
                User tmp = new User ();
                tmp.setId ( rs.getInt ( "Id" ) );
                tmp.setName ( rs.getString ( "Name" ) );
                tmp.setLastName ( rs.getString ( "LastName" ) );
                tmp.setEmail ( rs.getString ( "Email" ) );
                tmp.setPhone ( rs.getString ( "Phone" ) );
                tmp.setUsername ( rs.getString ( "Username" ) );
                tmp.setActive ( rs.getBoolean ( "Active" ) );
                users.add ( tmp );
            }
        }
        catch ( SQLException ex )
        {
            Logger.getLogger ( Main.class.getName () ).log ( Level.SEVERE, null, ex );
            db.CloseConnection ();
        }
        db.CloseConnection ();
        request.setAttribute ( "users", users );
    }

    private void AdminUserInfo ( HttpServletRequest request, HttpServletResponse response )
    {
        Database db = new Database ();
        String query;
        ResultSet rs;
        User user = new User ();

        query = "SELECT * FROM Users Where Id = ? AND Active = 1";
        rs = db.ExecQuery ( query, new Object[]
        {
            Integer.parseInt ( request.getParameter ( "edit" ) )
        } );

        try
        {
            while ( rs.next () )
            {
                user = new User ( rs.getInt ( "id" ), rs.getString ( "name" ), rs.getString ( "lastname" ), rs.getString ( "address" ), rs.getInt ( "postalcode" ), rs.getString ( "phone" ), rs.getString ( "email" ), rs.getString ( "username" ), rs.getString ( "password" ), rs.getInt ( "rol" ) );
                user.setActive ( rs.getBoolean ( "Active" ) );
            }
        }
        catch ( SQLException ex )
        {
            Logger.getLogger ( Main.class.getName () ).log ( Level.SEVERE, null, ex );
            db.CloseConnection ();
        }
        db.CloseConnection ();
        request.setAttribute ( "user", user );
    }

    private void AddUpdateAdminUser ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        Database db = new Database ();
        String query;
        Object[] args;
        boolean passwordMatch = true;

        if ( request.getParameter ( "new_password" ).equals ( request.getParameter ( "new_password2" ) ) )
        {
            if ( request.getParameter ( "user_id" ) == null )
            {
                query = "INSERT INTO Users VALUES(?,?,?,?,?,?,?,?,1,?)";
                args = new Object[]
                {
                    request.getParameter ( "user_name" ),
                    request.getParameter ( "user_lastname" ),
                    request.getParameter ( "user_address" ),
                    request.getParameter ( "user_postalCode" ),
                    request.getParameter ( "user_phone" ),
                    request.getParameter ( "user_email" ),
                    request.getParameter ( "user_username" ),
                    request.getParameter ( "new_password" ),
                    request.getParameter ( "user_status" )
                };

                db.ExecUpdate ( query, args );
            }
            else
            {
                int postalcode = Integer.parseInt ( request.getParameter ( "user_postalCode" ) );
                String update;
                Object[] update_args;
                if ( !request.getParameter ( "new_password" ).isEmpty () )
                {
                    update = "UPDATE Users "
                            + "SET Name = ?, LastName = ?, Address = ?, PostalCode = ?, Phone = ?, Email = ?, Password = ?, Active = ? "
                            + "WHERE Id = ?";

                    update_args = new Object[]
                    {
                        request.getParameter ( "user_name" ),
                        request.getParameter ( "user_lastname" ),
                        request.getParameter ( "user_address" ),
                        postalcode,
                        request.getParameter ( "user_phone" ),
                        request.getParameter ( "user_email" ),
                        request.getParameter ( "new_password" ),
                        request.getParameter ( "user_status" ),
                        request.getParameter ( "user_id" )
                    };
                }
                else
                {
                    update = "UPDATE Users "
                            + "SET Name = ?, LastName = ?, Address = ?, PostalCode = ?, Phone = ?, Email = ?, Active = ? "
                            + "WHERE Id = ?";

                    update_args = new Object[]
                    {
                        request.getParameter ( "user_name" ),
                        request.getParameter ( "user_lastname" ),
                        request.getParameter ( "user_address" ),
                        postalcode,
                        request.getParameter ( "user_phone" ),
                        request.getParameter ( "user_email" ),
                        request.getParameter ( "user_status" ),
                        request.getParameter ( "user_id" )
                    };
                }
                db.ExecUpdate ( update, update_args );
            }
        }
        else
        {
            passwordMatch = false;
        }

        db.CloseConnection ();

        if ( passwordMatch )
        {
            try
            {
                response.sendRedirect ( "main?section=AdminUsers" );
            }
            catch ( IOException ex )
            {
                Logger.getLogger ( Main.class.getName () ).log ( Level.SEVERE, null, ex );
            }
        }
        else
        {
            RequestDispatcher rd = null;
            request.setAttribute ( "invalid_new_password", true );
            rd = request.getRequestDispatcher ( "../WEB-INF/jsp/administration/admin_user_info.jsp" );
            rd.forward ( request, response );
        }
    }

    private void ClientStatus ( HttpServletRequest request, HttpServletResponse response )
    {
        Database db = new Database ();
        String id;
        String query;

        if ( request.getParameter ( "Active" ) != null )
        {
            id = request.getParameter ( "Active" );
            query = "UPDATE Users "
                    + "SET Active = 1 "
                    + "Where Id = ? AND Rol = 2";
        }
        else
        {
            id = request.getParameter ( "Inactive" );
            query = "UPDATE Users "
                    + "SET Active = 0 "
                    + "Where Id = ? AND Rol = 2";
        }
        db.ExecUpdate ( query, new Object[]
        {
            Integer.parseInt ( id )
        } );

        db.CloseConnection ();

        try
        {
            response.sendRedirect ( "main?section=Clients" );
        }
        catch ( IOException ex )
        {
            Logger.getLogger ( Main.class.getName () ).log ( Level.SEVERE, null, ex );
        }
    }

    private void Clients ( HttpServletRequest request, HttpServletResponse response )
    {
        Database db = new Database ();
        String query;
        ResultSet rs = null;
        List<User> clients = new ArrayList<> ();

        query = "SELECT * FROM Users WHERE Rol = 2";
        rs = db.ExecQuery ( query, null );

        try
        {
            while ( rs.next () )
            {
                User tmp = new User ( rs.getInt ( "id" ), rs.getString ( "name" ), rs.getString ( "lastname" ), rs.getString ( "address" ), rs.getInt ( "postalcode" ), rs.getString ( "phone" ), rs.getString ( "email" ), rs.getString ( "username" ), rs.getString ( "password" ), rs.getInt ( "rol" ) );
                tmp.setActive ( rs.getBoolean ( "Active" ) );
                clients.add ( tmp );
            }
        }
        catch ( SQLException ex )
        {
            Logger.getLogger ( Main.class.getName () ).log ( Level.SEVERE, null, ex );
        }
        db.CloseConnection ();
        request.setAttribute ( "clients", clients );
    }
}
