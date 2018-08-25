package jlopezmx.demo.http;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.NCSARequestLog;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

/**
 * Jetty Embebbed Launcher
 *
 * Jaziel Lopez, Software Engineer, BC, Mexico
 *
 * juan.jaziel@gmail.com
 *
 * https://jlopez.mx
 *
 */
public class Launcher {

    // Server Port
    private static final Integer PORT_PROPERTY =  8060;

    // Server min threads
    private static final Integer MIN_THREADS =  1;

    // Server max threads
    private static final Integer MAX_THREADS =  100;

    // The time in milliseconds that the connection can be idle before it is closed.
    private static final Integer IDLE_TIMEOUT =  30;

    // Server log file format name
    private static final String LOG_FORMAT_NAME = "jetty-yyyy_mm_dd.request.log";
    /**
     * Jetty web server port
     *
     * Customize port by defining an environment variable JETTY_SERVER_PORT
     *
     * Otherwise PORT_PROPERTY (see below) is returned
     *
     * @return port Integer
     */
    private static Integer getPort() {

        return (System.getenv("JETTY_SERVER_PORT") != null) ?

                Integer.parseInt(System.getenv("JETTY_SERVER_PORT")) : PORT_PROPERTY;

    }

    /**
     * Launcher
     *
     * @param args String[]
     *
     * @throws RuntimeException RuntimeException
     */
    public static void main(String[] args) throws RuntimeException{

        try {

            /*
             * Queue Thread Pool
             */
            Server server = new Server(new QueuedThreadPool(MAX_THREADS, MIN_THREADS, IDLE_TIMEOUT));

            /*
             * Connector Port
             */
            ServerConnector connector = new ServerConnector(server);
            connector.setPort(getPort());
            server.setConnectors(new Connector[] {connector});

            /*
             * Servlet Handler
             */
            ServletHandler servletHandler = new ServletHandler();

            /*
             * Log every request
             */
            NCSARequestLog requestLog = new NCSARequestLog(LOG_FORMAT_NAME);
            requestLog.setAppend(true);
            requestLog.setExtended(true);
            requestLog.setLogServer(true);
            requestLog.setLogLatency(true);
            requestLog.setLogCookies(true);
            server.setRequestLog(requestLog);

            /*
             * Servlet for all routes
             */
            servletHandler.addServletWithMapping(Servlet.class, "/*");
            server.setHandler(servletHandler);

            /*
             * Start web server
             */
            server.start();

            /*
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
