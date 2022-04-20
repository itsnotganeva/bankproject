package by.ganevich.validator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class CustomValidator<T> {

    public boolean validateDto (T dto) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<T>> validate = validator.validate(dto);

        if (validate.size() > 0) {
            for (ConstraintViolation<T> violation : validate) {
                System.out.println(violation.getMessage());
            }
            validatorFactory.close();
            return false;
        } else {
            return true;
        }
    }

    public List<String> validateCommand (T command) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<T>> validate = validator.validate(command);
        List<String> message = new ArrayList<>();

        if (validate.size() > 0) {
            for (ConstraintViolation<T> violation : validate) {
                message.add(violation.getMessage());
            }
            validatorFactory.close();
        }
        return message;
    }
}
