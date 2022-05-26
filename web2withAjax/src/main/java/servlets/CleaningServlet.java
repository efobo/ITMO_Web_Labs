package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/CleaningServlet")
public class CleaningServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        for(int i = 0; i < Integer.parseInt(getServletContext().getAttribute("requestsCount").toString()); ++i){
            String reqName = "request" + i;
            getServletContext().removeAttribute(reqName + "x");
            getServletContext().removeAttribute(reqName + "y");
            getServletContext().removeAttribute(reqName + "r");
            getServletContext().removeAttribute(reqName + "res");
            getServletContext().removeAttribute(reqName + "date");
        }
        getServletContext().removeAttribute("requestsCount");
        response.sendRedirect(request.getContextPath() + "/ControllerServlet");
    }


}
