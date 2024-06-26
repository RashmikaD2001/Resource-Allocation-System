/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Filter;


import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author RASHMIKA
 */
public class StringCharcter extends DocumentFilter{
    
     @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        // Only allow letters and spaces to be inserted
        if (string.matches("[a-zA-Z ]*")) {
            super.insertString(fb, offset, string, attr);
        }
    }
    
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        // Only allow letters and spaces to be replaced
        if (text.matches("[a-zA-Z ]*")) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
    
}
