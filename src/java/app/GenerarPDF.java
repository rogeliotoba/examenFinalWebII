/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import com.itextpdf.awt.*;
import com.itextpdf.testutils.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.xmp.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String queryPurchasedProducts;
        String querySaleInfo;
        ResultSet rsPurchasedProducts;
        ResultSet rsSaleInfo;
        Object[] args = new Object[]
        {
            Integer.parseInt ( idSale )
        };
        java.util.List<String[]> purchasedProduct = new ArrayList<String[]> ();
        String[] saleInfo = new String[ 14 ];

        queryPurchasedProducts = " SELECT P.Id, P.Name, D.AmountProduct, D.PurchasePrice FROM Sales S\n"
                + " INNER JOIN DetailSales D ON D.SaleId = S.Id\n"
                + " INNER JOIN Products P ON P.Id = D.ProductId\n"
                + " WHERE S.Id = ?";

        querySaleInfo = "SELECT * FROM Sales S\n"
                + "INNER JOIN Users U ON S.UserId = U.Id\n"
                + "WHERE S.Id = ?";

        rsPurchasedProducts = db.ExecQuery ( queryPurchasedProducts, args );
        rsSaleInfo = db.ExecQuery ( querySaleInfo, args );

        try
        {
            while ( rsPurchasedProducts.next () )
            {
                String[] products = new String[]
                {
                    rsPurchasedProducts.getString ( "Id" ),
                    rsPurchasedProducts.getString ( "Name" ),
                    rsPurchasedProducts.getString ( "AmountProduct" ),
                    rsPurchasedProducts.getString ( "PurchasePrice" )
                };
                purchasedProduct.add ( products );
            }

            while ( rsSaleInfo.next () )
            {
                for ( int i = 0; i < 14; i++ )
                {
                    saleInfo[i] = rsSaleInfo.getObject ( i + 1 ).toString ();
                }
            }
        }
        catch ( SQLException ex )
        {
            Logger.getLogger ( GenerarPDF.class.getName () ).log ( Level.SEVERE, null, ex );
            db.CloseConnection ();
        }
        db.CloseConnection ();

        HttpSession session = request.getSession ();
        try
        {
            OutputStream file = new FileOutputStream ( new File ( "" + session.getServletContext ().getRealPath ( "reporte/reporteCompra.pdf" ) ) );

            Document document = new Document ();
            PdfWriter.getInstance ( document, file );
            document.open ();

            document.add ( new Paragraph ( "COMPRA: " + saleInfo[0] ) );
            document.add ( new Paragraph ( "CLIENTE: " + saleInfo[4] + " " + saleInfo[5] ) );
            document.add ( new Paragraph ( "DOMICILIO: " + saleInfo[6] + " CP " + saleInfo[7] ) );
            document.add ( new Paragraph ( "TELEFONO: " + saleInfo[8] ) );
            document.add ( new Paragraph ( "E-MAIL: " + saleInfo[9] ) );
            document.add ( new Paragraph ( " " ) );

            PdfPTable table = new PdfPTable ( 4 );
            table.addCell ( "Codigo de Producto" );
            table.addCell ( "Producto" );
            table.addCell ( "Cantidad" );
            table.addCell ( "Precio Unitario" );
            for ( String[] product : purchasedProduct )
            {
                for ( int i = 0; i < 4; i++ )
                {
                    if ( i == 3 )
                    {
                        table.addCell ( "$ " + product[i] );
                    }
                    else
                    {
                        table.addCell ( product[i] );
                    }
                }
            }
            document.add ( table );

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
