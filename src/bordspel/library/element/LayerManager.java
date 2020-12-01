package bordspel.library.element;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class LayerManager {

	private PApplet sketch;
	private CopyOnWriteArrayList<Layer> layers = new CopyOnWriteArrayList<>();
	
	private Layer activeLayer;
	
	public LayerManager(PApplet sketch) {
		this.sketch = sketch;
		
		this.activeLayer = null;
		
		this.sketch.registerMethod("draw", this);
		this.sketch.registerMethod("keyEvent", this);
		this.sketch.registerMethod("mouseEvent", this);
	}
	
	public PApplet getSketch() {
		return this.sketch;
	}
	
	public CopyOnWriteArrayList<Layer> getLayers() {
		return this.layers;
	}
	
	public Layer getActiveLayer() {
		if (this.hasActiveLayer()) {
			return this.activeLayer;
		}
		
		return null;
	}
	
	public boolean hasActiveLayer() {
		return this.activeLayer != null;
	}
	
	public Layer createLayer(String name) {
		Layer layer = new Layer(this.getSketch(), name);
		this.layers.add(layer);
		return layer;
	}
	
	public void removeLayer(Layer layer) {
		if (this.activeLayer == layer) {
			this.activeLayer = null;
		}
		this.layers.remove(layer);
	}
	
	public void removeLayerByName(String name) {
		for (Layer layer : this.layers) {
			if (layer.getName() == name) {
				if (this.activeLayer == layer) {
					this.activeLayer = null;
				}
				
				this.layers.remove(layer);
				return;
			}
		}
	}
	
	public void setActiveLayer(Layer layer) {
		this.activeLayer = layer;
	}
	
	public void setActiveLayerByName(String name) {
		for (Layer layer : this.layers) {
			if (layer.getName() == name) {
				this.activeLayer = layer;
				return;
			}
		}
	}
	
	/*
	 * These events will automatically get called by processing.
	 * These functions will call all the elements with either the draw, key or mouse event.
	 */
	public void draw() {
		this.getSketch().background(255);
		
		if (this.hasActiveLayer()) {
			for (Element element : this.activeLayer.getElements()) {
				if (!element.isHidden()) {
					element._callDraw(this.activeLayer);
					element._draw(this.activeLayer);
				}
			}
		}
	}
	
	public void keyEvent(KeyEvent e) {
		if (this.hasActiveLayer()) {
			String type = "";
			if (e.getAction() == KeyEvent.PRESS)
				type = "PRESS";
			if (e.getAction() == KeyEvent.RELEASE)
				type = "RELEASE";
			if (e.getAction() == KeyEvent.TYPE)
				type = "TYPE";
			
			String key = String.valueOf(e.getKey());
			int keyCode = e.getKeyCode();
			
			if (type == "PRESS") {
				this.activeLayer.keyPressed = true;
			}
			if (type == "RELEASE") {
				this.activeLayer.keyPressed = false;
			}
			
			bordspel.library.element.event.KeyEvent keyEvent = new bordspel.library.element.event.KeyEvent(type, key, keyCode);
			for(Iterator<Element> element = this.activeLayer.getElements().iterator(); element.hasNext();){
				Element ele = element.next();
				ele._callKey(keyEvent);
				ele._key(keyEvent);
			}
		}
	}
	
	public void mouseEvent(MouseEvent e) {
		if (this.hasActiveLayer()) {
			String type = "";
			String button = "";
			if (e.getAction() == MouseEvent.PRESS)
				type = "PRESS";
			if (e.getAction() == MouseEvent.CLICK)
				type = "CLICK";
			if (e.getAction() == MouseEvent.RELEASE)
				type = "RELEASE";
			if (e.getAction() == MouseEvent.DRAG)
				type = "DRAG";
			if (e.getAction() == MouseEvent.MOVE)
				type = "MOVE";
			if (e.getAction() == MouseEvent.ENTER)
				type = "ENTER";
			if (e.getAction() == MouseEvent.WHEEL)
				type = "WHEEL";
			if (e.getAction() == MouseEvent.EXIT)
				type = "EXIT";
			
			if (e.getButton() == PConstants.RIGHT)
				button = "RIGHT";
			if (e.getButton() == PConstants.LEFT)
				button = "LEFT";
			if (e.getButton() == PConstants.CENTER)
				button = "CENTER";
			
			bordspel.library.element.event.MouseEvent mouseEvent = new bordspel.library.element.event.MouseEvent(type, button, e.getX(), e.getY());
			
			for (Element element : this.activeLayer.getElements()) {
				element._callMouse(mouseEvent);
				element._mouse(mouseEvent);
			}
		}
	}
	
}
