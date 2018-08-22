package jlopezmx.demo.http;

import org.eclipse.jetty.server.NCSARequestLog;
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

            /**
             * Log every request
             */
            NCSARequestLog requestLog = new NCSARequestLog("jetty-yyyy_mm_dd.request.log");
            requestLog.setAppend(true);
            requestLog.setExtended(true);
            requestLog.setLogServer(true);
            requestLog.setLogLatency(true);
            requestLog.setLogCookies(true);
            server.setRequestLog(requestLog);

            /**
             * Servlet for all routes
             */
            servletHandler.addServletWithMapping(Servlet.class, "/*");
            server.setHandler(servletHandler);

            /**
             * Start webserver
             */
            server.start();

            /**
             * Thread block until jetty launch application
             */
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
