package br.unitins.tp1.exception;

import java.util.Comparator;
import java.util.List;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ElementKind;
import jakarta.validation.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper extends AbstractProblemDetailsMapper
        implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        List<ProblemDetails.Violation> violations = exception.getConstraintViolations()
                .stream()
                .map(violation -> new ProblemDetails.Violation(
                        fieldName(violation),
                        violation.getMessage()))
                .sorted(Comparator.comparing(ProblemDetails.Violation::field)
                        .thenComparing(ProblemDetails.Violation::message))
                .toList();

        return problem(
                "urn:templatify:problem:validation-error",
                "Erro de validação",
                Response.Status.BAD_REQUEST.getStatusCode(),
                "Um ou mais campos da requisição são inválidos.",
                violations);
    }

    private String fieldName(ConstraintViolation<?> violation) {
        String fieldName = null;
        for (Path.Node node : violation.getPropertyPath()) {
            if (node.getKind() == ElementKind.PROPERTY) {
                fieldName = node.getName();
            }
        }
        return fieldName == null ? violation.getPropertyPath().toString() : fieldName;
    }
}
