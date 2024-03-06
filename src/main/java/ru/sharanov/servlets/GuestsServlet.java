package ru.sharanov.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import ru.sharanov.dto.GuestDto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.sharanov.services.GuestService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/guests/*")
@Slf4j
public class GuestsServlet extends HttpServlet {

    GuestService guestService;
    private final String BAD_ADDRESS = "invalid path";
    private final String USER_NOT_FOUND = "user not found";
    ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    @Override
    public void init() {
        guestService = new GuestService();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        if (req.getPathInfo() == null || req.getPathInfo().trim().equals("/")) {
            List<GuestDto> guests = guestService.getAllGuests();
            resp.setStatus(200);
            out.write(objectMapper.writeValueAsString(guests));
            out.flush();
            return;
        }
        String[] params = req.getPathInfo().split("/");
        if (params.length > 2) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, BAD_ADDRESS);
            return;
        }
        String id = params[1];
        if (!id.matches("\\d")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, BAD_ADDRESS);
            return;
        }
        try {
            GuestDto guest = guestService.getGuestDtoById(Integer.parseInt(id));
            resp.setStatus(200);
            out.write(objectMapper.writeValueAsString(guest));
            out.flush();
        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, USER_NOT_FOUND);
            log.error(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        GuestDto guestDto = objectMapper.readValue(requestBody, GuestDto.class);
        guestService.saveGuest(guestDto);
        resp.setStatus(201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getPathInfo() == null || !req.getPathInfo().matches("\\d+")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, BAD_ADDRESS);
            return;
        }
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        GuestDto guestDto = objectMapper.readValue(requestBody, GuestDto.class);
        try {
            guestService.updateGuest(guestDto, Integer.parseInt(req.getPathInfo()));
        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, USER_NOT_FOUND);
            log.error(e.getMessage());
        }
        resp.setStatus(201);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getPathInfo() == null || !req.getPathInfo().matches("\\d+")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, BAD_ADDRESS);
            return;
        }
        try {
            guestService.deleteGuestDtoById(Integer.parseInt(req.getPathInfo()));
        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, USER_NOT_FOUND);
            log.error(e.getMessage());
        }
    }
}
