package name.nicholasgribanov.services;

import name.nicholasgribanov.api.v1.mapper.VendorMapper;
import name.nicholasgribanov.api.v1.model.VendorDTO;
import name.nicholasgribanov.controllers.v1.VendorController;
import name.nicholasgribanov.domain.Vendor;
import name.nicholasgribanov.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private VendorRepository repository;
    private final VendorMapper mapper;

    public VendorServiceImpl(VendorRepository repository, VendorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return repository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = new VendorDTO();
                    vendorDTO.setNameVendor(vendor.getNameVendor());
                    vendorDTO.setUrl(getVendorUrl(vendor.getId()));

                    return vendorDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public VendorDTO findVendorById(Long id) {
        return repository.findById(id)
                .map(vendor -> {
                    VendorDTO vendorDTO = new VendorDTO();
                    vendorDTO.setNameVendor(vendor.getNameVendor());
                    vendorDTO.setUrl(getVendorUrl(vendor.getId()));

                    return vendorDTO;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return saveVendorDto(mapper.vendorDtoToVendor(vendorDTO));
    }


    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendor = mapper.vendorDtoToVendor(vendorDTO);
        vendor.setId(id);

        return saveVendorDto(vendor);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return repository.findById(id)
                .map(vendor -> {
                    if (vendorDTO.getNameVendor() != null) {
                        vendor.setNameVendor(vendorDTO.getNameVendor());
                    }

                    VendorDTO vendorDTO1 = mapper.vendorToVendorDto(vendor);

                    vendorDTO1.setUrl(getVendorUrl(id));

                    return vendorDTO1;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        repository.deleteById(id);

    }

    private String getVendorUrl(Long id) {
        return VendorController.BASE_URL + "/" + id;
    }

    private VendorDTO saveVendorDto(Vendor vendor) {
        Vendor savedVendor = repository.save(vendor);
        VendorDTO returnedVendorDto = mapper.vendorToVendorDto(savedVendor);
        returnedVendorDto.setUrl(getVendorUrl(savedVendor.getId()));

        return returnedVendorDto;
    }
}
