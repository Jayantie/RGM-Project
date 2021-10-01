package com.bludots.app.rgm.password.registration.repositories.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "ROLL_TABLE")
public class RollTable {

	

	@Id
	@TableGenerator(name = "table", table = "sequences_", pkColumnName = "PK_NAME", valueColumnName = "PK_VALUE", initialValue = 1000000)
	@GeneratedValue(generator = "table", strategy = GenerationType.TABLE)
	@Column(name = "ROLL_ID")
	private Long rollId;

	@Column(name = "ROLL_TYPE")
	private String rollType;

	@ManyToMany(mappedBy = "roles")
	private Set<UserTable> users;

	public Long getRollId() {
		return rollId;
	}

	public void setRollId(Long rollId) {
		this.rollId = rollId;
	}

	public String getRollType() {
		return rollType;
	}

	public void setRollType(String rollType) {
		this.rollType = rollType;
	}

	public Set<UserTable> getUsers() {
		return users;
	}

	public void setUsers(Set<UserTable> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rollId == null) ? 0 : rollId.hashCode());
		result = prime * result + ((rollType == null) ? 0 : rollType.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RollTable other = (RollTable) obj;
		if (rollId == null) {
			if (other.rollId != null)
				return false;
		} else if (!rollId.equals(other.rollId))
			return false;
		if (rollType == null) {
			if (other.rollType != null)
				return false;
		} else if (!rollType.equals(other.rollType))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}

	
}