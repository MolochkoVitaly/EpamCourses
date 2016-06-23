package by.bsu.chef.main;


import by.bsu.chef.action.DataLoader;
import by.bsu.chef.action.SideDishMaker;
import by.bsu.chef.action.SortByCalories;
import by.bsu.chef.entity.SideDish;
import by.bsu.chef.exception.IsNotCorrectValueException;
import by.bsu.chef.exception.UnknownIngredientException;
import by.bsu.chef.init.Initialize;
import by.bsu.chef.reporter.Reporter;
import by.bsu.chef.storage.IngredientsStorage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;


public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class);

    static {
        String pathToFileConfig = "src\\by\\bsu\\chef\\resources\\log4j.xml";
        new DOMConfigurator().doConfigure(pathToFileConfig, LogManager.getLoggerRepository());
    }

    public static void main(String[] args) {
        try {
            LOG.info("Start");
            String pathToFile = "src\\by\\bsu\\chef\\resources\\data.json";
            SideDish dish = new SideDish("Легкий");
            IngredientsStorage ingredientsStorage =
                            new IngredientsStorage(Initialize.getIngredients(DataLoader.readJsonFile(pathToFile)));
            SideDishMaker.maker(dish,ingredientsStorage.getIngredients());
            Reporter.printIngredients(ingredientsStorage.getIngredients());
            Reporter.printCalories(dish);
            Reporter.printIngredients(dish);
            SortByCalories.sort(dish);
            Reporter.printIngredients(dish);
            LOG.info("finish");
        }  catch (UnknownIngredientException e){
            LOG.error("UnknownException", e);
        } catch (IsNotCorrectValueException e) {
            LOG.error("IsNotCorrectValueException", e);
        }
    }
}
