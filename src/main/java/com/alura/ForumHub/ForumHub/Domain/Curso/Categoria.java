package com.alura.ForumHub.ForumHub.Domain.Curso;


public enum Categoria {
    PROGRAMACAO("Programação"),
    BANCO_DE_DADOS("Banco de Dados"),
    DESENVOLVIMENTO("Desenvolvimento"),
    DATA_SCIENCE("Data Science"),
    INTELIGENCIA_ARTIFICIAL("Inteligência Artificial"),
    ENGENHARIA("Engenharia"),
    REDES("Redes"),
    SEGURANCA("Segurança"),
    DESENVOLVIMENTO_MOBILE("Desenvolvimento Mobile"),
    DEVOPS("DevOps"),
    GERENCIAMENTO("Gerenciamento"),
    ANALISE("Análise"),
    ADMINISTRACAO("Administração"),
    DESIGN("Design"),
    TECNOLOGIA("Tecnologia");

    private String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

