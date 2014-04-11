/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import com.itextpdf.awt.*;
import com.itextpdf.testutils.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.xmp.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Armando
 */
public class GenerarPDF extends HttpServlet
{

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
        Database db = new Database ();
        String idSale = request.getParameter ( "Sale" );
        String query;
        ResultSet rsPurchasedProducts;
        java.util.List<String[]> departments = new ArrayList<String[]> ();

        query = " SELECT P.Name, D.AmountProduct, D.PurchasePrice FROM Sales S\n"
                + " INNER JOIN DetailSales D ON D.SaleId = S.Id\n"
                + " INNER JOIN Products P ON P.Id = D.ProductId\n"
                + " WHERE S.Id = ?";

        HttpSession session = request.getSession ();
        try
        {
            OutputStream file = new FileOutputStream ( new File ( "" + session.getServletContext ().getRealPath ( "reporte/reporteCompra.pdf" ) ) );

            Document document = new Document ();
            PdfWriter.getInstance ( document, file );
            document.open ();
            document.close ();
            file.close ();
        }
        catch ( Exception e )
        {
            e.printStackTrace ();
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
