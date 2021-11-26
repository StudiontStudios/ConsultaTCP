/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSP_T2_TCP_IP.Chat.Ej2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Vespertino
 */
public class Ej2EnvioDatos {

    public static void main(String[] args) {
        String ip = "10.2.2.7";
        int puerto = 2045;
        try {
            Socket clienteSocket = new Socket(ip, puerto);
            PrintWriter out = new PrintWriter(clienteSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

            System.out.println("Enviando : 1");
            out.println("1");

            System.out.println("Recibiendo : " + in.readLine());

            out.close();
            in.close();
            clienteSocket.close();
        } catch (UnknownHostException e) {
        } catch (IOException e) {
        }
    }

}
