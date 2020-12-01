package bordspel.library.element.event;

import org.python.core.PyByteArray;

public class KeyEvent {

	public String type;
	public PyByteArray key;
	public int keyCode;
	
	public KeyEvent(String type, char key, int keyCode) {
		this.type = type;
		this.key = new PyByteArray(key);
		this.keyCode = keyCode;
	}
	
	public String getType() {
		return this.type;
	}
	
	public PyByteArray getKey() {
		return this.key;
	}
	
	public int getKeyCode() {
		return this.keyCode;
	}
	
}
