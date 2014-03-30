/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mikitl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Armando
 */
public class Database
{

    private final static String SQL_USERNAME = "sa";
    private final static String SQL_PASSWORD = "admin";
    private final static String SQL_URL = "jdbc:sqlserver://MIKITL-AW\\AW_MSSQL;databaseName=dbTiendeishonWeb2";

    private Connection con = null;

    public Database ()
    {
        try
        {
            Class.forName ( "com.microsoft.sqlserver.jdbc.SQLServerDriver" );
            con = DriverManager.getConnection ( SQL_URL, SQL_USERNAME, SQL_PASSWORD );
        }
        catch ( Exception e )
        {
            e.printStackTrace ();
        }
    }

    public void CloseConnection ()
    {
        try
        {
            con.close ();
        }
        catch ( SQLException ex )
        {
            Logger.getLogger ( Database.class.getName () ).log ( Level.SEVERE, null, ex );
        }
    }

    public ResultSet ExecQuery ( String query, Object[] args )
    {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            ps = con.prepareStatement ( query );
            if ( args != null )
            {
                for ( int i = 0; i < args.length; i++ )
                {
                    ps.setObject ( i + 1, args[i] );
                }
            }
            rs = ps.executeQuery ();
        }
        catch ( Exception e )
        {
            e.printStackTrace ();
        }

        return rs;
    }
}
