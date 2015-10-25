/**
 * 
 */

/**
 * @author Piyush
 *
 */
public class NeighboringNode 
{
	
	private Point node = null;
	private boolean isConnected = false;
	private float euclideanDistance = 0f;
	
	public NeighboringNode(Point node)
	{
		this.node = node;
	}
	/**
	 * @return the node
	 */
	public Point getNode() {
		return node;
	}
	/**
	 * @param node the node to set
	 */
	public void setNode(Point node) {
		this.node = node;
	}
	/**
	 * @return the isConnected
	 */
	public boolean isConnected() {
		return isConnected;
	}
	/**
	 * @param isConnected the isConnected to set
	 */
	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}
	/**
	 * @return the euclideanDistance
	 */
	public float getEuclideanDistance() {
		return euclideanDistance;
	}
	/**
	 * @param euclideanDistance the euclideanDistance to set
	 */
	public void setEuclideanDistance(float euclideanDistance) {
		this.euclideanDistance = euclideanDistance;
	}

}
