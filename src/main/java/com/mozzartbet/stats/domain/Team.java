package com.mozzartbet.stats.domain;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;

public class Team {

	@Id
	private BigInteger id;

	private String name;

	private String mozzartName;

	protected Team() {
	}

	public Team(String name, String mozzartName) {
		this.name = name;
		this.mozzartName = mozzartName;
	}

	public BigInteger getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getMozzartName() {
		return mozzartName;
	}

	public void setMozzartName(String mozzartName) {
		this.mozzartName = mozzartName;
	}

	@Override
	public String toString() {
		return String.format("Team [id=%s, name=%s, mozzartName=%s]", id, name, mozzartName);
	}


}