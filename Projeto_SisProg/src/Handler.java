import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	private GameObject cursor;
	
	public void tick()
	{
		for (int i = 0; i < object.size(); i++)
		{
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g)
	{
		for (int i = 0; i < object.size(); i++)
		{
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object)
	{
		this.object.add(object);
	}
	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}
	public void clearAll()
	{
		this.object.clear();
	}
	
	public void clearCursor()
	{
		for (int i = 0; i < object.size(); i++)
		{
			if(this.object.get(i).getID() == ID.Cursor) cursor = this.object.get(i);		
		}
		this.object.remove(cursor);
	}
}
