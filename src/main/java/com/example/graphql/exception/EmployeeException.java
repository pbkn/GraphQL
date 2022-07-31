package com.example.graphql.exception;

import java.util.ArrayList;
import java.util.List;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class EmployeeException extends RuntimeException implements GraphQLError {

	private static final long serialVersionUID = 1L;

	public EmployeeException(String errorMessage) {
		super(errorMessage);
	}

	@Override
	public List<SourceLocation> getLocations() {
		return new ArrayList<>();
	}

	@Override
	public ErrorClassification getErrorType() {
		return ErrorType.DataFetchingException;
	}

}
