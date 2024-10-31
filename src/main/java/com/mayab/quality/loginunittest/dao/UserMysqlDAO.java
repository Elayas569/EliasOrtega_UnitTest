package com.mayab.quality.loginunittest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mayab.quality.loginunittest.model.User;

public class UserMysqlDAO implements IDAOUser {
    public Connection getConnectionMySQL() {

        Connection con = null;
        try {
            // Establish the driver connector
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Set the URI for connecting the MySql database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/calidad2024", "root", "123456");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    @Override
    public User findById(String id) {
        Connection connection = getConnectionMySQL();
        PreparedStatement preparedStatement;
        ResultSet rs;

        User retrieved = null;

        try {
            // Declare statement query to run
            preparedStatement = connection.prepareStatement("SELECT * from alumnos_tbl WHERE id = ?");
            // Set the values to match in the ? on query
            preparedStatement.setString(1, id);
            rs = preparedStatement.executeQuery();

            // Obtain the pointer to the data in generated table
            rs.next();

            String retrivedId = rs.getString(1);
            String retrivedName = rs.getString(2);
            String retrivedEmail = rs.getString(3);

            retrieved = new User(retrivedId, retrivedName, retrivedEmail);

            // Return the values of the search
            System.out.println("\n");
            System.out.println("---Alumno---");
            System.out.println("ID: " + retrieved.getId());
            System.out.println("Username: " + retrieved.getUsername());
            System.out.println("Email: " + retrieved.getEmail() + "\n");
            // Close connection with the database
            connection.close();
            rs.close();
            preparedStatement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        // Return statement
        return retrieved;
    }

    @Override
    public boolean logIn(String username, String password) {
        // Your login logic here, e.g., checking credentials against a database
        return false; // Replace with actual logic
    }

    @Override
    public int registerUser(User a) {
        Connection connection = getConnectionMySQL();
        int result = -1;
        try {
            // Declare statement query to run
            PreparedStatement preparedStatement;
            preparedStatement = connection
                    .prepareStatement("insert INTO usuarios(username,email,password) values(?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
            // Set the values to match in the ? on query
            preparedStatement.setString(1, a.getUsername());
            preparedStatement.setString(2, a.getEmail());
            preparedStatement.setString(3, a.getPassword());

            // Return the result of connection nad statement
            if (preparedStatement.executeUpdate() >= 1) {
                try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                    if (rs.next()) {
                        result = rs.getInt(1);
                    }
                }
            }
            System.out.println("\n");
            System.out.println("Alumno añadido con exito");
            System.out.println(">> Return: " + result + "\n");
            // Close connection with the database
            connection.close();
            preparedStatement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        // Return statement
        return result;
    }

    @Override
    public boolean deleteUser(User a) {
        Connection connection = getConnectionMySQL();
        boolean result = false;

        try {
            // Declare statement query to run
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("Delete from alumnos_tbl WHERE id = ?");
            // Set the values to match in the ? on query
            preparedStatement.setInt(1, a.getId());

            // Return the result of connection and statement
            if (preparedStatement.executeUpdate() >= 1) {
                result = true;
            }
            System.out.println("\n");
            System.out.println("Alumno eliminado con exito");
            System.out.println(">> Return: " + result + "\n");
            // Close connection with the database
            connection.close();
            preparedStatement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        // Return statement
        return result;
    }

    public boolean updateEmail(User a) {
        Connection connection = getConnectionMySQL();
        boolean result = false;

        try {
            // Declare statement query to run
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("UPDATE alumnos_tbl SET email = ? WHERE id = ?");
            // Set the values to match in the ? on query
            preparedStatement.setString(1, a.getEmail());
            // preparedStatement.setString(2, a.getId());

            // Return the result of connection and statement
            if (preparedStatement.executeUpdate() >= 1) {
                result = true;
            }
            System.out.println("\n");
            System.out.println("Correo de alumno con ID: " + a.getId() + " actualizado");
            System.out.println(">> Return: " + result + "\n");
            // Close connection with the database
            connection.close();
            preparedStatement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        // Return statement
        return result;
    }

    // overrides para que jala la interface
    @Override
    public User findUserByUsername(String username) {
        Connection connection = getConnectionMySQL();
        PreparedStatement preparedStatement;
        ResultSet rs;

        User result = null;

        try {
            // Declare statement query to run
            preparedStatement = connection.prepareStatement("SELECT * from usuarios WHERE name = ?");
            // Set the values to match in the ? on query
            preparedStatement.setString(1, username);
            rs = preparedStatement.executeQuery();

            // Obtain the pointer to the data in generated table
            rs.next();

            int id = rs.getInt(1);
            String userName = rs.getString(2);
            String email = rs.getString(3);
            String password = rs.getString(4);
            boolean isLogged = rs.getBoolean(5);

            result = new User(userName, password, email);
            result.setId(id);
            result.setLogged(isLogged);

            // Return the values of the search
            System.out.println("\n");
            System.out.println("---Alumno---");
            System.out.println("ID: " + result.getId());
            System.out.println("Nombre: " + result.getUsername());
            System.out.println("Email: " + result.getEmail());
            System.out.println("Tipo: " + result.isLogged() + "\n");
            // Close connection with the database
            connection.close();
            rs.close();
            preparedStatement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        // Return statement
        return result;

    }

    @Override
    public User findUserByEmail(String email) {
        Connection connection = getConnectionMySQL();
        PreparedStatement preparedStatement;
        ResultSet rs;

        User result = null;

        try {
            // Declare statement query to run
            preparedStatement = connection.prepareStatement("SELECT * from usuarios WHERE email = ?");
            // Set the values to match in the ? on query
            preparedStatement.setString(1, email);
            rs = preparedStatement.executeQuery();

            // Obtain the pointer to the data in generated table
            rs.next();

            int id = rs.getInt(1);
            String username = rs.getString(2);
            String emailUser = rs.getString(3);
            String password = rs.getString(4);
            boolean isLogged = rs.getBoolean(5);

            result = new User(username, password, emailUser);
            result.setId(id);
            result.setLogged(isLogged);

            // Return the values of the search
            System.out.println("\n");
            System.out.println("---Alumno---");
            System.out.println("ID: " + result.getId());
            System.out.println("Nombre: " + result.getUsername());
            System.out.println("Email: " + result.getEmail());
            System.out.println("Tipo: " + result.isLogged() + "\n");
            // Close connection with the database
            connection.close();
            rs.close();
            preparedStatement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        // Return statement
        return result;

    }

    @Override
    public User updateUser(User userNew) {
        Connection connection = getConnectionMySQL();
        User result = null;

        try {
            // Declare statement query to run
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("UPDATE usuarios SET name = ?,password= ? WHERE id = ?");
            // Set the values to match in the ? on query
            preparedStatement.setString(1, userNew.getUsername());
            preparedStatement.setString(2, userNew.getPassword());
            preparedStatement.setInt(3, userNew.getId());
            // Return the result of connection and statement
            if (preparedStatement.executeUpdate() >= 1) {
                result = userNew;
            }
            System.out.println("\n");
            // Close connection with the database
            connection.close();
            preparedStatement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        // Return statement
        return result;
    }

    @Override
    public int save(User user) {
        // Method to save a user to the database
        return 0;
    }

    @Override
    public List<User> findAll() {
        Connection connection = getConnectionMySQL();
        PreparedStatement preparedStatement;
        ResultSet rs;
        boolean result = false;

        User retrieved = null;

        List<User> listaAlumnos = new ArrayList<User>();

        try {
            // Declare statement query to run
            preparedStatement = connection.prepareStatement("SELECT * from usuarios");
            // Set the values to match in the ? on query
            rs = preparedStatement.executeQuery();

            // Obtain the pointer to the data in generated table
            while (rs.next()) {

                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String password = rs.getString(4);
                boolean log = rs.getBoolean(5);
                retrieved = new User(name, email, password);
                retrieved.setId(id);
                retrieved.setLogged(log);
                listaAlumnos.add(retrieved);
            }

            connection.close();
            rs.close();
            preparedStatement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return listaAlumnos;

    }

    @Override
    public boolean deleteById(int id) {
        Connection connection = getConnectionMySQL();
        boolean result = false;

        try {
            // Declare statement query to run
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("Delete from usuarios WHERE id = ?");
            // Set the values to match in the ? on query
            preparedStatement.setInt(1, id);

            // Return the result of connection and statement
            if (preparedStatement.executeUpdate() >= 1) {
                result = true;
            }
            System.out.println("\n");
            System.out.println("Alumno eliminado con exito");
            System.out.println(">> Return: " + result + "\n");
            // Close connection with the database
            connection.close();
            preparedStatement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        // Return statement
        return result;

    }
}
