package br.com.orcamento.controle.model;

public enum TipoCategoria {

    Alimentacao(1, "Categoria de Alimentação como Restaurantes, bares, mercado");

    private Integer codigo;
    private String descricao;

    TipoCategoria(Integer id, String descricao) {
        this.codigo = id;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoCategoria toEnum(Integer codigo){
        if (codigo == null){
            return null;
        }
        for (TipoCategoria categoria : TipoCategoria.values()) {
            if (codigo.equals(categoria.getCodigo())){
                return categoria;
            }
        }

        throw new IllegalArgumentException("código de categoria inválid: " + codigo);
    }

}
