package bordspel.library.element;

import java.util.concurrent.CopyOnWriteArrayList;

import processing.core.PApplet;

public class Layer {

	private PApplet sketch;
	public String name;
	public boolean keyPressed;
	
	private LayerManager layerManager;
	
	private CopyOnWriteArrayList<Element> elements = new CopyOnWriteArrayList<>();
	
	protected Layer(LayerManager layerManager, PApplet sketch, String name) {
		this.layerManager = layerManager;
		this.sketch = sketch;
		this.name = name;
	}
	
	public PApplet getSketch() {
		return this.sketch;
	}
	
	public LayerManager getLayerManager() {
		return this.layerManager;
	}
	
	public String getName() {
		return this.name;
	}
	
	public CopyOnWriteArrayList<Element> getElements() {
		return this.elements;
	}
	
	/*
	 * Helper functions to be able to manipulate elements more easily.
	 */	
	public Element getElementById(String id) {
		for (Element element : this.elements) {
			if (element.getID() == id) {
				return element;
			}
		}
		
		return null;
	}
	
	/*
	 * Used to create Element objects or to create pre-made Elements.
	 */
	public Element createElement(String id, int x, int y) {
		Element e = new Element(id, x, y);
		this.elements.add(e);
		return e;
	}
	
	public void addElement(Element element) {
		this.elements.add(element);
	}
	
	public void removeElement(Element element) {
		this.elements.remove(element);
	}
	
}
