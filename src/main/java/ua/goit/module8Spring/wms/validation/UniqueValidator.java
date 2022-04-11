package ua.goit.module8Spring.wms.validation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.context.ApplicationContext;
import ua.goit.module8Spring.wms.dto.Dto;
import ua.goit.module8Spring.wms.models.Dao;
import ua.goit.module8Spring.wms.services.AbstractModelService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UniqueValidator implements ConstraintValidator<UniqueValidation, Dto> {

    @NonFinal
    private AbstractModelService<? extends Dao, ? extends Dto> modelService;

    ApplicationContext context;

    @Override
    public void initialize(UniqueValidation constraintAnnotation) {
        modelService = context.getBean(constraintAnnotation.classService());
    }

    @Override
    public boolean isValid(Dto value, ConstraintValidatorContext cont) {
        return !modelService.isExist(value);
    }
}
