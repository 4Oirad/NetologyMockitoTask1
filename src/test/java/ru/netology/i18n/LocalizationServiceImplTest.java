package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    private final LocalizationService lc = new LocalizationServiceImpl();

    @ParameterizedTest
    @CsvSource({
            "RUSSIA, Добро пожаловать",
            "GERMANY, Welcome",
            "USA, Welcome",
            "BRAZIL, Welcome"
    })
    void locale(String country, String expected) {
        Assertions.assertEquals(expected, lc.locale(Country.valueOf(country)));
    }
}