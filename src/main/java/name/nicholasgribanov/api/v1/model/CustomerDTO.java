package name.nicholasgribanov.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    @ApiModelProperty(value = "First name there is", required = true)
    private String firstName;
    @ApiModelProperty(value = "Last name", required = true)
    private String lastName;

    @JsonProperty("customer_url")
    private String customerUrl;
}
