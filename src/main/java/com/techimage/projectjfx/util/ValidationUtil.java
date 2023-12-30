package com.techimage.projectjfx.util;

import com.techimage.projectjfx.annotations.TextValidation;
import com.techimage.projectjfx.exception.ValidationException;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {
    private Class<?> aClass;
    private Object instance;
    public ValidationUtil(Object instance) {
        this.instance = instance;
        this.aClass = instance.getClass();
    }
    private final HashMap<String, String> errors = new HashMap<>();

    public HashMap<String, String> getErrors () {
        return this.errors;
    }
    public void textValidation () throws NoSuchFieldException, IllegalAccessException {
        String suffixFieldError = "Error";
        String error = "";
        Field [] fields = this.aClass.getDeclaredFields();
        for (Field field: fields) {
            error = "";
           TextValidation textValidation =  field.getAnnotation(TextValidation.class);
           if(textValidation != null) {
               field.setAccessible(true);
               try {
                   Object value = field.get(this.instance);
                   TextField fieldInstance = (TextField) value;
                    String fieldValue = fieldInstance.getText();
                   if(!this.testRegex(fieldValue, textValidation.regex())) {
                       error = error.concat(textValidation.message() + "\n");
                   }
                   if(fieldValue.length() < textValidation.minLength()) {
                       error = error.concat(
                               String.format("`%s` doit contenir au moins %d caractères\n", textValidation.label(), textValidation.minLength()));
                   }
                   if(fieldValue.length() > textValidation.maxLength()) {
                       error = error.concat(
                               String.format("`%s` doit contenir au plus %d caractères\n", textValidation.label(), textValidation.maxLength()));
                   }
                   if(!error.isEmpty()) {
                       this.errors.put(String.format("%s%s", field.getName(), suffixFieldError), error);
                   }

               } catch (IllegalAccessException e) {
                   throw new RuntimeException(e);
               }
           }
        }

        if(!this.errors.isEmpty()) {
            this.setErrorMessage();
            throw new ValidationException();
        }
    }


    private  boolean testRegex(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    private void setErrorMessage () throws NoSuchFieldException, IllegalAccessException {
        Field [] fields = this.aClass.getDeclaredFields();
        for(Field field : fields) {
            if(field.getClass().equals(Label.class)) {
                Label label = (Label) field.get(this.instance);
                label.setText("");
            }
        }
        for(String key : this.errors.keySet()) {
            Field field = this.aClass.getField(key);
            field.setAccessible(true);
            Object value = field.get(this.instance);
            Label labelError = (Label) value;
            labelError.setText(this.errors.get(key));
        }
    }




}
