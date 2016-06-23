package by.bsu.internetprovider.ajax.command;

import by.bsu.internetprovider.ajax.command.impl.*;
import by.bsu.internetprovider.ajax.util.JSONUtil;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;

/**
 * Class RequestHelper ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class RequestHelper {
    /** Field instance  */
    private static RequestHelper instance = null;

    /** Field commands  */
    HashMap<String,Command> commands = new HashMap<String,Command>();


    /**
     * Constructor RequestHelper creates a new RequestHelper instance.
     */
    private RequestHelper() {
        commands.put("subscribe", new SubscribeCommand());
        commands.put("review", new AddReviewCommand());
        commands.put("addTariff", new AddNewTariffCommand());
        commands.put("editTariff", new EditTariffCommand());
        commands.put("getTariffs", new FindAllTariffsCommand());
        commands.put("findSelectedTariff", new FindSelectedTariffCommand());
        commands.put("transferToArchive", new TransferToArchiveCommand());
        commands.put("replenishBalance", new ReplenishBalanceCommand());
        commands.put("showBalance", new ShowBalanceCommand());
        commands.put("deleteReview", new DeleteReviewCommand());
        commands.put("updateReviews", new UpdateReviewsCommand());
    }

    /**
     * Method getCommand ...
     *
     * @param requestData of type String
     * @return Command
     * @throws IOException when
     */
    public Command getCommand(String requestData) throws IOException {
        JSONObject json = null;
        try {
            json = JSONUtil.stringToJson(requestData);
        } catch (ParseException e) {
            throw new IOException();
        }
        String action = JSONUtil.getCommand(json);
        Command command = commands.get(action);
        if (command == null) {
            command = new NoCommand();
        }
        return command;
    }

    /**
     * Method getInstance returns the instance of this RequestHelper object.
     *
     * Field instance
     *
     * @return the instance (type RequestHelper) of this RequestHelper object.
     */
    public static RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
        }
        return instance;
    }
}