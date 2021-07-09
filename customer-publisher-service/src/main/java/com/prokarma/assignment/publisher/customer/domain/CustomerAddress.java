package com.prokarma.assignment.publisher.customer.domain;

import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CustomerAddress
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen",
        date = "2020-11-04T15:33:35.041Z")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerAddress {
    @JsonProperty("addressLine1")
    private String addressLine1 = null;

    @JsonProperty("addressLine2")
    private String addressLine2 = null;

    @JsonProperty("street")
    private String street = null;

    @JsonProperty("postalCode")
    private String postalCode = null;

    public CustomerAddress addressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    /**
     * Get addressLine1
     * 
     * @return addressLine1
     **/
    @NotNull(message = "The field addressLine1 should not be null")
    @Pattern(regexp = "[A-Za-z0-9]{1,50}$",
            message = "The field addressLine1 must be a string with maximum length of 50.")
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public CustomerAddress addressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    /**
     * Get addressLine2
     * 
     * @return addressLine2
     **/


    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public CustomerAddress street(String street) {
        this.street = street;
        return this;
    }

    /**
     * Get street
     * 
     * @return street
     **/


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public CustomerAddress postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    /**
     * Get postalCode
     * 
     * @return postalCode
     **/
    @NotNull(message = "The field postalCode should not be null")
    @Pattern(regexp = "[0-9]{1,5}$",
            message = "The field postalCode must be a string with maximum length of 5.")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomerAddress customerAddress = (CustomerAddress) o;
        return Objects.equals(this.addressLine1, customerAddress.addressLine1)
                && Objects.equals(this.addressLine2, customerAddress.addressLine2)
                && Objects.equals(this.street, customerAddress.street)
                && Objects.equals(this.postalCode, customerAddress.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressLine1, addressLine2, street, postalCode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CustomerAddress {\n");

        sb.append("    addressLine1: ").append(toIndentedString(addressLine1)).append("\n");
        sb.append("    addressLine2: ").append(toIndentedString(addressLine2)).append("\n");
        sb.append("    street: ").append(toIndentedString(street)).append("\n");
        sb.append("    postalCode: ").append(toIndentedString(postalCode)).append("\n");
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

