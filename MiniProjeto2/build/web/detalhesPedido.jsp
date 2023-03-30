<%@page import="java.util.List"%>
<%@page import="model.Pedido"%>
<%@page import="model.LanchePedido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalhe do pedido</title>
        <style>
            .hidden {
                display: none;
            }
        </style>
    </head>
    <body>
        
        <% List<LanchePedido> lanches = (List<LanchePedido>) request.getAttribute("lanches"); %>
        <% Pedido pedido = (Pedido) request.getAttribute("pedido"); %>
        <table border="1">
            <thead>
                <tr>
                    <th width="50">ID</th>
                    <th width="150">CPF</th>
                    <th width="150">Endereço</th>
                    <th width="150">Data</th>
                </tr>
            </thead>
            
            <tr>
                <td> <%=pedido.getId_pedido()%></td>
                <td> <%=pedido.getCpfcliente()%></td>
                <td> <%=pedido.getEndereco()%></td>
                <td> <%=pedido.getDate()%></td>
            </tr>
        </table>
        <br>
        <table border="1">
            <thead>
                <tr>
                    <th width="150">Lanche</th>
                    <th width="150">Quantidade</th>
                    <th width="150">Observação</th>
                </tr>
            </thead>
            <% for (LanchePedido lanche : lanches) { %>
                <tr>
                    <td> <%=lanche.getNomelanche()%></td>
                    <td> <%=lanche.getQuantidade()%></td>
                    <td> <%=lanche.getObservacao()%></td>
                </tr>
            <% } %>
        </table>
        <br>
        <table border="1">
            <thead>
                <tr>
                    <th width="150">Valor total</th>
                </tr>
            </thead>
            <tr>
                <td> <%=pedido.getValor_Total()%></td>
            </tr>
        </table>
    </body>
</html>
