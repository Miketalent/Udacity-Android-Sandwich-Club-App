package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    //JSON object names
    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String KNOWN_AS = "alsoKnownAs";
    private static final String ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) throws JSONException
    {
        JSONObject sandwichJson = new JSONObject(json);


        String mainName = sandwichJson.getJSONObject(NAME).getString(MAIN_NAME);
        List<String> knownAs = new ArrayList<>();

        JSONArray knownAsJsonArray = sandwichJson.getJSONObject(NAME).getJSONArray(KNOWN_AS);
        for (int i = 0; i < knownAsJsonArray.length(); i++) {
            knownAs.add(i, knownAsJsonArray.getString(i));
        }

        String placeOfOrigin = sandwichJson.getString(ORIGIN);
        String description = sandwichJson.getString(DESCRIPTION);
        String image = sandwichJson.getString(IMAGE);

        List<String> ingredients = new ArrayList<>();
        JSONArray ingredientsJsonArray = sandwichJson.getJSONArray(INGREDIENTS);
        for (int i = 0; i < ingredientsJsonArray.length(); i++) {
            knownAs.add(i, ingredientsJsonArray.getString(i));
        }


        return new Sandwich(mainName, knownAs, placeOfOrigin, description, image, ingredients);
    }
}
