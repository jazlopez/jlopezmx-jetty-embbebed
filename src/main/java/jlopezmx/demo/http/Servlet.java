package jlopezmx.demo.http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class Servlet extends HttpServlet {

    String log;

    private Date getNow() {

        return new Date();
    }

    /**
     *
     * @param log String
     */
    @Override
    public void log(String log) {

        getServletContext().log(log);
    }


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
}