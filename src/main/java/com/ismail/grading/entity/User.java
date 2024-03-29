package com.ismail.grading.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
/*
 * 'user' is a reserved keyword in SQL, so we name our table users. Otherwise we
 * get a org.h2.jdbc.JdbcSQLSyntaxErrorException.
 */
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Username cannot be blank!")
	@NonNull
	@Column(nullable = false, unique = true)
	private String username;

	@NotBlank(message = "Password cannot be blank!")
	@NonNull
	@Column(nullable = false)
	private String password;

}