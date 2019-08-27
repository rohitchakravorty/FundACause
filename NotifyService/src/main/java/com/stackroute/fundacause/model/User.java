package com.stackroute.fundacause.model;

import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class User {

	private String name;
	private String cause;
	private String email;
	private Long amount;
	private Map<String, Object> model;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", cause='" + cause + '\'' +
				", emailAddress='" + email + '\'' +
				", amount=" + amount +
				'}';
	}


}
