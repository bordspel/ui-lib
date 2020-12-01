package bordspel.library.element.event;

import org.python.core.Py;
import org.python.core.PyString;

public class KeyEvent {

	public String type;
	public PyString key;
	public int keyCode;
	
	public KeyEvent(String type, String key, int keyCode) {
		this.type = type;
		this.key = Py.newStringOrUnicode(key);
		this.keyCode = keyCode;
	}
	
	public String getType() {
		return this.type;
	}
	
	public PyString getKey() {
		return this.key;
	}
	
	public int getKeyCode() {
		return this.keyCode;
	}
	
}
