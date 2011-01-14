package rested.editors.rules;

import java.util.Random;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

/**
 * Rules to highlight ReST sections.
 * They are tricky because there are not clear start end sequences for them. So
 * standard multiline rules cannot be applied... 
 *
 * //TODO handle resume flag correctly
 */
public class SectionRule implements IPredicateRule {

    /** The token to be returned on success */
    protected IToken fToken;
    protected char[] validDecorators = "= - ` : ' \" ~ ^ _ * + # < >".toCharArray();

    public SectionRule(IToken token) {
        fToken = token;
    }

    @Override
    public IToken getSuccessToken() {
        return fToken;
    }

    @Override
    public IToken evaluate(ICharacterScanner scanner) {
        return doEvaluate(scanner, false);
    }

    @Override
    public IToken evaluate(ICharacterScanner scanner, boolean resume) {
        return doEvaluate(scanner, resume);
    }

    /**
     * Tries to match the following pattern: \n(===+\n)?.*\n[${validDecorators}]+\n
     * 
     * 
     */
    protected IToken doEvaluate(ICharacterScanner scanner, boolean resume) {
        int readCount = 0;
        int c;
        
        readCount = matchNewline(scanner);
        if (readCount > 0) {
            
            readCount += readDecoratorLine(scanner);
            
            while ((c = scanner.read()) != ICharacterScanner.EOF) {
                if (c == '\r' || c == '\n') {
                    scanner.unread();
                    readCount += matchNewline(scanner);
                    break;
                }
                readCount++;
            }

            if (c != ICharacterScanner.EOF && readCount > 3 && readDecoratorLine(scanner) > 0) {
                return fToken;
            }
        }

        unread(scanner, readCount);
        return Token.UNDEFINED;
    }

    /**
     * Reads a line of made of +3 decorator characters
     * @param scanner
     * @return
     */
    protected int readDecoratorLine(ICharacterScanner scanner) {
        int c;
        int readCount = 0;

        while (isDecorator(c = scanner.read())) {
            readCount++; // consume the line of decorators
        }
        readCount++;

        if ((c == '\n' || c == '\r') && (readCount > 3)) {
            scanner.unread();
            return readCount + matchNewline(scanner) - 1;
        } else {
            unread(scanner, readCount);
            return 0;
        }
    }

    /**
     * Returns <code>true</code> if the character is a valid decorator. 
     */
    protected boolean isDecorator(int c) {
        for (int decorator : validDecorators) {
            if (decorator == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * Undeads N characters
     * @param scanner
     * @param count Number of characters to unread
     */
    protected void unread(ICharacterScanner scanner, int count) {
        for (int i = 0; i < count; i++) {
            scanner.unread();
        }
    }

    /**
     * @stolen from LdifRecordRule.java
     * 
     * Checks for new line "\n", "\r" or "\r\n".
     * 
     * @param scanner
     * @return
     */
    private int matchNewline(ICharacterScanner scanner) {

        int c = scanner.read();

        if (c == '\r') {
            c = scanner.read();
            if (c == '\n') {
                return 2;
            } else {
                scanner.unread();
                return 1;
            }
        } else if (c == '\n') {
            c = scanner.read();
            if (c == '\r') {
                return 2;
            } else {
                scanner.unread();
                return 1;
            }
        } else {
            scanner.unread();
            return 0;
        }
    }
}
    