package ua.goit.module8Spring.wms.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import ua.goit.module8Spring.wms.dto.Dto;
import ua.goit.module8Spring.wms.services.AbstractModelService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<UniqueValidation, Dto> {

    private AbstractModelService<?, ?> modelService;

    @Autowired
    private ApplicationContext context;

    @Override
    public void initialize(UniqueValidation constraintAnnotation) {
        modelService = context.getBean(constraintAnnotation.classService());
    }

    @Override
    public boolean isValid(Dto value, ConstraintValidatorContext context) {
        return !modelService.isExist(value);
    }
}
