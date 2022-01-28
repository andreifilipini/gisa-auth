package com.gisa.gisaauth.model;

import com.gisa.gisacore.util.CipherUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String login;

	@NotNull
	private String password;

	@OneToMany(mappedBy = "user")
	private Set<Role> roles;

	public User(String name, String login, String password) {
		super();
		this.name = name;
		this.login = login;
		updatePassword(password);
	}

	public void updatePassword(String password) {
		this.password = CipherUtil.encrypt(password);
	}

	public void addRole(Role role) {
		if (role == null) {
			return;
		}
		if (this.roles == null) {
			this.roles = new HashSet<>();
		}
		this.roles.add(role);
	}
}
