import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class shoppingList {
    private int productIndex;
    private int quantity;
    private String specialRequest;
    private JSONObject item;
    private JSONArray tempShoppingList = new JSONArray();
    private JSONObject shoppingListSum = new JSONObject();

    // constructor
    public shoppingList(int index, int quantity, String specialRequest) {
        this.productIndex = index;
        this.quantity = quantity;
        this.specialRequest = specialRequest;
    }

    // store quotation into json object
    public void storeShoppingListsJObject() {
        item = new JSONObject();
        item.put("ProductIndex", productIndex);
        item.put("Quantity", quantity);
        item.put("SpecialRequest", specialRequest);
    }

    //write into the JSON file
    public void writeJson() {
        // read the json file
        readJson();

        shoppingList item = new shoppingList(this.productIndex, this.quantity, this.specialRequest);

        item.storeShoppingListsJObject();

        tempShoppingList.add(item.getItem());

        shoppingListSum.put("ShoppingList", tempShoppingList);

        try {
            // Constructs a FileWriter given a file name, using the platform's default charset
            FileWriter file = new FileWriter("./src/shoppingList.json");
            file.write(shoppingListSum.toJSONString());
            file.flush();
            file.close();
        } catch ( IOException c) {
            c.printStackTrace();
        }
    }

    //read the file
    // read and return JSON file data
    public void readJson() {
        JSONParser parser = new JSONParser();

        try {
            Object item = parser.parse(new FileReader("./src/shoppingList.json"));
            JSONObject jsonObject = (JSONObject) item;

            tempShoppingList = (JSONArray) jsonObject.get("ShoppingList");
        }
        catch(FileNotFoundException e) {e.printStackTrace();}
        catch(IOException e) {e.printStackTrace();}
        catch(ParseException e) {e.printStackTrace();}
        catch(Exception e) {e.printStackTrace();}
    }

    // get quotation object
    public JSONObject getItem() {
        return item;
    }

    // get shopping list array
    public JSONArray getShoppingList() {
        return tempShoppingList;
    }
}
