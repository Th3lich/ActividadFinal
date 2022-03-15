package com.nttdata.actividadfinal.components;


public interface CustomValidator {
	void validate(Object target, boolean creating) throws ValidationException;
}
