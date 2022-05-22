<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>2 Лаба</title>
    <link rel="stylesheet" href="res/css/buttons.css">
    <link rel="stylesheet" href="res/css/style.css">
</head>


<body>
<table width="100%">
    <tr class="heading" align="center">
        <th align="center" colspan="2">Ершова Анна, гр. P3232<br>Вариант 32871</th>
    </tr>
    <tr><td><br><br><br><br><br><br></td></tr>
    <tr>
        <td width="50%" align="center">
            <form method="get" action="${pageContext.request.contextPath}/ControllerServlet" onsubmit="addXToRequest()">
                <table class="_form">
                    <tr>
                        <th align="center">Форма ввода данных<br><br></th>
                    </tr>
                    <tr>
                        <td>
                            Координата X
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table id="xTable">
                                <tr>
                                    <td align="center"><input type="button" name="x_value" value="-2"></td>
                                    <td align="center"><input type="button" name="x_value" value="-1.5"></td>
                                    <td align="center"><input type="button" name="x_value" value="-1"></td>
                                </tr>
                                <tr>
                                    <td align="center"><input type="button" name="x_value" value="-0.5"></td>
                                    <td align="center"><input type="button" name="x_value"value="0" style="background-color: #6F60C1"></td>
                                    <td align="center"><input type="button" name="x_value" value="0.5"></td>
                                </tr>
                                <tr>
                                    <td align="center"><input type="button" name="x_value" value="1"></td>
                                    <td align="center"><input type="button" name="x_value" value="1.5"></td>
                                    <td align="center"><input type="button" name="x_value" value="2"></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="y_value"><br>Координата Y </label><input type="text" oninput="validate(this)" required id="y_value" name="y_value" size="12px" maxlength="8" placeholder="от -5 до 3" data-rule="number">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <br>
                            Радиус R
                                <br>
                                <input type="radio" name="radius" value="1" checked="checked">1
                                <input type="radio" name="radius" value="1.5">1.5
                                <input type="radio" name="radius" value="2">2
                                <input type="radio" name="radius" value="2.5">2.5
                                <input type="radio" name="radius" value="3">3
                            <br>
                        </td>
                    </tr>
                    <tr>
                        <td class="submit-row" align="center">
                            <br>
                            <input type="submit" value="OK">
                        </td>
                    </tr>
                </table>
            </form>
        </td>
        <td align="center" width="100%" id="svg_wrapper">
            <svg class='svg_axis' width="300" height="300">
                <line x1="0" y1="150" x2="300" y2="150" stroke="#003"></line>
                <line x1="150" y1="0" x2="150" y2="300" stroke="#003"></line>
                <polygon points="300,150 295,145 295,155" fill="#003" stroke="#000"></polygon>
                <polygon points="150,0 145,5 155,5" fill="#003" stroke="#000"></polygon>
                <line x1="270" y1="155" x2="270" y2="145" stroke="#000"></line>
                <text x="265" y="140" class="axis-text" id="radius-text-right">R</text>
                <line x1="210" y1="155" x2="210" y2="145" stroke="#000"></line>
                <text x="200" y="140" class="axis-text" id="radius-text-right-half">R/2</text>
                <line x1="90" y1="155" x2="90" y2="145" stroke="#000"></line>
                <text x="75" y="140" class="axis-text" id="radius-text-left-half">-R/2</text>
                <line x1="30" y1="155" x2="30" y2="145" stroke="#000"></line>
                <text x="23" y="140" class="axis-text" id="radius-text-left">-R</text>
                <line x1="145" y1="30" x2="155" y2="30" stroke="#000"></line>
                <text x="157" y="35" class="axis-text" id="radius-text-top">R</text>
                <line x1="145" y1="90" x2="155" y2="90" stroke="#000"></line>
                <text x="157" y="95" class="axis-text" id="radius-text-top-half">R/2</text>
                <line x1="145" y1="210" x2="155" y2="210" stroke="#000"></line>
                <text x="157" y="215" class="axis-text" id="radius-text-bottom-half">-R/2</text>
                <line x1="145" y1="270" x2="155" y2="270" stroke="#000"></line>
                <text x="157" y="275" class="axis-text" id="radius-text-bottom">-R</text>


                <polygon points="150,150 150,30 270,150" fill="#00f" stroke="#003" fill-opacity="0.5"></polygon>
                <rect height="120" width="120" x="30" y="30" fill-opacity="0.5" fill="#a0f" stroke="#003"></rect>
                <path stroke="#003" d="M 270,150 L 150,150 L 150,270 A 130,130 0 0 1 30,150" fill-opacity="0.5"></path>
                <%--<%
                    if(application.getAttribute("requestsCount") != null){
                        for(int i = 0; i < Integer.parseInt(application.getAttribute("requestsCount").toString()); ++i){
                            double x, y, radius;
                            try {
                                x = Double.parseDouble(application.getAttribute("request" + i + "x").toString());
                                y = Double.parseDouble(application.getAttribute("request" + i + "y").toString());
                                radius = Double.parseDouble(application.getAttribute("request" + i + "r").toString());
                            } catch (NumberFormatException e){
                                continue;
                            }

                            double newX = x / radius * 125 + 150,
                                    newY = 150 - y / radius * 125;

                            String fillColor = application.getAttribute("request" + i + "res").toString().equals("YES")
                                    ? "white" : "black";

                            out.println(String.format("<circle r='5' cx='%s' cy='%s' fill='%s' stroke='black'/>",
                                    Double.toString(newX).replace(",", "."), Double.toString(newY).replace(",", "."), fillColor));
                        }
                    }
                %>--%>
                <%
                    if(application.getAttribute("requestsCount") != null){
                        double x, y, radius;
                        int i = Integer.parseInt(application.getAttribute("requestsCount").toString())-1;
                            x = Double.parseDouble(application.getAttribute("request" + i + "x").toString());
                            y = Double.parseDouble(application.getAttribute("request" + i + "y").toString());
                            radius = Double.parseDouble(application.getAttribute("request" + i + "r").toString());

                        double newX = x / radius * 125 + 150,
                                newY = 150 - y / radius * 125;

                        String fillColor = application.getAttribute("request" + i + "res").toString().equals("YES")
                                ? "white" : "black";

                        out.println(String.format("<circle r='5' cx='%s' cy='%s' fill='%s' stroke='black'/>",
                                Double.toString(newX).replace(",", "."), Double.toString(newY).replace(",", "."), fillColor));
                    }
                %>
            </svg>
        </td>
    </tr>
</table>

<script type="text/javascript">
    let x = 0;
    var form = document.getElementsByTagName("form")[0];
    //svg.addEventListener('mousedown', event => shoot(svg, event));

    function validate(input) {
        input.value = input.value.replace(/[^0-9,.-]/g, '').replace(/,/, ".");
        if (isNaN(input.value) && input.value !== "-")
            input.value = "";

        if (input.value > 3)
            input.value = 3;

        if (input.value < -5) {
            input.value = -5;
        }

        if (input.value.length > 8) {
            input.value = input.value.slice(0, -1);
        }
    }

    var xButtons = document.querySelectorAll("#xTable input");
    xButtons.forEach(click);

    function click(element) {
        element.onclick = function () {
            x = this.value;
            xButtons.forEach(function (element) {
                element.style.backgroundColor = "white";
            });
            this.style.backgroundColor = "#6F60C1"
        }
    }

    function addXToRequest() {
        var xElement = document.createElement("input");
        xElement.type = "text";
        xElement.name = "x_value";
        xElement.value = x;
        xElement.hidden = true;
        form.appendChild(xElement);
        return true;
    }

    function getMousePosition(svg, event) {
        var rect = svg.getBoundingClientRect();
        return {
            x: event.clientX - rect.left,
            y: event.clientY - rect.top
        };
    }


    var svg = document.getElementsByTagName("svg")[0];
    svg.addEventListener("click", function (ev) {
        /*var pos = getMousePosition(svg, ev);
        var radius = document.querySelector('input[name="radius"]:checked').value;
        var y = (150 - pos.y) / 125 * radius;
        var x = (pos.x - 150) / 125 * radius;
        let str = "?y_value=" + y + "&radius=" + radius + "&x_value=" + x;
        let req = new XMLHttpRequest();
        req.open("GET", window.location.protocol+"//" + window.location.hostname + ":" + window.location.port + "/web2_war_exploded/ControllerServlet" + str, true);
        req.send();*/
        var pos = getMousePosition(svg, ev);

        var radius = document.querySelector('input[name="radius"]:checked').value;
        x = (pos.x - 150) / 125 * radius;
        document.getElementById("y_value").value = (150 - pos.y) / 125 * radius;
        document.querySelector("input[type='submit']").click();
    })


</script>
</body>
</html>