package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String x = request.getParameter("x_value"),
                y = request.getParameter("y_value"),
                radius = request.getParameter("radius");
        request.setAttribute("fromController", true);
        if(x == null || y == null || radius == null){
            if(request.getParameter("clearHistory") != null){
                getServletContext().getRequestDispatcher("/CleaningServlet").forward(request, response);
            } else{
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else{
            getServletContext().getRequestDispatcher("/AreaCheckServlet").forward(request, response);
        }



    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("fromController", true);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }


    private boolean validate(double x, double y, double r) {
        boolean is_valid = true;
        if (x < -2 || x > 2) {
            is_valid = false;
        } else if (y < -5 || y > 5) {
            is_valid = false;
        } else if (r < 1 || r > 5) {
            is_valid = false;
        }
        return is_valid;
    }
}
