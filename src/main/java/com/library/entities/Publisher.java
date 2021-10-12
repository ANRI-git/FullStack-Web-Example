package com.library.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Publisher {
	@Id
	private String id;
	private String name;
	private Boolean registered;

	public Publisher() {
		super();
	}

	public Publisher(String id, String name, Boolean registered) {
		super();
		this.id = id;
		this.name = name;
		this.registered = registered;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getRegistered() {
		return registered;
	}

	public void setRegistered(Boolean registered) {
		this.registered = registered;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, registered);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Publisher other = (Publisher) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(registered, other.registered);
	}

	@Override
	public String toString() {
		return "Publisher [id=" + id + ", name=" + name + ", registered=" + registered + "]";
	}

}
