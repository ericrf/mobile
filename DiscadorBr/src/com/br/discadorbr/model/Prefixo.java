package com.br.discadorbr.model;

public class Prefixo {
	private int id;
	private String label;
	private int numero;
	private String descricao;
	private boolean isDefault;

	public Prefixo(String label, int numero, String descricao){
		this.label = label;
		this.numero = numero;
		this.descricao = descricao;
	}
	public Prefixo(String label, int numero){
		this.label = label;
		this.numero = numero;
	}
	public Prefixo() {
		this(null,0,null);
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
	public boolean isDefault() {
		return isDefault;
	}
	public void setDefault(boolean isSelected) {
		this.isDefault = isSelected;
	}
	public void setIsDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	
	
}
