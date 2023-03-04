$(document).ready(function() {
    setInterval(function() {
        $.ajax({
            url: 'ServletMultiplo', // Substitua pelo endereço do arquivo que atualiza a lista de pedidos
            type: 'POST',
            data: { botao: 'consultar' },
            success: function(response) {
                $('body').html(response);
            },
            error: function(error) {
                console.log('Erro ao atualizar a lista de pedidos:', error);
            }
        });
    }, 5000); // Intervalo de atualização em milissegundos (5 segundos neste exemplo)
});