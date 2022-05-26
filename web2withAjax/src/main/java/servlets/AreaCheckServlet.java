package servlets;

import data.RequestDataList;
import data.RequestObj;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/AreaCheckServlet")
public class AreaCheckServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html; charset=windows-1251");
        PrintWriter out = response.getWriter();

        ServletContext context = getServletContext();

        if(context.getAttribute("requestsCount") == null)
            context.setAttribute("requestsCount", 0);

        out.println("<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <title>2 лаба</title>\n" +
                "    <link rel=\"stylesheet\" href=\"res/css/result.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<table class=\"resultTable\">\n" +
                "    <tr class=\"header\">\n" +
                "        <th>Координата X</th>\n" +
                "        <th>Координата Y</th>\n" +
                "        <th>Радиус</th>\n" +
                "        <th>Результат</th>\n" +
                "        <th>Текущее время</th>\n" +
                "    </tr>");


        for(int i = 0; i < Integer.parseInt(context.getAttribute("requestsCount").toString()); ++i){
            printTD(i, out);
        }

        String check = request.getParameter("check");

        if (check == null) {
            double xCoord = 0;
            double yCoord = 0;
            double radius = 0;
            boolean valid = true;

            try {
                xCoord = Double.parseDouble(request.getParameter("x_value"));
                yCoord = Double.parseDouble(request.getParameter("y_value"));
                radius = Double.parseDouble(request.getParameter("radius"));
            } catch (Exception e) {
                valid = false;
            }

            String reqName = "request" + context.getAttribute("requestsCount");

            valid = valid && radius >= 1 && radius <= 3;

            context.setAttribute(reqName + "x", valid ? xCoord : "Error");
            context.setAttribute(reqName + "y", valid ? yCoord : "Error");
            context.setAttribute(reqName + "r", valid ? radius : "Error");
            context.setAttribute(reqName + "res", valid ? isHit(xCoord, yCoord, radius) : "Error");
            context.setAttribute(reqName + "date", valid ? new SimpleDateFormat("M/d/y hh:mm:ss a").format(new Date()) : "Errors");


            printTD(Integer.parseInt(context.getAttribute("requestsCount").toString()), out);
            context.setAttribute("requestsCount", Integer.parseInt(context.getAttribute("requestsCount").toString()) + 1);

        }
        out.println("</table>\n" +
                "<table>\n" +
                "    <tr class=\"buttons\"><br><br>\n" +
                "        <td>\n" +
                "            <form method=\"get\" action = \"ControllerServlet\">\n" +
                "                <button name='back' value='back'>Назад</button>\n" +
                "            </form>\n" +
                "        </td>\n" +
                "        <td>\n" +
                "            <form method=\"get\" action = \"ControllerServlet\">\n" +
                "                <button name='clearHistory' value='clear''>Очистить сессионную историю</button>\n" +
                "            </form>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>");
        out.close();
    }

    private void printTD(int num, PrintWriter out){
        ServletContext context = getServletContext();
        out.printf("<tr>\n<td>%s</td>\n<td>%s</td>\n<td>%s</td>\n<td>%s</td>\n<td>%s</td>\n</tr>\n",
                context.getAttribute("request" + num + "x"),
                context.getAttribute("request" + num + "y"),
                context.getAttribute("request" + num + "r"),
                context.getAttribute("request" + num + "res"),
                context.getAttribute("request" + num + "date"));
    }

    private String isHit(double x, double y, double r){
        if (((x > 0) && (y > 0) && (x + y <= r))
                || ((x < 0) && (y > 0) && (y <= r) && (x >= -r))
                || ((x < 0) && (y < 0) && ((x * x) + (y * y) <= r * r))
                || ((x == 0) && (((y >= 0) && (y <= r)) || ((y <= 0) && (y >= -r))))
                || ((y == 0) && (((x >= 0) && (x <= r)) || ((x <= 0) && (x >= -r))))) return "YES";
        else return "NO";
    }

}