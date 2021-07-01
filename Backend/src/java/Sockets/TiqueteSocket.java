package Sockets;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.Path;

@ServerEndpoint("/tiquetesSocket")
public class TiqueteSocket {

    private static final Map<String, Session> sessions = new ConcurrentHashMap<>();

    /*On open nos permite interceptar la creacion de una nueva sesión.
    La clase de sesión nos permite enviar los datos al usuario*/
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Conexión #" + session.getId() + " se ha abierto TIQUETES");
        sessions.put(session.getId(), session);
    }

    /*
    Coando un usuario envúa un mensaje al servidor, este método interceptar el mensaje
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("La sesion #  " + session.getId() + " ha mandado el siguiente mensaje\n=====================\n"+message);
        try {
            for (Map.Entry<String, Session> set : sessions.entrySet()) {
                set.getValue().getBasicRemote().sendText(message);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Sesión  " + session.getId() + " ha terminado");
        sessions.remove(session.getId());
    }
    @OnError
    public void onError(Session session, Throwable thr) {
        System.out.println("EROR EN SOCKET: #" + session.getId() + " -->" + thr.getMessage());
    }
}