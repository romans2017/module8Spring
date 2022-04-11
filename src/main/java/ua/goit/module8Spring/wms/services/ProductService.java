package ua.goit.module8Spring.wms.services;

import org.springframework.stereotype.Service;
import ua.goit.module8Spring.wms.dto.Dto;
import ua.goit.module8Spring.wms.dto.ProductDto;
import ua.goit.module8Spring.wms.models.Product;
import ua.goit.module8Spring.wms.repositories.ProductRepository;

import java.util.UUID;

@Service
public class ProductService extends AbstractModelService<Product, ProductDto> {

    @Override
    public void update(UUID id, ProductDto dto) {
        repository.findById(id)
                .map(product -> {
                    product.setProducer(null);
                    modelMapper.map(dto, product);
                    return product;
                }).ifPresent(product -> repository.save(product));
    }

    @Override
    public boolean isExist(Dto dto) {
        if (dto.getId() == null) {
            return ((ProductRepository) repository).existsByNameIgnoreCase(((ProductDto) dto).getName());
        } else {
            return ((ProductRepository) repository).existsByNameIgnoreCaseAndIdIsNot(((ProductDto) dto).getName(), dto.getId());
        }
    }
}
