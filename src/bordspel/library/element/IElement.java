package bordspel.library.element;

import bordspel.library.element.event.KeyEvent;
import bordspel.library.element.event.MouseEvent;

public interface IElement {
	
	public void _draw(Layer layer);
	public void _key(KeyEvent e);
	public void _mouse(MouseEvent e);

}
