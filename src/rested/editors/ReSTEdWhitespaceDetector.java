package rested.editors;

import org.eclipse.jface.text.rules.IWhitespaceDetector;

public class ReSTEdWhitespaceDetector implements IWhitespaceDetector {

    public boolean isWhitespace(char c) {
        return (c == ' ' || c == '\t' || c == '\n' || c == '\r');
    }
}
