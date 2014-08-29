package com.felino.model;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Model {
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


}
