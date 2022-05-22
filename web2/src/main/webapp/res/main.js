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

/*function shoot(svg, event){
    var radius = document.querySelector('input[name="radius"]:checked').value;
    /!*let x = event.offsetX;
    let y = event.offsetY;*!/
    var pos = getMousePosition(svg, ev);
    x = (pos.x - 150) / 125 * radius;
    y = (150 - pos.y) / 125 * radius;
    let str = "y_value=";
    str += y;
    str += "&radius=";
    str += radius;
    str += "&x_value=";
    str += x;
    //event.preventDefault();
    submit(str);
}*/
var svg = document.getElementsByTagName("svg")[0];
svg.addEventListener("click", function (ev) {
   /* pos = getMousePosition(svg, ev);
    radius = document.querySelector('input[name="radius"]:checked').value;
    y = (150 - pos.y) / 125 * radius;
    x = (pos.x - 150) / 125 * radius;
    let str = "y_value=" + y + "&radius=" + radius + "&x_value" + x;
    submit(str);*/
    var pos = getMousePosition(svg, ev);

    var radius = document.querySelector('input[name="radius"]:checked').value;
    x = (pos.x - 150) / 125 * radius;
    document.getElementById("y_value").value = (150 - pos.y) / 125 * radius;
    document.querySelector("input[type='submit']").click();
})

/*function submit(str){
    $.ajax({
        url: '/WebLab2_war_exploded/filter',
        type: 'GET',
        data: str,
        dataType: 'json',
        success: function(data){

        },
        error: function(data){

        }
    });*/
/*function submit(str){
    const url ="/ControllerServlet";
    const params = str;
    request.open("GET", url, params);
    request.addEventListener("readystatechange", () => {

        if(request.readyState === 4 && request.status === 200) {
            console.log(request.responseText);
        }
    });

    request.send(params);

}*/
/* $.ajax({
     url: 'web2_war_exploded/ControllerServlet',
     type: 'GET',
     data: str,
     dataType: 'json',
     success: function(data){
         addRow(data[0][0], data[0][1], data[0][2], data[0][3], data[0][4], data[0][5]);
         drawPoint(data[0][0], data[0][1],data[0][3]);
     },
     error: function(data){
         alert("error");
     }
 })*/