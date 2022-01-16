package ua.goit.module8Spring.wms.validation;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.module8Spring.wms.dto.Dto;
import ua.goit.module8Spring.wms.models.Model;
import ua.goit.module8Spring.wms.services.AbstractModelService;

import javax.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.UUID;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
public @interface UniqueValidation {

    String message() default "This object is not unique";

    Class<? extends AbstractModelService<? extends Model, ? extends Dto>> classService();

}
