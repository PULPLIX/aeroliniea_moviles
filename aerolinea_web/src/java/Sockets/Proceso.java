package Sockets;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/proceso")
public class Proceso {

    /*On open nos permite interceptar la creacion de una nueva sesión.
    La clase de sesión nos permite enviar los datos al usuario*/
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Conexión #" + session.getId() + " se ha abierto");
        try {
            session.getBasicRemote().sendText("Conección establecida");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*
    Coando un usuario envúa un mensaje al servidor, este método interceptar el mensaje
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        Gson gson = new Gson();
        //Ejemplo de como crear un objeto de Java desde un JSON por medio de la libreria de Gson
        //Usuario usu = gson.fromJson(message, Usuario.class);
        ArrayList<String> array = gson.fromJson(message, ArrayList.class);
        String path = array.get(0);

        switch (path) {
            case "conexion":
                System.out.println("Se ha conectado un nuevo socket");
            case "/insert":
                this.insert(array.get(1));
            case "/delete": ;
                this.delete(array.get(1));
            case "/update": ;
                this.update(array.get(1));

        }
        System.out.println("Mensaje " + session.getId() + ": " + message);
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Sesión  " + session.getId() + " ha terminado");
    }

    private void insert(String objeto) {
        System.out.println("Insertando: " +objeto);
    }

    private void delete(String objeto) {
        System.out.println("Eliminando: " +objeto);

    }

    private void update(String objeto) {
        System.out.println("Actualizando: " + objeto);

    }
}
