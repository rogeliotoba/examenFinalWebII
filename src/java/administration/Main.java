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
                            foward = true;
                            rd = request.getRequestDispatcher ( "../WEB-INF/jsp/administration/products.jsp" );
                            break;

                        case "Clients":
                            foward = true;
                            rd = request.getRequestDispatcher ( "../WEB-INF/jsp/administration/clients.jsp" );
                            break;

                        case "AdminUsers":
                            foward = true;
                            rd = request.getRequestDispatcher ( "../WEB-INF/jsp/administration/admin_users.jsp" );
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
        }
        catch ( IOException ex )
        {
            Logger.getLogger ( Main.class.getName () ).log ( Level.SEVERE, null, ex );
        }
        db.CloseConnection ();
    }
}
