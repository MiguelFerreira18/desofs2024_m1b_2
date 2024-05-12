package isep.ipp.pt.api.desofs.Model;


public enum Estado {
    REGISTADO("Registado"),
    ENTREGUE("Entregue"),
    CANCELADO("Cancelado");

    private final String estado;

    Estado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
