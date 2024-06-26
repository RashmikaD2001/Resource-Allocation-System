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
public class StringFilter extends DocumentFilter{
    
    
    private static final String REGEX = "[a-zA-Z]*"; // Regular expression to match only string characters
    
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        String newStr = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
        if (newStr.matches(REGEX)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}
