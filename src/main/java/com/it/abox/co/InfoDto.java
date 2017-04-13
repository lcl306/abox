package com.it.abox.co;

import java.io.Serializable;

public class InfoDto implements Serializable{

	private static final long serialVersionUID = -995367009186660088L;

	private String ip;
	
	private String template;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}
	
	
}
