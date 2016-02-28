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
  SoupIngredientsListAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final Spinner  sw1                   = (Spinner) findViewById(R.id.spinner_list_ingredients);
    final TextView soupRecipeIngredients = (TextView) findViewById(R.id.soupeRecipeIngredients);


    adapter = new SoupIngredientsListAdapter(this);
    sw1.setAdapter(adapter);

    Button addVegetable    = (Button) findViewById(R.id.addVegetable);
    Button removeVegetable = (Button) findViewById(R.id.removeVegetable);

    addVegetable.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final int selectedItemPosition = sw1.getSelectedItemPosition();

        adapter.incrementQuantity(selectedItemPosition);

        soupRecipeIngredients.setText(makeRecipeIngredientsString());
      }
    });

    removeVegetable.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        adapter.decrementQuantity(sw1.getSelectedItemPosition());
        soupRecipeIngredients.setText(makeRecipeIngredientsString());
      }
    });



  }

  private String makeRecipeIngredientsString() {
    String str ="";

    for (int i = 0; i < adapter.getCount(); i++) {
      SoupIngredient soupIngredient = (SoupIngredient) adapter.getItem(i);
      if (Integer.valueOf(soupIngredient.getQuantity()) > 0) {
        if (str.isEmpty())
          str = getString(R.string.add) + " " + soupIngredient.getQuantity() + " " + soupIngredient.getIngredient();
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
}
