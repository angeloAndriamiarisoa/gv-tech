package com.techimage.projectjfx.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TextValidation {
    String label() default "Ce champs";
    String regex();
    int minLength() default 0;
    int maxLength() default 1000;
    String message() default "La valeur du champs est incorrecte";
}
