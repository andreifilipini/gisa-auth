package com.gisa.gisaauth.model;

import com.gisa.gisacore.model.RoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	private User user;

	@NotNull
	@Enumerated(EnumType.STRING)
	private RoleEnum name;

	public Role(User user, RoleEnum name) {
		super();
		this.user = user;
		this.name = name;
	}
}
