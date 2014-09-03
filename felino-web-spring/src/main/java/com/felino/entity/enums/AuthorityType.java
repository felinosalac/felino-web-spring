package com.felino.entity.enums;

public enum AuthorityType {
	
	ROLE_ADMIN("System Admin"), 
	ROLE_MODERATOR("Moderator"),
	ROLE_USER("User");

	private String label;
	
	private AuthorityType(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static AuthorityType instanceOf(String label) {
		AuthorityType type=null;
		
		for (AuthorityType authorityType : values()) {
			if (authorityType.getLabel().equals(label)) {
				type = authorityType;
				break;
			}
		}
		
		return type;
	}
	
}
