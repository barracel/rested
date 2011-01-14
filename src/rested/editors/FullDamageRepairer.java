package rested.editors;

import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.presentation.IPresentationDamager;
import org.eclipse.jface.text.presentation.IPresentationRepairer;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;

/**
 * 
 * Hack to force the partition to update on every keystroke. 
 * Otherwise sections rules are no "real time updated". 
 * //TODO: Really groK the performance/implication of what we do here...
 */
public class FullDamageRepairer extends DefaultDamagerRepairer {

    public FullDamageRepairer(ITokenScanner scanner) {
        super(scanner);
    }

    @Override
    public IRegion getDamageRegion(ITypedRegion partition, DocumentEvent event,
            boolean documentPartitioningChanged) {
        return partition;
    }

}
