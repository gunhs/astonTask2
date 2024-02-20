package ru.sharanov.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = "/guests/*")
@Slf4j
public class GuestsServlet extends HttpServlet {

    GuestService guestService;
    private final String BAD_ADDRESS = "invalid path";


    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void init() {
        guestService = new GuestService();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String[] params = req.getPathInfo().split("/");
        ObjectMapper objectMapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        if (params.length > 2) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, BAD_ADDRESS);
        } else if (params.length > 0) {
            String id = params[1];
            if (!id.matches("\\d")) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, BAD_ADDRESS);
            } else {
                Optional<GuestDto> guest = null;
                try {
                    guest = guestService.getGuestDtoById(Integer.parseInt(id));
                } catch (SQLException e) {
                    log.error(e.getMessage());
                }
                resp.setStatus(200);
                out.write(objectMapper.writeValueAsString(guest));
                out.flush();
            }
        } else {
            List<GuestDto> guests = null;
            try {
                guests = guestService.getAllGuests();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            resp.setStatus(200);
            out.write(objectMapper.writeValueAsString(guests));
            out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        LocalDate firstDateOfStay = LocalDate.parse(req.getParameter("firstDateOfStay"), formatter);
        LocalDate lastDateOfStay = LocalDate.parse(req.getParameter("lastDateOfStay"), formatter);
        String passportNumber = req.getParameter("passportNumber");

        GuestDto guestDto = new GuestDto();
        guestDto.setName(name);
        guestDto.setSurname(surname);
        guestDto.setFirstDateOfStay(firstDateOfStay);
        guestDto.setLastDateOfStay(lastDateOfStay);
        guestDto.setPassportNumber(passportNumber);
        try {
            guestService.saveGuest(guestDto);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
//        resp.sendRedirect(req.getContextPath() + "/tasksmenu");
        resp.setStatus(201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            guestService.deleteGuestDtoById(Integer.parseInt(id));
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
}
