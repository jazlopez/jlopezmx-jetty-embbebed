package jlopezmx.demo.http;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;

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
public class Servlet extends HttpServlet {


    private TreeMap<String, Integer> treeMap;
    /**
     *
     */
    private ObjectMapper objectMapper;

    /**
     *
     */
    public Servlet() {

        this.objectMapper = new ObjectMapper();

        this.treeMap = new TreeMap<String, Integer>();
    }

    /**
     *
     * @param log String
     */
    @Override
    public void log(String log) {

        getServletContext().log(log);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Worker worker = new Worker();

        log("doPost has been invoked");

        extractServletMessage(request);

        worker.write(request.getInputStream(), null, null);

        try {


            response.setContentType("application/json; charset=UTF-8");

            response.setHeader("origin", "jlopez.mx");

            response.setStatus(HttpServletResponse.SC_OK);

            response.getWriter().println("OK");

            log("doPost completed to process");

        }catch (Exception e) {

            throw new ServletException(e.getMessage());
        }

    }

    /**
     *
     * @param req
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        Worker worker = new Worker();

        log("doGet has been invoked");

        worker.write(req.getInputStream(), null, null);

            try {


                response.setContentType("application/json; charset=UTF-8");

                response.setHeader("origin", "jlopez.mx");

                response.setStatus(HttpServletResponse.SC_OK);

                response.getWriter().println("OK");

                log("doGet completed to process");

            }catch (Exception e) {

                throw new ServletException(e.getMessage());
            }
    }

    /**
     * Log servlet stats to file in system
     */
    private void logServletStats() {

        try {

            PrintWriter writer = new PrintWriter(new FileWriter("stats.log"));

            writer.print("Log Message stats:");
            writer.print("\n");

            for(String key : this.treeMap.keySet()){

                log(String.format("%s: [%d]", key, Integer.parseInt(this.treeMap.get(key).toString())));

                writer.printf("%s: [%d]", key, Integer.parseInt(this.treeMap.get(key).toString()));
                writer.print("\n");

            }
            writer.print("===================================");
            writer.print("\n");
            writer.flush();
            writer.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     *
     * @param request
     */
    private void extractServletMessage(HttpServletRequest request){

        int current = 0;

        log(String.format("Header inspect: amz-topic-arn [%s]",request.getHeader("amz-topic-arn")));

        log(String.format("Post Body Parameter: message [%s]", request.getParameter("message")));

        try {
            Message incoming = this.objectMapper.readValue(request.getParameter("message"), Message.class);

            log(String.format("Incoming message.deviceId [%s]", incoming.getDeviceId()));
            log(String.format("Incoming message.timestamp [%s]", incoming.getTimestamp()));

            if(this.treeMap.containsKey(incoming.getDeviceId())) {

                current = Integer.parseInt(this.treeMap.get(incoming.getDeviceId()).toString());
            }

            current++;

            this.treeMap.put(incoming.getDeviceId(),current);

            log(String.format("Received messages for message.deviceId [%s] Total: [%d]", incoming.getDeviceId(), current));

            logServletStats();

        }catch(JsonMappingException e) {

            e.printStackTrace();
        }catch (IOException e) {

            e.printStackTrace();
        }
    }
}