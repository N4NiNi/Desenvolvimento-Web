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
        <form action="ServletMultiplo" method="post">
            <label>Escolha um ou mais lanches:</label><br>
            <% List<Lanche> lanches = (List<Lanche>) request.getAttribute("lanches"); %>
            <% for (Lanche lanche : lanches) { %>
                <input type="checkbox" id="<%= lanche.getNomelanche() %>" name="lanches[]" value="<%= lanche.getNomelanche() %>">

                <label for="<%= lanche.getNomelanche() %>"><%= lanche.getNomelanche() %></label>
                <input type="number" id="qtd_<%= lanche.getNomelanche() %>" name="qtd_lanches[]" min="1" max="10" value="1" class="hidden">
                
                <label for="qtd_<%= lanche.getNomelanche() %>">R$ <%= lanche.getValor() %></label>
                <input class="hidden" type="text" name="observacoes" placeholder="Observacoes" maxlength="80">
                <br>
                
                <input type='hidden' id="<%= lanche.getNomelanche() %>" value=<%= lanche.getValor() %>>
            <% } %>

            <br>
            <div style='display: flex'>
                <label>CPF:</label>
                <input type="text" name="cpf" id="cpf" placeholder="Digite seu CPF" required><br>
                
            </div>
            <div style='display: flex'>
                <label>Endereco:</label>
                <input type="text" name="endereco" id="endereco" placeholder="Digite seu endereco completo" required><br>
                
            </div>
            <br>
            <div style='display: flex'>
                <label>Valor total:&nbsp</label>
                <span>R$&nbsp</span><input name="precototal" id="precototal" value="0.0" readonly>
            </div>  
       
            <input type="submit" value="registrar" name="botao">
        </form>

        <script>
            const lanches = document.querySelectorAll('input[type="checkbox"]');
            const qtd_lanches = document.querySelectorAll('input[type="number"]');
            const valores = document.querySelectorAll('input[type="hidden"]');
            const observacoes = document.querySelectorAll('input[type="text"]');
            const precototal = document.querySelector('#precototal');

            lanches.forEach((lanche, index) => {
                console.log(index);
                lanche.addEventListener('change', () => {
                    if (lanche.checked) {
                        qtd_lanches[index].classList.remove('hidden');
                        observacoes[index].classList.remove('hidden');
                    } else {
                        qtd_lanches[index].classList.add('hidden');
                        observacoes[index].classList.add('hidden');
                    }
                    atualizaValores();
                });
            });

            const atualizaValores = () => {
                let valor_total = 0;
                lanches.forEach((lanche, index) => {
                    if (lanche.checked) {
                        const valor = parseFloat(valores[index].value);
                        const qtd = parseFloat(qtd_lanches[index].value);
                        console.log(index, valor, qtd);
                        const total = valor * qtd;
                        console.log(total);
                        console.log("R$ " + total);
                        qtd_lanches[index].nextElementSibling.innerHTML = "R$ " + total.toFixed(1);
                        valor_total += total;
                    }
                });
                precototal.value = valor_total.toFixed(1);
            }

            qtd_lanches.forEach((qtd_lanche) => {
                qtd_lanche.addEventListener('change', atualizaValores);
            });

            atualizaValores();
        </script>
</body>
</html>
