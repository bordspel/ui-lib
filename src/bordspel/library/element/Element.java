package bordspel.library.element;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import org.python.core.PyObject;

import bordspel.library.element.event.KeyEvent;
import bordspel.library.element.event.MouseEvent;

public class Element implements IElement {
	
	public String id;
	public int x;
	public int y;
	
	public boolean hidden = false;
	
	protected CopyOnWriteArrayList<PyObject> drawHandlers = new CopyOnWriteArrayList<>();
	protected CopyOnWriteArrayList<PyObject> keyHandlers = new CopyOnWriteArrayList<>();
	protected CopyOnWriteArrayList<PyObject> mouseHandlers = new CopyOnWriteArrayList<>();
	
	protected Element(String id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	/*
	 * Helper function to be able to manipulate an Element more easily.
	 */
	public String getID() {
		return this.id;
	}
	
	public boolean isHidden() {
		return this.hidden;
	}
	
	public void hide() {
		this.hidden = true;
	}
	
	public void show() {
		this.hidden = false;
	}
	
	public int getX() {
		return this.x;		
	}
	
	public int getY() {
		return this.y;
	}
	
	/*
	 * Register and unregister listeners.
	 */
	public Element registerDrawListener(PyObject po) {
		this.drawHandlers.add(po);
		return this;
	}
	
	public Element unregisterDrawListener(PyObject po) {
		this.drawHandlers.remove(po);
		
		return this;
	}
	
	public Element registerKeyListener(PyObject po) {
		this.keyHandlers.add(po);
		return this;
	}
	
	public Element unregisterKeyListener(PyObject po) {
		this.keyHandlers.remove(po);
		
		return this;
	}
	
	public Element registerMouseListener(PyObject po) {
		this.mouseHandlers.add(po);
		return this;
	}
	
	public Element unregisterMouseListener(PyObject po) {
		this.mouseHandlers.remove(po);
		
		return this;
	}
	
	/*
	 * These functions will call the registered listeners when invoked.
	 */
	public void _callDraw(Layer layer) {
		Object[] oj = {layer, this};
		
		Iterator<PyObject> iter = this.drawHandlers.iterator();
		while(iter.hasNext()) {
			PyObject po = iter.next();
			if (po != null)
				po._jcall(oj);
		}
	}
	
	public void _callKey(KeyEvent e) {
		Object[] oj = {e};

		Iterator<PyObject> iter = this.keyHandlers.iterator();
		while(iter.hasNext()) {
			PyObject po = iter.next();
			if (po != null)
				po._jcall(oj);	
		}
	}
	
	public void _callMouse(MouseEvent e) {
		Object[] oj = {e};
		
		Iterator<PyObject> iter = this.mouseHandlers.iterator();
		while(iter.hasNext()) {
			PyObject po = iter.next();
			if (po != null)
				po._jcall(oj);
		}
	}
	
	@Override
	public void _draw(Layer layer) {}

	@Override
	public void _key(KeyEvent e) {}

	@Override
	public void _mouse(MouseEvent e) {}
	
}
