package rested.editors;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class ColorManager {
    static RGB HIGH = new RGB(127, 0, 81);
    static RGB TITLE = new RGB(0, 200, 0);
    static RGB LINK = new RGB(0, 60, 200);
    static RGB DEFAULT = new RGB(0, 0, 0);
    static RGB DEFAULT_SECTION = new RGB(255, 0, 255);
    
	protected Map fColorTable = new HashMap(10);

	public void dispose() {
		Iterator e = fColorTable.values().iterator();
		while (e.hasNext())
			 ((Color) e.next()).dispose();
	}
	public Color getColor(RGB rgb) {
		Color color = (Color) fColorTable.get(rgb);
		if (color == null) {
			color = new Color(Display.getCurrent(), rgb);
			fColorTable.put(rgb, color);
		}     
		return color;
	}
}
