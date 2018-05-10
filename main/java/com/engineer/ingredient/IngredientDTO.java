package com.engineer.ingredient;

public class IngredientDTO {

    private String ingredient;

    private String propositionName;

    private String propositionType;

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getPropositionName() {
        return propositionName;
    }

    public void setPropositionName(String propositionName) {
        this.propositionName = propositionName;
    }

    public String getPropositionType() {
        return propositionType;
    }

    public void setPropositionType(String propositionType) {
        this.propositionType = propositionType;
    }
}
