package bordspel.library.element.custom;

import org.python.core.PyTuple;

import bordspel.library.element.Element;
import bordspel.library.element.IElement;
import bordspel.library.element.Layer;
import bordspel.library.element.event.KeyEvent;
import bordspel.library.element.event.MouseEvent;
import processing.core.PApplet;
import processing.core.PConstants;

public class Button extends Element implements IElement {

	private int w;
	private int h;
	
	private PyTuple normalColor;
	private PyTuple highlightColor;
	
	public Button(String id, int x, int y, int w, int h, PyTuple normalColor, PyTuple highlightColor) {
		super(id, x, y);
		
		this.w = w;
		this.h = h;
		
		this.normalColor = normalColor;
		this.highlightColor = highlightColor;
		
		this.createBound();
		this.addBoundLocation(x, y);
		this.addBoundLocation(x + w, y);
		this.addBoundLocation(x, y + h);
		this.addBoundLocation(x + w, y + h);
	}
	
	public void setNormalColor(PyTuple normalColor) {
		this.normalColor = normalColor;
	}
	
	public void setHighlightColor(PyTuple highlightColor) {
		this.highlightColor = highlightColor;
	}

	@Override
	public void _draw(Layer layer) {
		PApplet sketch = layer.getSketch();
		
		// Check if the mouse is hovering over the button.
		if (this.hovering) {
			if (!layer.getLayerManager().activeCursors.contains(this.toString()))
				layer.getLayerManager().activeCursors.add(this.toString());
			sketch.cursor(PConstants.HAND);
			
			sketch.fill((int)this.highlightColor.get(0), 
						(int)this.highlightColor.get(1),
						(int)this.highlightColor.get(2));
		} else {
			if (layer.getLayerManager().activeCursors.contains(this.toString()))
				layer.getLayerManager().activeCursors.remove(this.toString());
			
			sketch.fill((int)this.normalColor.get(0), 
					(int)this.normalColor.get(1),
					(int)this.normalColor.get(2));
		}
		
		sketch.noStroke();
		sketch.rect(this.x, this.y, this.w, this.h);
	}

	@Override
	public void _key(KeyEvent e) {}

	@Override
	public void _mouse(MouseEvent e) {}

}
