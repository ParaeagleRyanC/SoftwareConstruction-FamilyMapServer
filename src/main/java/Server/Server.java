package Server;

import Handlers.*;
import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;

public class Server {

    // The maximum number of waiting incoming connections to queue.
    private static final int MAX_WAITING_CONNECTIONS = 12;

    // This method initializes and runs the server.
    private void run(String portNumber) {

    HttpServer server;

    // Since the server has no "user interface", it should display "log"
    // messages containing information about its internal activities.
    // This allows a system administrator (or you) to know what is happening
    // inside the server, which can be useful for diagnosing problems
    // that may occur.
    System.out.println("Initializing HTTP Server");

    try {
        // Create a new HttpServer object.
        server = HttpServer.create(
                new InetSocketAddress(Integer.parseInt(portNumber)),
                MAX_WAITING_CONNECTIONS);
    } catch (IOException e) {
        e.printStackTrace();
        return;
    }

    // Indicate that we are using the default "executor".
    // This line is necessary, but its function is unimportant for our purposes.
    server.setExecutor(null);

    // Log message indicating that the server is creating and installing its HTTP handlers.
    // The HttpServer class listens for incoming HTTP requests.
    // When one is received, it looks at the URL path inside the HTTP request,
    // and forwards the request to the handler for that URL path.
    System.out.println("Creating contexts");

    server.createContext("/user/register", new RegisterHandler());
    server.createContext("/user/login", new LoginHandler());
    server.createContext("/clear", new ClearHandler());
    server.createContext("/fill/", new FillHandler());
    server.createContext("/load", new LoadHandler());
    server.createContext("/person/", new PersonSingleMemberHandler());
    server.createContext("/person", new PersonAllFamilyMemberHandler());
    server.createContext("/event/", new EventSingleHandler());
    server.createContext("/event", new EventAllFromAllMemberHandler());

    // Create and install the "default" (or "file") HTTP handler.
    // All requests that do not match the other handler URLs will be passed to this handle.
    server.createContext("/", new FileHandler());

    // Log message indicating that the HttpServer is about the start
    // accepting incoming client connections.
    System.out.println("Starting server");

    // Tells the HttpServer to start accepting incoming client connections.
    // This method call will return immediately, and the "main" method for the program will also complete.
    // Even though the "main" method has completed, the program will continue running
    // because the HttpServer object we created is still running in the background.
    server.start();

    // Log message indicating that the server has successfully started.
    System.out.println("Server started");
    }

    // "main" method for the server program
    // "args" should contain one command-line argument, which is the port number
    // on which the server should accept incoming client connections.
    public static void main(String[] args) {
        String portNumber = args[0];
        new Server().run(portNumber);
    }
}

