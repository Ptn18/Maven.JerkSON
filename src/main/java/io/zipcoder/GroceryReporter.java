package io.zipcoder;

import io.zipcoder.utils.FileReader;
import io.zipcoder.utils.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroceryReporter {
    private final String originalFileText;

    public GroceryReporter(String jerksonFileName) {
        this.originalFileText = FileReader.readFile(jerksonFileName);
    }


    @Override
    public String toString() {
        ItemParser parser = new ItemParser();
        List<Item> itemsList = parser.parseItemList(originalFileText);
        Map<String, List<Item>> itemsMap = createItemMap(itemsList);

        StringBuilder builder = new StringBuilder("name    " + itemsList);
        itemsMap.toString();
        System.out.println(builder);

        return builder.toString();
    }

    private Map<String, List<Item>> createItemMap(List<Item> itemsList) {
        Map<String, List<Item>> itemsMap = new HashMap<>();

        for (int i = 0; i < itemsList.size(); i++) {
            Item item = itemsList.get(i);
            List<Item> list = itemsMap.get(item.getName());
            if(list == null){
                list = new ArrayList<>();
            }
            list.add(item);
            itemsMap.put(item.getName(), list);
        }
        return itemsMap;
    }


}
