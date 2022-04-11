package ua.goit.module8Spring.wms.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ua.goit.module8Spring.wms.dto.Dto;
import ua.goit.module8Spring.wms.dto.ProducerDto;
import ua.goit.module8Spring.wms.models.Producer;
import ua.goit.module8Spring.wms.models.Product;
import ua.goit.module8Spring.wms.repositories.ProducerRepository;
import ua.goit.module8Spring.wms.repositories.ProductRepository;

import java.util.List;
import java.util.UUID;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class ProducerService extends AbstractModelService<Producer, ProducerDto> {

    ProductRepository productRepository;

    @Override
    public void delete(UUID id) {
        List<Product> products = productRepository.findByProducer_Id(id);
        products.forEach(product -> {
            product.setProducer(null);
            productRepository.save(product);
        });
        repository.deleteById(id);
    }

    @Override
    public boolean isExist(Dto dto) {
        if (dto.getId() == null) {
            return ((ProducerRepository) repository).existsByNameIgnoreCase(((ProducerDto) dto).getName());
        } else {
            return ((ProducerRepository) repository).existsByNameIgnoreCaseAndIdIsNot(((ProducerDto) dto).getName(), dto.getId());
        }
    }
}
