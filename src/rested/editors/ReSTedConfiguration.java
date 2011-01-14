package rested.editors;

import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.PatternRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.SWT;

import rested.editors.rules.ExMultilineRule;
import rested.editors.rules.SectionRule;


class SectionScanner extends RuleBasedScanner {
    public SectionScanner(ColorManager colorManager) {
        Token title = new Token(new TextAttribute(colorManager.getColor(ColorManager.TITLE)));

        List<IRule> rules = new ArrayList<IRule>();
        rules.add(new SectionRule(title));
        
        setRules(rules.toArray(new IRule[0]));
    }
}

class Scanner extends RuleBasedScanner {
    public Scanner(ColorManager colorManager) {
        Token title = new Token(new TextAttribute(colorManager.getColor(ColorManager.TITLE)));
        Token comment = new Token(new TextAttribute(colorManager.getColor(ColorManager.COMMENT)));
        Token emphasis = new Token(new TextAttribute(colorManager.getColor(ColorManager.COMMENT)));
        Token strongEmphasis = new Token(new TextAttribute(colorManager.getColor(ColorManager.COMMENT)));
        Token interpreted = new Token(new TextAttribute(colorManager.getColor(ColorManager.COMMENT)));
        Token inlineLiteral = new Token(new TextAttribute(colorManager.getColor(ColorManager.COMMENT)));
        Token link = new Token(new TextAttribute(colorManager.getColor(ColorManager.LINK), null, SWT.BOLD));
        Token lists = new Token(new TextAttribute(colorManager.getColor(ColorManager.DEFAULT), null, SWT.BOLD));
        
        List<IRule> rules = new ArrayList<IRule>();
        rules.add(new SingleLineRule("#", null, comment));
        rules.add(new ExMultilineRule("*", "*", emphasis));
        rules.add(new ExMultilineRule("**", "**", strongEmphasis));
        rules.add(new ExMultilineRule("`", "`", interpreted));
        rules.add(new ExMultilineRule("``", "``", inlineLiteral));
        rules.add(new SingleLineRule("http://", " ", link));
        rules.add(new SingleLineRule("https://", " ", link));
        PatternRule pr = new PatternRule("*", " ", lists, (char) 0, true);
        pr.setColumnConstraint(0);
        rules.add(pr);
        pr = new PatternRule("-", " ", lists, (char) 0, true);
        pr.setColumnConstraint(0);
        rules.add(pr);
        rules.add(new SectionRule(title));

        setRules(rules.toArray(new IRule[0]));
    }
}

public class ReSTedConfiguration extends SourceViewerConfiguration {
    private Scanner scanner;
    private SectionScanner sectionScanner;
    private ColorManager colorManager;

    public ReSTedConfiguration(ColorManager colorManager) {
        this.colorManager = colorManager;
    }

    protected Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(colorManager);
            scanner.setDefaultReturnToken(new Token(new TextAttribute(colorManager
                    .getColor(ColorManager.DEFAULT))));
        }
        return scanner;
    }
    
    protected SectionScanner getSectionScanner() {
        if (sectionScanner == null) {
            sectionScanner = new SectionScanner(colorManager);
            sectionScanner.setDefaultReturnToken(new Token(new TextAttribute(colorManager
                    .getColor(ColorManager.DEFAULT_SECTION))));
        }
        return sectionScanner;
    }
    
    @Override
    public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
        return new String[] {
            IDocument.DEFAULT_CONTENT_TYPE,
            ReSTEdPartitionScanner.SECTION};
    }

    @Override
    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
        PresentationReconciler reconciler = new PresentationReconciler();



        DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getScanner());
        reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
        reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
        
        //Section scanner is handled in as separated content so we use the FullDamageRepairer
        //only in the needed sections (is really overkill...)
        dr = new FullDamageRepairer(getSectionScanner());
        reconciler.setDamager(dr, ReSTEdPartitionScanner.SECTION);
        reconciler.setRepairer(dr, ReSTEdPartitionScanner.SECTION);

        return reconciler;
    }
}