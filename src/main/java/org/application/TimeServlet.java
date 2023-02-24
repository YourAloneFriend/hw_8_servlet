package org.application;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String timezone = req.getParameter("timezone");
        if(timezone == null)
            timezone = "UTC";

        if(timezone.contains("UTC "))
            timezone = timezone.replace(" ", "+");

        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().write("<h1>TimeServlet</h1>");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        //ZonedDateTime time = LocalDateTime.now().atZone(ZoneId.of(timezone));
        ZonedDateTime time = Instant.now().atZone(ZoneId.of(timezone));

        resp.getWriter().write(String.format("<h2>%s %s</h2>", time.format(formatter), timezone));
        resp.getWriter().close();
    }
}
