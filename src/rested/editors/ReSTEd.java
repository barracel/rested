package rested.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class ReSTEd extends TextEditor {

	private ColorManager colorManager;

	public ReSTEd() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new ReSTedConfiguration(colorManager));
		setDocumentProvider(new ReSTEdDocumentProvider());
	}
	
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
