package bordspel.library.element.custom;

import bordspel.library.element.Element;
import bordspel.library.element.IElement;
import bordspel.library.element.Layer;
import bordspel.library.element.event.KeyEvent;
import bordspel.library.element.event.MouseEvent;
import processing.core.PApplet;
import processing.core.PConstants;

public class Text extends Element implements IElement {

	private String text;
	private int textSize = 18;
	private int color = 0;
	
	public Text(String id, int x, int y, String text) {
		super(id, x, y);
		
		this.text = text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}
	
	public void setColor(int color) {
		this.color = color;
	}

	@Override
	public void _draw(Layer layer) {
		PApplet sketch = layer.getSketch();
		
		sketch.textSize(this.textSize);
		sketch.textAlign(PConstants.CENTER, PConstants.CENTER);
		
		sketch.fill(this.color);
		sketch.text(this.text, this.x, this.y);
	}

	@Override
	public void _key(KeyEvent e) {}

	@Override
	public void _mouse(MouseEvent e) {}

}
