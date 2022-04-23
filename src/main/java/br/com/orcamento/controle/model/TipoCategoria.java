package br.com.orcamento.controle.model;

public enum TipoCategoria {

    OUTRAS(1,"Valor Default"),
    ALIMENTACAO(2, "Gastos com Alimentação como Restaurantes, bares, mercado"),
    SAUDE(3,"Gastos com Saude como farmacia, medico"),
    MORADIA(4,"Gatsos com Moradia como alugel, financiamento, juros"),
    TRANSPORTE(5,"Gastos com Trasnporte como onibus, combustivel, seguro"),
    EDUCACAO(6,"Gastos com Educação, como escola, faculdade, cursos "),
    LAZER(7,"Gastos com Lazer como festas, lanches e saidas"),
    IMPREVISTOS(8,"Gastos com Imprevistos como acidentes");

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
