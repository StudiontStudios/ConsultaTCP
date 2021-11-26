/*
Realiza un servidor TCP que retorne los datos de un empleado
consultando a una BD a partir del código del empleado pasado desde
un cliente TCP. El cliente lo puedes hacer en modo consola.
 */
package PSP_T2_TCP_IP.Chat.Ej2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Vespertino
 */
public class Ej2 {

    public static void main(String[] args) {

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:E:\\sqlite-tools-win32-x86-3360000\\ejemplo.db", "", "");

            try {
                ServerSocket serverSocket = new ServerSocket(2045);
                Socket clienteSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clienteSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

                String inputLine = in.readLine();
                System.out.println("El codigo de CLIENTE ES :" + inputLine);

                String sql = "select * from CLIENTES where ID = " + Integer.parseInt(inputLine) + " ;";

                ResultSet rs;
                rs = sacarConsulta(conexion, sql);//saca la consulta

                String outputLine = rs.getString(2).toString();
                out.println(outputLine);

                out.close();
                in.close();
                clienteSocket.close();
                serverSocket.close();

            } catch (IOException ex) {
            }

        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ResultSet sacarConsulta(Connection conexion, String sql) throws SQLException {
        System.out.println(sql);
        PreparedStatement sentencia
                = conexion.prepareStatement(sql);

        try {
            ResultSet rs = sentencia.executeQuery(); //es Query es para hacer consultas select
            // Nos recoremos los objetos de la coleccion

            return rs;

        } catch (SQLException e) {
            System.out.println("HA OCURRIDO UNA EXCEPCI�N:");
            System.out.println("Mensaje:    " + e.getMessage());
            System.out.println("SQL estado: " + e.getSQLState());
            System.out.println("C�d error:  " + e.getErrorCode());
        }

        sentencia.close(); // Cerrar Statement
        conexion.close(); // Cerrar conexi�n
        return null;
    }

}
