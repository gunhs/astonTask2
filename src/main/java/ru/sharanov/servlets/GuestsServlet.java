package ru.sharanov.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ru.sharanov.dto.GuestDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.sharanov.services.GuestService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/guests/")
public class GuestsServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id =  req.getParameter("id");
        if (req.getPathInfo() != null){
            System.out.println(req.getPathInfo());

        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        resp.setStatus(200);
        GuestService guestService = new GuestService();
        List<GuestDto> guests;
        try {
            guests = guestService.getAllGuests();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObjectMapper objectMapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        PrintWriter out = resp.getWriter();
        out.write(objectMapper.writeValueAsString(guests)+ "\n"+req.getRequestURI());
        out.flush();
    }
}
