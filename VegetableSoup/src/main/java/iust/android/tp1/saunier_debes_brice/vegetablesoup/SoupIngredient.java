/*
 * SoupIngredients.java
 * SAUNIER DEBES Brice
 * 24/02/16
 */

package iust.android.tp1.saunier_debes_brice.vegetablesoup;

/**
 * La classe comprenant les différentes données d’un ingrédient
 */
public class SoupIngredient {

// ------------------------------ FIELDS ------------------------------

  /**
   * Le nom de l’ingrédient
   */
  private String ingredient;
  /**
   * La quantité de l’ingrédient
   */
  private String quantity;

// --------------------------- CONSTRUCTORS ---------------------------

  /**
   * Instancie un nouvel ingrédient de la soupe
   *
   * @param ingredient Le nom de l’ingrédient
   */
  public SoupIngredient(String ingredient) {
    this.ingredient = ingredient;
    this.quantity = "0";
  }

  /**
   * Instancie un nouvel ingrédient de la soupe
   *
   * @param ingredient Le nom de l’ingrédient
   * @param quantity   La quantité
   */
  public SoupIngredient(String ingredient, String quantity) {
    this.ingredient = ingredient;
    this.quantity = quantity;
  }

// -------------------------- OTHER METHODS --------------------------

  /**
   * Gets ingredient.
   *
   * @return l’ingredient
   */
  public String getIngredient() {
    return ingredient;
  }

  /**
   * Gets quantité de l’ingrédient
   *
   * @return La quantité de l’ingrédient
   */
  public String getQuantity() {
    return quantity;
  }
}
