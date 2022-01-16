package ua.goit.module8Spring.wms.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import ua.goit.module8Spring.wms.services.AbstractModelService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<UniqueValidation, String> {

    private AbstractModelService<?, ?> modelService;

    @Autowired
    private ApplicationContext context;

    @Override
    public void initialize(UniqueValidation constraintAnnotation) {
        modelService = context.getBean(constraintAnnotation.classService());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !modelService.isExist(value);
    }
}
