package model;

import java.sql.Date;

public class Pedido {
    private int id_pedido;
    private float valor_Total;
    private String cpfcliente;
    private String endereco;
    private Date date;

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCpfcliente() {
        return cpfcliente;
    }

    public void setCpfcliente(String cpfcliente) {
        this.cpfcliente = cpfcliente;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public float getValor_Total() {
        return valor_Total;
    }

    public void setValor_Total(float valor_Total) {
        this.valor_Total = valor_Total;
    }
}
