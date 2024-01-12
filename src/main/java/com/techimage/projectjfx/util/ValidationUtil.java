package com.techimage.projectjfx.util;

import com.techimage.projectjfx.annotations.TextValidation;
import com.techimage.projectjfx.exception.ValidationException;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {
    private final Class<?> aClass;
    private final Object instance;
    public ValidationUtil(Object instance) {
        this.instance = instance;
        this.aClass = instance.getClass();
    }
    private final HashMap<String, String> errors = new HashMap<>();
    public void textValidation () throws NoSuchFieldException, IllegalAccessException {
        String suffixFieldError = "Error";
        Field [] fields = this.aClass.getDeclaredFields();

        Arrays.stream(fields)
                .filter(field -> field.getAnnotation(TextValidation.class) != null)
                .forEach(field -> {
                    String error = "";
                    field.setAccessible(true);
                    TextValidation textValidation =  field.getAnnotation(TextValidation.class);
                    try {
                        String fieldValue = getFieldValue(field);
                        if(textValidation.required() || !fieldValue.isEmpty()) {
                            if(!this.testRegex(fieldValue, textValidation.regex())) {
                                error = error.concat(textValidation.message() + "\n");

                            }
                            if(fieldValue.length() < textValidation.minLength()) {
                                error = error.concat(
                                        String.format("Le champs %s doit contenir au moins %d caractères\n",
                                                textValidation.label(), textValidation.minLength())
                                );
                            }
                            if(fieldValue.length() > textValidation.maxLength()) {
                                error = error.concat(
                                        String.format("Le champs %s doit contenir au plus %d caractères\n",
                                                textValidation.label(), textValidation.maxLength())
                                );
                            }
                        }

                        if(!error.isEmpty()) {
                            this.errors.put(
                                    String.format("%s%s", field.getName(), suffixFieldError),
                                    error
                            );
                        }

                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
        if(!this.errors.isEmpty()) {
            this.setErrorMessage();
            throw new ValidationException();
        }
    }

    private String getFieldValue(Field field) throws IllegalAccessException {
        Object value = field.get(this.instance);
        String fieldValue;
        if(value.getClass().equals(ChoiceBox.class)) {
            ChoiceBox<String> fieldInstance = (ChoiceBox<String>) value;
            fieldValue = fieldInstance.getSelectionModel().getSelectedItem();
            if(fieldValue == null) {
                fieldValue = "";
            }
        }
        else {
            TextField fieldInstance = (TextField) value;
            fieldValue = fieldInstance.getText();
        }
        return fieldValue;
    }


    private  boolean testRegex(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    private void setErrorMessage () throws NoSuchFieldException, IllegalAccessException {
        Field [] fields = this.aClass.getDeclaredFields();
        Arrays.stream(fields)
                .filter(field -> field.getName().contains("Error"))
                .toList().forEach(field -> {
                    Label label;
                    try {
                        label = (Label) field.get(this.instance);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    label.setText("");
                });
        for(String key : this.errors.keySet()) {
            Field field = this.aClass.getField(key);
            field.setAccessible(true);
            Object value = field.get(this.instance);
            Label labelError = (Label) value;
            labelError.setText(this.errors.get(key));
        }
        this.errors.clear();
    }




}
