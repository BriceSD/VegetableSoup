package iust.android.tp1.saunier_debes_brice.vegetablesoup;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity
    extends Activity {

// ------------------------------ FIELDS ------------------------------

  SoupIngredientsListAdapter adapter;

// -------------------------- OTHER METHODS --------------------------

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final Spinner  ingredientsList       = (Spinner) findViewById(R.id.spinner_list_ingredients);
    final TextView soupRecipeIngredients = (TextView) findViewById(R.id.soup_recipe_ingredients);


    adapter = new SoupIngredientsListAdapter(this);
    ingredientsList.setAdapter(adapter);

    Button addVegetable    = (Button) findViewById(R.id.addVegetable);
    Button removeVegetable = (Button) findViewById(R.id.removeVegetable);

    addVegetable.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final int selectedItemPosition = ingredientsList.getSelectedItemPosition();

        adapter.incrementQuantity(selectedItemPosition);

        soupRecipeIngredients.setText(makeRecipeIngredientsString());
      }
    });

    removeVegetable.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        adapter.decrementQuantity(ingredientsList.getSelectedItemPosition());
        soupRecipeIngredients.setText(makeRecipeIngredientsString());
      }
    });



  }

  private String makeRecipeIngredientsString() {
    String str = "";

    for (int i = 0; i < adapter.getCount(); i++) {
      SoupIngredient soupIngredient = (SoupIngredient) adapter.getItem(i);
      if (Integer.valueOf(soupIngredient.getQuantity()) > 0) {
        if (str.isEmpty())
          str = getString(R.string.add) + " " + soupIngredient.getQuantity() + " " + soupIngredient
              .getIngredient();
        else
          str += ", " + soupIngredient.getQuantity() + " " + soupIngredient.getIngredient();
      }
    }

    return str;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);

    this.adapter = (SoupIngredientsListAdapter) savedInstanceState.getSerializable("SoupIngredientsListAdapter");

    final TextView soupRecipeIngredients = (TextView) findViewById(R.id.soup_recipe_ingredients);
    soupRecipeIngredients.setText(makeRecipeIngredientsString());
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);

    outState.putSerializable("SoupIngredientsListAdapter", this.adapter);
  }
}
