package com.ashraf.androidunittestingexample;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ValidationCheckUnitTest {

    //email validation check unit test
    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertTrue(ValidationCheck.isValidEmail("name@email.com"));
    }
    @Test
    public void emailValidator_FalseEmailSimple_ReturnsTrue() {
        assertFalse(ValidationCheck.isValidEmail("name.com"));
    }
    @Test
    public void emailValidator_FalseEmailSimple2_ReturnsTrue() {
        assertFalse(ValidationCheck.isValidEmail("name@com"));
    }

    ///password validation check unit test
    //correct password
    @Test
    public void passwordValidator_CorrectPasswordSimple_ReturnTrue(){
        assertTrue(ValidationCheck.isValidPassword("Boss789#"));
    }
    //without upper case latter
    @Test
    public void passwordValidator_IncorrectPasswordSimple_ReturnTrue(){
        assertFalse(ValidationCheck.isValidPassword("boss789#"));
    }

    //without digit
    @Test
    public void passwordValidator_IncorrectPasswordSimple2_ReturnTrue(){
        assertFalse(ValidationCheck.isValidPassword("Boss####"));
    }
    //without specific charecter
    @Test
    public void passwordValidator_IncorrectPasswordSimple3_ReturnTrue(){
        assertFalse(ValidationCheck.isValidPassword("Boss12312"));
    }

    //minimum length check
    @Test
    public void passwordValidator_IncorrectPasswordSimple4_ReturnTrue(){
        assertFalse(ValidationCheck.isValidPassword("bo7#"));
    }

    //maximum length check
    @Test
    public void passwordValidator_IncorrectPasswordSimple5_ReturnTrue(){
        assertFalse(ValidationCheck.isValidPassword("bo7#********************************************************"));
    }
}
