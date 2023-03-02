<%@page import="java.util.List"%>
<%@page import="model.Lanche"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Criar pedido</title>
        <style>
            .hidden {
                display: none;
            }
        </style>
    </head>
    <body>
        <form action="#" method="post">
            <label>Escolha um ou mais lanches:</label><br>
            <% List<Lanche> lanches = (List<Lanche>) request.getAttribute("lanches"); %>
            <% for (Lanche lanche : lanches) { %>
                <input type="checkbox" id="<%= lanche.getNomelanche() %>" name="lanches[]" value="<%= lanche.getValor() %>">
                <label for="<%= lanche.getNomelanche() %>"><%= lanche.getNomelanche() %></label>
                <input type="number" id="qtd_<%= lanche.getNomelanche() %>" name="qtd_lanches[]" min="1" max="10" value="1" class="hidden">
                <label for="qtd_<%= lanche.getNomelanche() %>">R$ <%= lanche.getValor() %></label> <br>
            <% } %>

            <br>
            <div style='display: flex'>
                <label>CPF:</label>
                <input type="text" name="cpf" id="cpf" placeholder="Digite seu CPF" required><br>
            </div>

            <br>
            <input type="submit" value="registrar" name="botao">
        </form>

        <script>
            const lanches = document.querySelectorAll('input[type="checkbox"]');
            const qtd_lanches = document.querySelectorAll('input[type="number"]');

            lanches.forEach((lanche, index) => {
                lanche.addEventListener('change', () => {
                    if (lanche.checked) {
                        qtd_lanches[index].classList.remove('hidden');
                    } else {
                        qtd_lanches[index].classList.add('hidden');
                    }
                });
            });

            const atualizaValores = () => {
                lanches.forEach((lanche, index) => {
                    if (lanche.checked) {
                        const valor = parseFloat(lanche.value);
                        const qtd = parseFloat(qtd_lanches[index].value);
                        console.log(index, valor, qtd);
                        const total = valor * qtd;
                        console.log(total);
                        console.log("R$ " + total);
                        qtd_lanches[index].nextElementSibling.innerHTML = "R$ " + total.toFixed(1);
                    }
                });
            }

            qtd_lanches.forEach((qtd_lanche) => {
                qtd_lanche.addEventListener('change', atualizaValores);
            });
        </script>
</body>
</html>
