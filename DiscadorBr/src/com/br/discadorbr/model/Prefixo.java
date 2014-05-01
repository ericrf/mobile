package com.br.discadorbr.model;

public class Prefixo {
	private int id;
	private String label;
	private int numero;
	private String descricao;
	private int isDefault;

	public Prefixo(String label, int numero, String descricao){
		this.label = label;
		this.numero = numero;
		this.descricao = descricao;
	}
	public Prefixo(int id, String label, int numero, String descricao, int isDefault){
		this.id = id;
		this.label = label;
		this.numero = numero;
		this.descricao = descricao;
		this.isDefault = isDefault;
	}
	public Prefixo(String label, int numero){
		this.label = label;
		this.numero = numero;
	}
	public Prefixo() {
		this(null,0,null);
	}
	public Prefixo(int numero) {
		this.numero = numero;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(int isSelected) {
		this.isDefault = isSelected;
	}


}
