package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GeoServiceImplTest {

    private final GeoServiceImpl geoService = new GeoServiceImpl();
    private static final String OTHER_IP = "23.55.13.22";

    @Test
    void byIp_Null_NotNullTest() {
        Assertions.assertNotNull(geoService.byIp(GeoServiceImpl.LOCALHOST));
        Assertions.assertNotNull(geoService.byIp(GeoServiceImpl.MOSCOW_IP));
        Assertions.assertNotNull(geoService.byIp(GeoServiceImpl.NEW_YORK_IP));
        Assertions.assertNull(geoService.byIp(OTHER_IP));
    }

    @ParameterizedTest
    @CsvSource({
            "172.0.32.11, RUSSIA",
            "96.44.183.149, USA",
            "172.12345, RUSSIA",
            "96.12345, USA"
    })
    void byIpTest(String ip, String country) {
        Assertions.assertEquals(country, geoService.byIp(ip).getCountry().toString());
    }

    @Test
    void testByIpShouldBeReturnLocationWithCountryNull() {
        Assertions.assertNull(geoService.byIp("127.0.0.1").getCountry());
    }

    @Test
    void byCoordinatesTestShouldBeReturnThrow() {
        Assertions.assertThrows(RuntimeException.class, () -> geoService.byCoordinates(12.56,145.8));
    }
}