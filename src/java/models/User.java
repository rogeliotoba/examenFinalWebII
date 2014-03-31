/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author rogeliotorres
 */
public class User
{

    private int id;
    private String name;
    private String lastName;
    private String address;
    private int postalCode;
    private String phone;
    private String email;
    private String username;
    private String password;
    private int rol;

    public User ()
    {
    }

    public User ( int id, String name, String lastName, String address, int postalCode, String phone, String email, String username, String password, int rol )
    {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

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

    public String getLastName ()
    {
        return lastName;
    }

    public void setLastName ( String lastName )
    {
        this.lastName = lastName;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress ( String address )
    {
        this.address = address;
    }

    public int getPostalCode ()
    {
        return postalCode;
    }

    public void setPostalCode ( int postalCode )
    {
        this.postalCode = postalCode;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone ( String phone )
    {
        this.phone = phone;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail ( String email )
    {
        this.email = email;
    }

    public String getUsername ()
    {
        return username;
    }

    public void setUsername ( String username )
    {
        this.username = username;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword ( String password )
    {
        this.password = password;
    }

    public int getRol ()
    {
        return rol;
    }

    public void setRol ( int rol )
    {
        this.rol = rol;
    }

}
