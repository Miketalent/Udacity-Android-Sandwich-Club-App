package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private ImageView mIngredientsIv;
    private TextView mIngredientsTv;
    private TextView mAlsoKnownAsTv;
    private TextView mDescriptionTv;
    private TextView mPlaceOfOrigin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mIngredientsIv = findViewById(R.id.image_iv);
        mIngredientsTv = findViewById(R.id.ingredients_tv);
        mAlsoKnownAsTv = findViewById(R.id.also_known_tv);
        mDescriptionTv = findViewById(R.id.description_tv);
        mPlaceOfOrigin = findViewById(R.id.origin_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];

        try {
            //parse information from the sandwich's json
            Sandwich sandwich = JsonUtils.parseSandwichJson(json);

            //populate information about current sandwich to UI
            populateUI(sandwich);
            Picasso.with(this)
                    .load(sandwich.getImage())
                    .into(mIngredientsIv);

            setTitle(sandwich.getMainName());
        } catch (JSONException e) {
                // Sandwich data unavailable
                closeOnError();
                return;
        }


    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    /**
     * set the corresponding information about the sandwich
     * to the UI
     *
     * @param sandwich  - sandwich info object
     */
    private void populateUI(Sandwich sandwich) {
        mPlaceOfOrigin.setText(sandwich.getPlaceOfOrigin());
        mDescriptionTv.setText(sandwich.getDescription());

        for (String word : sandwich.getAlsoKnownAs()) {
            mAlsoKnownAsTv.append(word);
        }

        for (String word : sandwich.getIngredients()) {
            mIngredientsTv.append(word);
        }

    }
}
