
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script>
        function setWidValue(form) {
            var widValue = document.querySelector('input[name="wid"]').value;
            var hiddenInput = form.querySelector('input[name="wid"]');
            if (!hiddenInput) {
                hiddenInput = document.createElement('input');
                hiddenInput.type = 'hidden';
                hiddenInput.name = 'wid';
                form.appendChild(hiddenInput);
            }
            hiddenInput.value = widValue;
        }
    </script>

</head>
<body>
    <h1>worker.jsp</h1>

    <input type = "text" name = "wid">

    <br>
    <br>
    <br>

    <form action = "/login/worker/in" method = "post" onsubmit="setWidValue(this)">
        <button>IN</button>
    </form>

    <br>
    <br>
    <br>

    <form action = "/login/worker/out" method = "post" onsubmit="setWidValue(this)">
        <button>OUT</button>
    </form>



</body>
</html>
