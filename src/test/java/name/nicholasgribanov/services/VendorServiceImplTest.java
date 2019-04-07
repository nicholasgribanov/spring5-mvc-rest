package name.nicholasgribanov.services;

import name.nicholasgribanov.api.v1.mapper.VendorMapper;
import name.nicholasgribanov.api.v1.model.VendorDTO;
import name.nicholasgribanov.domain.Vendor;
import name.nicholasgribanov.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class VendorServiceImplTest {

    @Mock
    private VendorRepository repository;
    private VendorService vendorService;

    private final String NAME = "SEVER";
    private final String URL = "/api/v1/vendors/1";
    private final Long ID = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(repository, VendorMapper.INSTANCE);
    }

    @Test
    public void getAllVendors() {
        Vendor vendor = new Vendor();
        vendor.setNameVendor(NAME);
        vendor.setUrl(URL);
        Vendor vendor1 = new Vendor();
        vendor1.setNameVendor(NAME);
        vendor1.setUrl(URL);

        List<Vendor> vendors = Arrays.asList(vendor, vendor1);

        when(repository.findAll()).thenReturn(vendors);

        List<VendorDTO> vendorDTOS = vendorService.getAllVendors();

        assertEquals(vendorDTOS.size(), 2);
    }

    @Test
    public void findVendorById() {
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setNameVendor(NAME);
        vendor.setUrl(URL);
        Optional<Vendor> vendorOptional = Optional.of(vendor);

        when(repository.findById(anyLong())).thenReturn(vendorOptional);

        VendorDTO vendorDTO = vendorService.findVendorById(ID);

        assertEquals(vendorDTO.getNameVendor(), NAME);
        assertEquals(vendorDTO.getUrl(), URL);
    }

    @Test
    public void createNewVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setNameVendor(NAME);
        vendorDTO.setUrl(URL);

        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setNameVendor(vendorDTO.getNameVendor());
        vendor.setUrl(vendorDTO.getUrl());

        when(repository.save(any(Vendor.class))).thenReturn(vendor);

        VendorDTO savedDtos = vendorService.createNewVendor(vendorDTO);

        assertEquals(savedDtos.getNameVendor(), vendor.getNameVendor());
    }

    @Test
    public void saveVendorByDTO() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setNameVendor(NAME);
        vendorDTO.setUrl(URL);

        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setNameVendor(vendorDTO.getNameVendor());
        vendor.setUrl(vendorDTO.getUrl());

        when(repository.save(any(Vendor.class))).thenReturn(vendor);

        VendorDTO savedDtos = vendorService.saveVendorByDTO(1L, vendorDTO);

        assertEquals(savedDtos.getNameVendor(), vendor.getNameVendor());
    }

}