package iust.android.tp1.saunier_debes_brice.vegetablesoup;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;

/**
 * L’activité principale.
 */
public class MainActivity
    extends Activity
    implements Serializable {

// ------------------------------ FIELDS ------------------------------

  /**
   * L’adapteur des vues des ingrédients
   */
  SoupIngredientsListAdapter adapter;

// -------------------------- OTHER METHODS --------------------------

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final Spinner  ingredientsList       = (Spinner) findViewById(R.id.spinner_list_ingredients);
    final TextView soupRecipeIngredients = (TextView) findViewById(R.id.soup_recipe_ingredients);


    //Création de l’adapter et ajout de celui ci à l’activité courante
    adapter = new SoupIngredientsListAdapter(this);
    ingredientsList.setAdapter(adapter);

    Button addVegetable    = (Button) findViewById(R.id.addVegetable);
    Button removeVegetable = (Button) findViewById(R.id.removeVegetable);

    //Lors d’un clique sur le bouton "+", augmente la quantité de l’ingrédient selectionnée pour la
    //recette. Mise à jours du texte en prenant compte des changements
    addVegetable.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final int selectedItemPosition = ingredientsList.getSelectedItemPosition();

        adapter.incrementQuantity(selectedItemPosition);
        soupRecipeIngredients.setText(makeRecipeIngredientsString());
      }
    });

    //Lors d’un clique sur le bouton "-", diminue la quantité de l’ingrédient selectionnée pour la
    //recette. Mise à jours du texte en prenant compte des changements
    removeVegetable.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        adapter.decrementQuantity(ingredientsList.getSelectedItemPosition());
        soupRecipeIngredients.setText(makeRecipeIngredientsString());
      }
    });



  }

  /**
   * Fait le message comprenant les différents ingrédients avec la quantité adéquate pour la recette
   * crée par l’utilisateur
   *
   * @return La recette
   */
  private String makeRecipeIngredientsString() {
    String str = "";

    // pour chaque ingrédient
    for (int i = 0; i < adapter.getCount(); i++) {
      SoupIngredient soupIngredient = (SoupIngredient) adapter.getItem(i);
      // si il y en a au moins un dans la recette
      if (Integer.valueOf(soupIngredient.getQuantity()) > 0) {
        // si aucun autre ingrédient n’a été mis dans le message, on ajoute "Ajoutez"
        // puis l’ingrédient lui même
        if (str.isEmpty())
          str = getString(R.string.add) + " " + soupIngredient.getQuantity() + " " + soupIngredient
              .getIngredient();
        // sinon une virgule puis l’ingrédient lui même
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
  protected void onDestroy() {
    super.onDestroy();
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
  protected void onPause() {
    super.onPause();
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);

    //L’adapter (qui contient la liste des ingrédients et les quantités) est restoré.
    this.adapter = (SoupIngredientsListAdapter) savedInstanceState
        .getSerializable("SoupIngredientsListAdapter");

    TextView soupRecipeIngredients = (TextView) findViewById(R.id.soup_recipe_ingredients);
    soupRecipeIngredients.setText(makeRecipeIngredientsString());
  }

  @Override
  protected void onResume() {
    super.onResume();

    final Spinner  ingredientsList       = (Spinner) findViewById(R.id.spinner_list_ingredients);
    final TextView soupRecipeIngredients = (TextView) findViewById(R.id.soup_recipe_ingredients);

    //Lors de la reprise de l’activité la liste des noms des ingrédients est mise à jours au cas où
    //la langue a changée. La quantitée est la même que celle sauvegardée.
    adapter = new SoupIngredientsListAdapter(this, adapter.getQuantities());
    ingredientsList.setAdapter(adapter);

    //Le text est mis à jour.
    soupRecipeIngredients.setText(makeRecipeIngredientsString());
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);

    //L’adapter (qui contient la liste des ingrédients et les quantités) est sauvegardé.
    outState.putSerializable("SoupIngredientsListAdapter", this.adapter);
  }

  @Override
  protected void onStop() {
    super.onStop();
  }
}
