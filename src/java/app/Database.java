/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

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

    /**
     * Returns void. Este metodo solo cierra la conexion actual hacia Microsoft
     * SQL Server.
     */
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

    /**
     * Retorna ResultSet que se puede utilizar para analairzar todos los datos
     * extraidos de la Base de Datos. 
     * <p>
     * Este metodo se utlizara para ejecutar los Querys en la Base de Datos. 
     * Se manda el Querya ejecutar mediante un String, este puede hacer uso de 
     * comodines '?', pues el Query se pasa a un PreparedStatement. Los argumentos 
     * se pasan por medio de un Arreglo de Objetos en el orden que se quieren remplazar.
     * <p>
     * En caso de que el Query y los Argumentos sean mandados de forma
     * incorrecta retornara un NULL.
     *
     * @param query String a ejecutar en un PreparedStatement. Se puede utilizar
     * comodines '?'.
     *
     * @param args Object[] a remplazar por comodines. Se envian en el orden que
     * se quiere remplazar, en caso de no usar comodines enviar un NULL.
     *
     * @return Un ResultSet de la ejecucion del Query.
     */
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

    /**
     * Retorna un TRUE si el update se ejecuto de manera correcta, un FALSE
     * si se ejecuto de manera incorrecta.
     * <p>
     * Este metodo se utilizar para poder ejecutar INSERT, UPDATE y DELETE a
     * las tablas de la base de datos. Se manda la Sentencia SQL a ejecutar mediante
     * un String, este puede hacer uso de comodines '?', pues la Sentencia SQL se pasa a un
     * PreparedStatement. Los argumentos se pasan por medio de un Arreglo de Objetos 
     * en el orden que se quieren reemplazar.
     * <p>
     * En caso de que la Sentencia SQL y los Argumentos sean mandados de forma
     * incorrecta retornara un NULL.
     *
     * @param query String a ejecutar en un PreparedStatement. Se puede utilizar
     * comodines '?'.
     *
     * @param args Object[] a remplazar por comodines. Se envian en el orden que
     * se quiere remplazar, en caso de no usar comodines enviar un NULL.
     *
     * @return TRUE si el update se ejecuto de manera correcta. FALSE si se ejecuto
     * de manera incorrecta.
     */
    public boolean ExecUpdate ( String query, Object[] args )
    {
        PreparedStatement ps = null;

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
            ps.executeUpdate ();

            return true;
        }
        catch ( Exception e )
        {
            e.printStackTrace ();
            return false;
        }
        
    }

}
