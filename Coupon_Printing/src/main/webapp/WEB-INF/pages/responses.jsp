<!DOCTYPE html>
<html>
    <body onLoad = RegisterSSE()>
          <h1>Getting server updates</h1>
          <div id="result"></div>
          <script>
              function RegisterSSE()
              {
                  alert("wtf");
                  if(typeof(EventSource) != "undefined")
                  {
                      var source = new EventSource ("getPrinterResponse");
                      source.onmessage = function(event)
                      {
                          document.getElementById("result").innerHTML += event.data + "<br/>";
                      };
                  }
                  else
                  {
                      document.getElementById("result").innerHTML = "Sorry, your browser does not support   server-sent events...";
                  }
              }
          </script>
    </body>
    </html>