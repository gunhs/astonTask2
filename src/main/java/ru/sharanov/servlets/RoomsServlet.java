package ru.sharanov.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import ru.sharanov.dto.RoomDto;
import ru.sharanov.services.RoomService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/rooms/*")
@Slf4j
public class RoomsServlet extends HttpServlet {
    RoomService roomService;
    private final String BAD_ADDRESS = "invalid path";
    private final String ROOM_NOT_FOUND = "room not found";
    ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    @Override
    public void init() {
        roomService = new RoomService();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        if (req.getPathInfo() == null) {
            List<RoomDto> rooms = roomService.getAllRooms();
            resp.setStatus(200);
            out.write(objectMapper.writeValueAsString(rooms));
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
            RoomDto rooms = roomService.getRoomDtoById(Integer.parseInt(id));
            resp.setStatus(200);
            out.write(objectMapper.writeValueAsString(rooms));
            out.flush();
        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, ROOM_NOT_FOUND);
            log.error(e.getMessage());
        }
    }
}
