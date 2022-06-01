package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {

    @ParameterizedTest
    @CsvSource({
            "172.65.34.128, RUSSIA, Добро пожаловать",
            "96.65.34.128, USA, Welcome"
    })
    public void sendTest(String ip, String country, String str) {
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ip)).thenReturn(new Location(null, Country.valueOf(country), null, 0));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.valueOf(country))).thenReturn(str);
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> map = new HashMap();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        Assertions.assertEquals(str, messageSender.send(map));
    }
}