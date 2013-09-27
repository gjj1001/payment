package com.casit.bean.product;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ProductType implements Serializable {
	
	private static final long serialVersionUID = -1740220822009765290L;
	private Integer typeId;
	private String name;
	private String note;
	private Boolean visible=true;
	private Set<ProductType> children = new HashSet<ProductType>();
	private ProductType parent;
	
	public ProductType() {}

	public ProductType(Integer typeId) {
		this.typeId = typeId;
	}

	@OneToMany(cascade={CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy="parent") 	
	public Set<ProductType> getChildren() {
		return children;
	}

	public void setChildren(Set<ProductType> children) {
		this.children = children;
	}
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="parentid")
	public ProductType getParent() {
		return parent;
	}

	public void setParent(ProductType parent) {
		this.parent = parent;
	}

	@Column(length=36, nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(length=200)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	@Column(nullable=false)
	public boolean isVisable() {
		return visible;
	}

	public void setVisable(boolean visable) {
		this.visible = visable;
	}

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
	
}
