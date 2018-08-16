package jlopezmx.demo.http;

import org.eclipse.jetty.server.Server;

public class Launcher {

    private static final String PORT_PROPERTY = "8080";

    private Launcher() {

    }

    public static void main(String[] args) throws RuntimeException{

//        ContextHandler contextHandler = new ContextHandler();
//        contextHandler.setContextPath("/");

        final int PORT = 8060;

        try {

            Server server = new Server(PORT);

            server.start();
            server.join();
        } catch (InterruptedException e) {

            System.out.print(e.getMessage());

            throw new RuntimeException(e);

        }catch (Exception e) {

            System.out.print(e.getMessage());

            throw new RuntimeException(e);
        }
    }
}
