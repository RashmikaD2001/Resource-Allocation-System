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
public class IntFilter extends DocumentFilter {
    
    private int maxLength;
    
    public IntFilter(int maxLength) {
        this.maxLength = maxLength;
    }
    
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (fb.getDocument().getLength() + string.length() <= maxLength && string.matches("\\d*")) {
            super.insertString(fb, offset, string, attr);
        }
    }
    
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        String newStr = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
        if (newStr.matches("\\d*") && newStr.length() <= maxLength) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
    
}
