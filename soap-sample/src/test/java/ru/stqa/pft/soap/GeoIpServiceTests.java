package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by Golem on 05.05.2017.
 */
public class GeoIpServiceTests {

    @Test
    public void testMyIp(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("212.98.167.249");
        assertEquals(geoIP.getCountryCode(), "BLR");
    }

    @Test
    public void testInvalidIp(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("212.98.167.xxx");
        assertEquals(geoIP.getCountryCode(), "BLR");
    }
}
