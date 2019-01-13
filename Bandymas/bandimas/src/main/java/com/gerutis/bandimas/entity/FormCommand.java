package com.gerutis.bandimas.entity;




public class FormCommand {

    String textField;

    String textareaField;

    String datetimeField;

    String colorField;

    boolean singleCheckboxField;

    String[] multiCheckboxSelectedValues;

    String radioButtonSelectedValue;
    String dropdownSelectedValue;

    public String[] getMultiCheckboxSelectedValues() {
        return multiCheckboxSelectedValues;
    }

    public void setMultiCheckboxSelectedValues(String[] multiCheckboxSelectedValues) {
        this.multiCheckboxSelectedValues = multiCheckboxSelectedValues;
    }
}
