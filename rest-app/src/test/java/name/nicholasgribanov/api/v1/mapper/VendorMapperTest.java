package name.nicholasgribanov.api.v1.mapper;

import name.nicholasgribanov.api.v1.model.VendorDTO;
import name.nicholasgribanov.domain.Vendor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VendorMapperTest {

    private final String NAME = "SEVER";
    private final String URL = "/api/v1/1";
    VendorMapper mapper = VendorMapper.INSTANCE;;


    @Test
    public void vendorToVendorDto() {
        Vendor vendor = new Vendor();
        vendor.setNameVendor(NAME);
        vendor.setUrl(URL);

        VendorDTO vendorDTO = mapper.vendorToVendorDto(vendor);

        assertEquals(NAME, vendorDTO.getNameVendor());
        assertEquals(URL, vendorDTO.getUrl());
    }

    @Test
    public void vendorDtoToVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setNameVendor(NAME);
        vendorDTO.setUrl(URL);

        Vendor vendor = mapper.vendorDtoToVendor(vendorDTO);

        assertEquals(NAME, vendor.getNameVendor());
        assertEquals(URL, vendor.getUrl());
    }
}