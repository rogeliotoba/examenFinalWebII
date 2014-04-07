/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author rogeliotorres
 */
public class Department
{

    private int id;
    private String name;
    private int active;

    public int getId ()
    {
        return id;
    }

    public void setId ( int id )
    {
        this.id = id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName ( String name )
    {
        this.name = name;
    }

    public int getActive ()
    {
        return active;
    }

    public void setActive ( int active )
    {
        this.active = active;
    }
}
