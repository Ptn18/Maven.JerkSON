package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ItemParser {
    public List<Item> parseItemList(String valueToParse) {
        String[] noP = valueToParse.split("##");
        List<Item> items = new ArrayList<>();
        for(String string: noP){
            try {
                items.add(parseSingleItem(string));
            } catch (ItemParseException e) {
//                e.printStackTrace();
            }
        }

        return items;
    }

    public Item parseSingleItem(String singleItem) throws ItemParseException {

        String lowerSingle = singleItem.toLowerCase().replace("##","");
        String[] keyAndValue = semiparse(lowerSingle);
        Map<String, String> items = new TreeMap<>();
        for (String string : keyAndValue) {
            String[] moreSplitting = string.split("[:%^*!@;]");
            if(moreSplitting.length < 2) {
                throw new ItemParseException();
            }
            items.put(moreSplitting[0], moreSplitting[1]);
        }

        return new Item(items.get("name"),Double.parseDouble(items.get("price")),items.get("type"),items.get("expiration"));
    }
    public String[] semiparse(String withSemi){
        return withSemi.split(";");
    }
}
