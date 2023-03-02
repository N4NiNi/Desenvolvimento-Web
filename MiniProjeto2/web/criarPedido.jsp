<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
        <body>
	<form action="#" method="post">
		<label>Escolha um ou mais lanches:</label><br>
		<input type="checkbox" id="xburger" name="lanches[]" value="xburger">
		<label for="xburger">X-Burger</label>
		<input type="number" id="qtd_xburger" name="qtd_lanches[]" min="1" max="10" value="1" class="hidden"><br>

		<input type="checkbox" id="xsalada" name="lanches[]" value="xsalada">
		<label for="xsalada">X-Salada</label>
		<input type="number" id="qtd_xsalada" name="qtd_lanches[]" min="1" max="10" value="1" class="hidden"><br>

		<input type="checkbox" id="xbacon" name="lanches[]" value="xbacon">
		<label for="xbacon">X-Bacon</label>
		<input type="number" id="qtd_xbacon" name="qtd_lanches[]" min="1" max="10" value="1" class="hidden"><br>

		<input type="checkbox" id="xmilho" name="lanches[]" value="xmilho">
		<label for="xmilho">X-Milho</label>
		<input type="number" id="qtd_xmilho" name="qtd_lanches[]" min="1" max="10" value="1" class="hidden"><br>

		<input type="checkbox" id="xfrango" name="lanches[]" value="xfrango">
		<label for="xfrango">X-Frango</label>
		<input type="number" id="qtd_xfrango" name="qtd_lanches[]" min="1" max="10" value="1" class="hidden"><br>

		<input type="checkbox" id="xtudo" name="lanches[]" value="xtudo">
		<label for="xtudo">X-Tudo</label>
		<input type="number" id="qtd_xtudo" name="qtd_lanches[]" min="1" max="10" value="1" class="hidden"><br>

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
	</script>
</body>
    </body>
</html>
