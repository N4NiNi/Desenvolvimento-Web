<%@page import="java.util.List"%>
<%@page import="model.Pedido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
        <script src='ajaxhandler.js'></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar pedidos</title>
    </head>
    <body>
        <% List<Pedido> pedidos = (List<Pedido>) request.getAttribute("listaPedidos"); %>
        <table id="tabela_pedidos" border="1">
            <thead>
                <tr>
                    <th width="50">ID</th>
                    <th width="150">CPF</th>
                    <th width="150">Valor total</th>
                    <th width="150">Data</th>
                </tr>
            </thead>
            
            <% for (Pedido pedido : pedidos) { %>
                <tr>
                    <td> <%=pedido.getId_pedido()%></td>
                    <td> <%=pedido.getCpfcliente()%></td>
                    <td> <%=pedido.getValor_Total()%></td>
                    <td> <%=pedido.getDate()%></td>
                    <td>
                        <form action="ServletMultiplo" method="POST">
                            <input type="hidden" name="id_pedido" value="<%=pedido.getId_pedido()%>">
                            <button type="submit" name="botao" value="detalhesPedido">
                                Detalhes
                            </button>
                        </form>
                    </td>
                </tr>
            <% } %>
        </table>
    </body>
</html>
