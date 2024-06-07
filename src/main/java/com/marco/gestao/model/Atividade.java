package com.marco.gestao.model;
import jakarta.persistence.*;

@Entity
@Table(name = "atividade")
public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String atividade;
    @Column
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "idProjeto")
    private Projeto projeto;
    @Column
    private Long tamanho;
    @Column(nullable = false)
    private Boolean finalizada;
    @ManyToOne
    @JoinColumn(name = "idParticipante")
    private Participante participante;

    public Atividade(){}

    public Atividade(Long id, String atividade, String descricao, Projeto projeto, Long tamanho, Boolean finalizada, Participante participante) {
        this.id = id;
        this.atividade = atividade;
        this.descricao = descricao;
        this.projeto = projeto;
        this.tamanho = tamanho;
        this.finalizada = finalizada;
        this.participante = participante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Long getTamanho() {
        return tamanho;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
    }

    public Boolean getFinalizada() {
        return finalizada;
    }

    public void setFinalizada(Boolean finalizada) {
        this.finalizada = finalizada;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }
}
