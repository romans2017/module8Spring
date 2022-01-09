package ua.goit.module8Spring.wms.services.modelServices;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.module8Spring.wms.dto.Dto;
import ua.goit.module8Spring.wms.models.Model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

abstract public class AbstractModelService<M extends Model, D extends Dto> {

    private final Class<M> modelClass;
    private final Class<D> dtoClass;
    protected JpaRepository<M, UUID> repository;

    @Autowired
    protected ModelMapper modelMapper;

    @SuppressWarnings("unchecked")
    protected AbstractModelService() {
        Type[] params = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
        modelClass = (Class<M>) params[0];
        dtoClass = (Class<D>) params[1];
    }

    public List<D> getAll() {
        List<D> dtoList = new ArrayList<>();
        repository.findAll().forEach(item -> dtoList.add(modelMapper.map(item, dtoClass)));
        return dtoList;
    }

    public D get(UUID id) {
        D dto = null;
        try {
            dto = dtoClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return repository.findById(id).map(entity -> modelMapper.map(entity, dtoClass)).orElse(dto);
    }

    public void create(D dto) {
        M model = modelMapper.map(dto, modelClass);
        repository.save(model);
    }

    public void update(UUID id, D dto) {
        repository.findById(id)
                .map(user -> {
                    modelMapper.map(dto, user);
                    return user;
                }).ifPresent(user -> {
                    repository.save(user);
                });
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}