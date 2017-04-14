package com.it.abox.config;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/context.properties")
@ConfigurationProperties(prefix="web",ignoreUnknownFields=false)
public class Context {
	
	@NotBlank
	private String port;
	
	@NotNull
	private Db db;
	
	public static class Db{
		
		@NotBlank
		private String url;
		
		@NotBlank
		private String username;
		
		@NotBlank
		private String password;
		
		@NotBlank
		private Integer maxcon;
		
		@NotBlank
		private Integer mincon;
		
		@NotBlank
		private Integer initcon;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Integer getMaxcon() {
			return maxcon;
		}

		public void setMaxcon(Integer maxcon) {
			this.maxcon = maxcon;
		}

		public Integer getMincon() {
			return mincon;
		}

		public void setMincon(Integer mincon) {
			this.mincon = mincon;
		}

		public Integer getInitcon() {
			return initcon;
		}

		public void setInitcon(Integer initcon) {
			this.initcon = initcon;
		}
		
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Db getDb() {
		return db;
	}

	public void setDb(Db db) {
		this.db = db;
	}

}
