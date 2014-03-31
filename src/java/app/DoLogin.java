/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rogeliotorres
 */
public class DoLogin extends HttpServlet
{

    Database db = new Database ();

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

        HttpSession session = request.getSession ( true );
        session.setMaxInactiveInterval ( 60 * 60 * 12 );
        session.setAttribute ( "logged_in", false );

        Object argumentos[] = new Object[]
        {
            request.getParameter ( "login" ),
            request.getParameter ( "password" )
        };

        ResultSet rs = db.ExecQuery ( "Select * from users where username = ? and password = ?", argumentos );

        if ( rs != null )
        {
            try
            {
                if ( rs.next () )
                {
                    session.setAttribute ( "logged_in", true );
                    session.setAttribute ( "user_id", rs.getInt ( "id" ) );
                    session.setAttribute ( "name", rs.getString ( "name" ) );
                    session.setAttribute ( "rol", rs.getInt ( "rol" ) );

                    if ( rs.getInt ( "rol" ) == 1 )
                    {
                        response.sendRedirect ( "administration/main" );
                    }
                    else
                    {
                        response.sendRedirect ( "main" );
                    }
                }
                else
                {
                    response.sendRedirect ( "access?error=true" );
                }
            }
            catch ( SQLException ex )
            {
                Logger.getLogger ( DoLogin.class.getName () ).log ( Level.SEVERE, null, ex );
            }
        }
        else
        {
            response.sendRedirect ( "access?error=true" );
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
}
