package br.com.gile.financas.modelo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(query = "select avg(m.valor) from Movimentacao m where m.conta = :pConta"
		+ " and m.tipoMovimentacao = :pTipo"
		+ " group by day(m.data), month(data), year(data)", name = "MediasPorDiaETipo")
public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private BigDecimal valor;
	
	//Quando é criado um ENUM, é assim que se passa para a JPA
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipoMovimentacao;
	
	//Quando se trata de data, pode ser passado DATE / TIME / TIMESTAMP
	//DATE pega somente a data
	//TIME pega somente a Hora
	//TIMESTAMP pega data e hora
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;
	private String descricao;
	
	//Varias Movimentações para uma conta
	@ManyToOne
	private Conta conta;
	
	@ManyToMany
	private List<Categoria> categoria;
	
	public List<Categoria> getCategorias() {
		return categoria;
	}
	public void setCategorias(List<Categoria> categoria) {
		this.categoria = categoria;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}
	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
