$(document).ready(function() {
    setInterval(function() {
        $.ajax({
            url: 'ServletMultiplo',
            type: 'post',
            data: { "botao": 'consultarPedidos' },
            success: function(response) {
                $('body').html(response);
            },
            error: function(error) {
                console.log('Erro ao atualizar a lista de pedidos:', error);
            }
        });
    }, 5000);
});