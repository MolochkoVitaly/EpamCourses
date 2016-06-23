package by.bsu.office.main;

import by.bsu.office.service.ShopService;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;



public class ShopManager {
    private static final Logger LOG = Logger.getLogger(ShopManager.class);

    static {
        String pathToFileConfig = "resources\\log4j.xml";
        new DOMConfigurator().doConfigure(pathToFileConfig, LogManager.getLoggerRepository());
    }
    public static void main(String[] args) {
        ShopService shopService = ShopService.getInstance();
        shopService.work();
    }
}
