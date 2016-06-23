package by.bsu.chef.action;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class DataLoader {
    private static final Logger LOG = Logger.getLogger(DataLoader.class);

    public static JSONObject readJsonFile(String fileName) {
        JSONParser parser;
        JSONObject obj = null;
        try (FileReader fileReader = new FileReader(fileName)) {

            LOG.info("Loading data from file");
            parser = new JSONParser();
            obj = (JSONObject) parser.parse(fileReader);
            LOG.info("Loading data from file completed");

        } catch (FileNotFoundException e) {
            LOG.error("FileNotFoundException", e);
        }  catch (ParseException e) {
            LOG.error("ParseException", e);
        } catch (ClassCastException e) {
            LOG.error("ClassCastException", e);
        } catch (IOException e) {
            LOG.error("IOException", e);
        }

        return obj;
    }
}
