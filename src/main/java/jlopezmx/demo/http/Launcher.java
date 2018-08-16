package jlopezmx.demo.http;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class Launcher {

    private static final String PORT_PROPERTY = "8080";

    private Launcher() {

    }

    public static void main(String[] args) throws RuntimeException{

        final int PORT = 8060;

        try {

            Server server = new Server(PORT);

            ServletHandler servletHandler = new ServletHandler();

            servletHandler.addServletWithMapping(Servlet.class, "/*");

            server.setHandler(servletHandler);

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
