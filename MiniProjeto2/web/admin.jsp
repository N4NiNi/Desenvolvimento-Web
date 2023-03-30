<%-- 
    Document   : index
    Created on : 30 de mar. de 2023, 10:18:37
    Author     : Vinicius
--%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de pedidos</title>
</head>
<body>
    
    <%
        //allow access only if session exists
        String user = (String) session.getAttribute("user");
        String userName = null;
        String sessionID = null;
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
        for(Cookie cookie : cookies){
                if(cookie.getName().equals("user")) userName = cookie.getValue();
                if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
            }
        }

    %>
    
    <h3>Olá, <%=user %> seja bem-vindo.</h3>
    <p>Escolha o serviço desejado:</p>
    <form action="ServletAdmin" method="post">
        <ul>
            <li><button type="submit" name="botao" value="consultarPedidos">Consultar Pedidos</button></li>
        </ul>
    </form>
    
<form action="LogoutServletX" method="post">
<input type="submit" value="Logout" >
</form>
</body>
</html>
