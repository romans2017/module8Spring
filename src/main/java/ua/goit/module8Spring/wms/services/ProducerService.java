package ua.goit.module8Spring.wms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.module8Spring.wms.dto.Dto;
import ua.goit.module8Spring.wms.dto.ProducerDto;
import ua.goit.module8Spring.wms.models.Producer;
import ua.goit.module8Spring.wms.repositories.ProducerRepository;

@Service
public class ProducerService extends AbstractModelService<Producer, ProducerDto> {

    @Autowired
    protected ProducerRepository repository;

    @Override
    public boolean isExist(Dto dto) {
        if (dto.getId() == null) {
            return repository.existsByNameIgnoreCase(((ProducerDto) dto).getName());
        } else {
            return repository.existsByNameIgnoreCaseAndIdIsNot(((ProducerDto) dto).getName(), dto.getId());
        }
    }
}
