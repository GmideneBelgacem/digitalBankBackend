package com.digitalBank.backend.digitalBank.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String email;
	@OneToMany(mappedBy = "customer")
	//@JsonProperty(access = Access.WRITE_ONLY)
	private List<BankAccount> banckAccountList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<BankAccount> getBanckAccountList() {
		return banckAccountList;
	}

	public void setBanckAccountList(List<BankAccount> banckAccountList) {
		this.banckAccountList = banckAccountList;
	}

	public Customer(Long id, String nom, String email, List<BankAccount> banckAccountList) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.banckAccountList = banckAccountList;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

}
