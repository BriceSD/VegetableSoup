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

public class SoupIngredientsListAdapter
    extends BaseAdapter implements Serializable {

// ------------------------------ FIELDS ------------------------------

  private Context  activityContext;
  private String[] ingredients;
  private String[] quantities;

// --------------------------- CONSTRUCTORS ---------------------------

  public SoupIngredientsListAdapter(Context activityContext) {
    this.activityContext = activityContext;
    this.ingredients = activityContext.getResources().getStringArray(R.array.soupIngredients);
    this.quantities =
        activityContext.getResources().getStringArray(R.array.soupIngredientsQuantities);
  }

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
    LayoutInflater inflater = LayoutInflater.from(activityContext);
    convertView = inflater.inflate(R.layout.soup_ingredients, null);

    TextView tw_ingredients = (TextView) convertView.findViewById(R.id.label_ingredients);
    tw_ingredients.setText(this.ingredients[position]);


    return convertView;
  }

// -------------------------- OTHER METHODS --------------------------

  public void decrementQuantity(int position) {
    if (Integer.valueOf(quantities[position]) > 0)
      quantities[position] = (Integer.valueOf(quantities[position]) - 1) + "";
  }

  public void incrementQuantity(int position) {
    quantities[position] = (Integer.valueOf(quantities[position]) + 1) + "";
  }
}
