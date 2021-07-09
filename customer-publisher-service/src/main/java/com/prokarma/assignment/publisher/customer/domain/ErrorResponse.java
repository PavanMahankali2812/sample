package com.prokarma.assignment.publisher.customer.domain;

import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ErrorResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen",
        date = "2020-11-04T15:33:35.041Z")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    @JsonProperty("status")
    private String status = null;

    @JsonProperty("message")
    private String message = null;

    @JsonProperty("error_type")
    private String errorType = null;

    public ErrorResponse status(String status) {
        this.status = status;
        return this;
    }

    /**
     * Get status
     * 
     * @return status
     **/
    @NotNull
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ErrorResponse message(String message) {
        this.message = message;
        return this;
    }

    /**
     * Get message
     * 
     * @return message
     **/
    @NotNull
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorResponse errorType(String errorType) {
        this.errorType = errorType;
        return this;
    }

    /**
     * Get errorType
     * 
     * @return errorType
     **/
    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ErrorResponse errorResponse = (ErrorResponse) o;
        return Objects.equals(this.status, errorResponse.status)
                && Objects.equals(this.message, errorResponse.message)
                && Objects.equals(this.errorType, errorResponse.errorType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message, errorType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ErrorResponse {\n");

        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    message: ").append(toIndentedString(message)).append("\n");
        sb.append("    errorType: ").append(toIndentedString(errorType)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first
     * line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

