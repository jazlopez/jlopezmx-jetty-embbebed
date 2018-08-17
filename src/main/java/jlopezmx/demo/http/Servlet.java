package jlopezmx.demo.http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        System.out.println(req);

        response.setContentType("application/json; charset=UTF-8");

        response.setHeader("origin", "jlopez.mx");

        response.setStatus(HttpServletResponse.SC_OK);

        response.getWriter().println("OK");
    }
}
