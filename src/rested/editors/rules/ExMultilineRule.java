package rested.editors.rules;

import javax.xml.stream.events.EndElement;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;

/**
 * Work as the MultilineRule but after the start secuence does not allow an space.
 * That way the start sequences can be used in the text without being part of a Exmultiline rule.
 * 
 * //TODO Find a better name... now is a very bad tribute to win32 api...
 */
public class ExMultilineRule extends MultiLineRule {

    private String sStart;
    private boolean startSeqRead = false;

    public ExMultilineRule(String startSequence, String endSequence, IToken token) {
        super(startSequence, endSequence, token);
        sStart = startSequence;
    }

    @Override
    protected boolean sequenceDetected(ICharacterScanner scanner, char[] sequence,
            boolean eofAllowed) {
        boolean res = super.sequenceDetected(scanner, sequence, eofAllowed);
        
        String seq = new String(sequence);
        if (!seq.equals(sStart) || startSeqRead) {
            return res;
        }
        if (res) {
            int c = scanner.read();
            scanner.unread();
            if (Character.isLetter(c)) {
                startSeqRead = true;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    @Override
    public IToken evaluate(ICharacterScanner scanner) {
        startSeqRead = false;
        return super.evaluate(scanner);
    }
    
    @Override
    public IToken evaluate(ICharacterScanner scanner, boolean resume) {
        startSeqRead = false;
        return super.evaluate(scanner, resume);
    }

}
