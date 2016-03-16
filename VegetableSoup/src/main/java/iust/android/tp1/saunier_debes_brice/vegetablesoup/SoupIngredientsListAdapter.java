/*
 * Soup.java
 * SAUNIER DEBES Brice
 * 24/02/16
 */

package iust.android.tp1.saunier_debes_brice.vegetablesoup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Adapter de la liste les ingrédients possible dans la soupe
 */
public class SoupIngredientsListAdapter
    extends BaseAdapter
    implements Serializable {

// ------------------------------ FIELDS ------------------------------

  /**
   * Le context de l’activité.
   */
  private Context  activityContext;
  /**
   * Les différents ingrédients possibles.
   */
  private String[] ingredients;
  /**
   * Les quantités de chaque ingrédient
   */
  private String[] quantities;

// --------------------------- CONSTRUCTORS ---------------------------

  /**
   * Instancie un nouvel adapter. Les noms des différents ingrédients sont en fonctions de la langue
   * selectionnée par l’utilisateur
   *
   * @param activityContext Le context de l’activité
   */
  public SoupIngredientsListAdapter(Context activityContext) {
    this.activityContext = activityContext;
    this.ingredients = activityContext.getResources().getStringArray(R.array.soupIngredients);
    this.quantities =
        activityContext.getResources().getStringArray(R.array.soupIngredientsQuantities);
  }

  /**
   * Instantiates a new Soup ingredients list adapter.
   *
   * @param activityContext Le context de l’activité
   * @param quantities      Les quantités
   */
  public SoupIngredientsListAdapter(Context activityContext, String[] quantities) {
    this.activityContext = activityContext;
    this.ingredients = activityContext.getResources().getStringArray(R.array.soupIngredients);
    this.quantities = quantities.clone();
  }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface Adapter ---------------------

  @Override
  public int getCount() {
    return ingredients.length;
  }

  @Override
  public Object getItem(int position) {
    return new SoupIngredient(ingredients[position], quantities[position]);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    //Prépare le layout inflater en fonction de l’activité
    LayoutInflater inflater = LayoutInflater.from(activityContext);
    //Désérialise la vue (XML) pour en faire un objet
    convertView = inflater.inflate(R.layout.soup_ingredients, null);

    TextView tw_ingredients = (TextView) convertView.findViewById(R.id.label_ingredients);
    tw_ingredients.setText(this.ingredients[position]);


    //Retourne la vue adaptée dynamiquement
    return convertView;
  }

// -------------------------- OTHER METHODS --------------------------

  /**
   * Décremente la quantitée d’un ingrédient
   *
   * @param position l’index de l’ingrédient dans la liste
   */
  public void decrementQuantity(int position) {
    if (Integer.valueOf(quantities[position]) > 0)
      quantities[position] = (Integer.valueOf(quantities[position]) - 1) + "";
  }

  /**
   * Get quantitées
   *
   * @return Un tableau contenant les différentes quantités
   */
  public String[] getQuantities() {
    return quantities;
  }

  /**
   * Incrémente la quantitée d’un ingrédient
   *
   * @param position l’index de l’ingrédient dans la liste
   */
  public void incrementQuantity(int position) {
    quantities[position] = (Integer.valueOf(quantities[position]) + 1) + "";
  }
}
