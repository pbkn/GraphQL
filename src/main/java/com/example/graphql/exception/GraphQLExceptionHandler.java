package com.example.graphql.exception;

import java.util.Collections;
import java.util.List;

import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GraphQLExceptionHandler implements DataFetcherExceptionResolver {

	@Override
	public Mono<List<GraphQLError>> resolveException(Throwable exception, DataFetchingEnvironment environment) {
		log.info("[GraphQLExceptionHandler] Exception message: {}", exception.getMessage());

		if (exception instanceof EmployeeException) {
			log.info("[GraphQLExceptionHandler] handling EmployeeException");
			EmployeeException employeeException = (EmployeeException) exception;
			return Mono.just(Collections.singletonList(employeeException));
		}

		if (exception instanceof Exception) {
			log.info("[GraphQLExceptionHandler] handling Exception");
			GraphQLError graphQLError = new GraphQLError() {

				private static final long serialVersionUID = 1L;

				@Override
				public String getMessage() {
					return exception.getMessage();
				}

				@Override
				public List<SourceLocation> getLocations() {
					return List.of(environment.getField().getSourceLocation());
				}

				@Override
				public ErrorClassification getErrorType() {
					return ErrorType.INTERNAL_ERROR;
				}
			};
			return Mono.just(Collections.singletonList(graphQLError));
		}

		return Mono.empty();
	}

}
