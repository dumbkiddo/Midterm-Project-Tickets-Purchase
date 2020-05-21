package kz.iitu.midterm.validator;

import kz.iitu.midterm.entity.Movie;
import kz.iitu.midterm.form.MovieForm;
import kz.iitu.midterm.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MovieFormValidator implements Validator {

    @Autowired
    private MovieRepo movieRepo;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == MovieForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        MovieForm movieForm = (MovieForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "NotEmpty.movieForm.code");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.movieForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.movieForm.price");

        String code = movieForm.getCode();
        if (code != null && code.length() > 0) {
            if (code.matches("\\s+")) {
                errors.rejectValue("code", "Pattern.movieForm.code");
            } else if (movieForm.isNewMovie()) {
                Movie movie = movieRepo.findMovie(code);
                if (movie != null) {
                    errors.rejectValue("code", "Duplicate.movieForm.code");
                }
            }
        }
    }

}