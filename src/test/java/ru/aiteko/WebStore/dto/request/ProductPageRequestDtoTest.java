package ru.aiteko.WebStore.dto.request;

import org.junit.jupiter.api.Test;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.*;

class ProductPageRequestDtoTest {

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Test
    void testCreateProductPageRequestDto() {
        ProductPageRequestDto requestDto = new ProductPageRequestDto();
        requestDto.setPage(0);
        requestDto.setSize(5);

        assertTrue(validator.validate(requestDto).isEmpty());
    }

    @Test
    void testInvalidPageValue() {
        ProductPageRequestDto requestDto = new ProductPageRequestDto();
        requestDto.setPage(-1);
        requestDto.setSize(5);

        assertFalse(validator.validate(requestDto).isEmpty());
    }

    @Test
    void testInvalidSizeValue() {
        ProductPageRequestDto requestDto = new ProductPageRequestDto();
        requestDto.setPage(0);
        requestDto.setSize(15);

        assertFalse(validator.validate(requestDto).isEmpty());
    }
}
