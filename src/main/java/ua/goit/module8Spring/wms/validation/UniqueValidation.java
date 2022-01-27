package ua.goit.module8Spring.wms.validation;

import ua.goit.module8Spring.wms.dto.Dto;
import ua.goit.module8Spring.wms.models.Dao;
import ua.goit.module8Spring.wms.services.AbstractModelService;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
public @interface UniqueValidation {

    String message() default "This object is not unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    Class<? extends AbstractModelService<? extends Dao, ? extends Dto>> classService();

}
