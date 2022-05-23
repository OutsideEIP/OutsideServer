package com.outside.database;
import javax.persistence.*;

@Entity
@Table(name = "Users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "email")
	private String email;

    @Column(name = "token")
	private String token;

    @Column(name = "accountType")
	private String accountType;

    public Users() {
	}

	public Users(String email, String token, String accountType) {
		this.email = email;
		this.token = token;
		this.accountType = accountType;
	}

	public long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", token=" + token + ", accountType=" + accountType + "]";
	}
}
