
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 
 */

/**
 * @author Piyush
 *
 */
public class Point {
	
	private int id = 0;
	private int x =0;
	private int y =0;
	private List<NeighboringNode> neighborList;
	private boolean connected = false;
	private LinkedHashMap<Point, Float> neighbors;
	
	public Point() {
	}
	
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Point(int id, int x, int y) {
		super();
		this.setId(id);
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the neighborList
	 */
	public List<NeighboringNode> getNeighborList() {
		return neighborList;
	}

	/**
	 * @param neighborList the neighborList to set
	 */
	public void setNeighborList(List<NeighboringNode> neighborList) {
		this.neighborList = neighborList;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the connected
	 */
	public boolean isConnected() {
		return connected;
	}

	/**
	 * @param connected the connected to set
	 */
	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	/**
	 * @return the neighbors
	 */
	public LinkedHashMap<Point, Float> getNeighbors() {
		return neighbors;
	}

	/**
	 * @param neighbors the neighbors to set
	 */
	public void setNeighbors(LinkedHashMap<Point, Float> neighbors) {
		this.neighbors = neighbors;
	}

}
