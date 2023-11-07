package com.delibot.userservice.web.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 *
 * <pre>
 *  Model class to represent a person;
 *  For the sake of simplicity all fields are kept same as {@link PersonEntity} object to be used as Entity class representing th table
 *  </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {
	private static final long serialVersionUID = 3570556679061270028L;

	private int personId;
	private String firstName;
	private String lastName;
	private String email;

}
