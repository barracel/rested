package rested.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.*;

import rested.editors.rules.ExMultilineRule;
import rested.editors.rules.SectionRule;
/**
 * Basic partition scanner needed for multiline rules. Otherwise the partitions are not 
 * correctly update while editing. 
 *
 */
public class ReSTEdPartitionScanner extends RuleBasedPartitionScanner {
    public final static String SECTION = "__ReSTEd_SECTION";

	public ReSTEdPartitionScanner() {

		IToken section = new Token(SECTION);
		IToken defaultCon = new Token(IDocument.DEFAULT_CONTENT_TYPE);
		

		List<IPredicateRule> rules = new ArrayList<IPredicateRule>();
		rules.add(new SectionRule(section));
		rules.add(new ExMultilineRule("*", "*", defaultCon));
        rules.add(new ExMultilineRule("**", "**", defaultCon));
        rules.add(new ExMultilineRule("`", "`", defaultCon));
        rules.add(new ExMultilineRule("``", "``", defaultCon));
		
		setPredicateRules(rules.toArray(new IPredicateRule[0]));
	}
}
