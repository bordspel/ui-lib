package bordspel.library.element.event;

public class MouseEvent {

	public String type;
	public String button;
	
	public int x;
	public int y;
	
	public MouseEvent(String type, String button, int x, int y) {
		this.type = type;
		this.button = button;
		this.x = x;
		this.y = y;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getButton() {
		return this.button;
	}
	
}
