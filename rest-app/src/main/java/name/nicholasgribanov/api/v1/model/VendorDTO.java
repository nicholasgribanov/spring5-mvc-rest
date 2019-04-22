package name.nicholasgribanov.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDTO {
    @ApiModelProperty(value = "Name of Vendor", required = true)
    private String nameVendor;
    @ApiModelProperty(value = "URL for Vendor")
    private String url;
}
