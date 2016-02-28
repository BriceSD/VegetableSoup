/*
 * SoupIngredients.java
 * SAUNIER DEBES Brice
 * 24/02/16
 */

package iust.android.tp1.saunier_debes_brice.vegetablesoup;

public class SoupIngredient {

// ------------------------------ FIELDS ------------------------------

  private String ingredient;
  private String quantity;

// --------------------------- CONSTRUCTORS ---------------------------

  public SoupIngredient(String ingredient) {
    this.ingredient = ingredient;
    this.quantity = "0";
  }
  public SoupIngredient(String ingredient, String quantity) {
    this.ingredient = ingredient;
    this.quantity = quantity;
  }

// -------------------------- OTHER METHODS --------------------------

  public String getIngredient() {
    return ingredient;
  }

  public String getQuantity() {
    return quantity;
  }
}
