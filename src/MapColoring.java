import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

/**
 * 
 */

/**
 * @author Piyush
 *
 */
public class MapColoring {
	
	private static int _sizeOfMatrix = 20;
	private static int _nbrOfPoints = 6;
	private static char _map[][];
	private static List<Point> _listOfNodes;
	private static Set<String> _CoordinatesSet = new HashSet<String>();
	//private static String[] _colorArray = new String[]{"red", "green", "blue", "yellow"};
	private static Set<String> _lineSegmentIdSet = new HashSet<String>();
	private static List<LineSegment> _lineSegmentList = new ArrayList<LineSegment>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createMap();
		printMap();
		searchAllNeighbors();
		linkCities();
	}
	
	public static void linkCities() 
	{

		LineSegment line = null;
		for(Point currPoint : _listOfNodes)
		{
			LinkedHashMap<Point, Float> sortedMap = currPoint.getNeighbors();
			LinkedHashMap<Point, Float> newFilteredSortedMap = new LinkedHashMap<Point, Float>();
			for(Entry<Point, Float> entry : sortedMap.entrySet())
			{
				Point neighbor = entry.getKey();
				Float distance = entry.getValue();
				String lineId = String.valueOf(currPoint.getId())+":"+String.valueOf(neighbor.getId());
				String lineIdRev = String.valueOf(neighbor.getId())+":"+String.valueOf(currPoint.getId());
				
				if(!_lineSegmentIdSet.contains(lineId+"-c"))
				{
					//_lineSegmentIdSet.add(lineId);
					//_lineSegmentIdSet.add(lineIdRev);
					
					LineSegment lineA = new LineSegment(currPoint, neighbor, lineId);
					
					if(_lineSegmentList.size() == 0)
					{
						_lineSegmentList.add(lineA);
						neighbor.setConnected(true);
						newFilteredSortedMap.put(neighbor, distance);
						_lineSegmentIdSet.add(lineId+"-c");
						_lineSegmentIdSet.add(lineIdRev+"-c");
					}
					else
					{
						boolean validLine = true;
						for(int i = 0; i < _lineSegmentList.size(); i++)
						{
							LineSegment lineB = _lineSegmentList.get(i);
							if(lineA.lineId != lineB.lineId)
							{
								Point p0 = lineA._begin_;
								Point p1 = lineA._end_;
								Point p2 = lineB._begin_;
								Point p3 = lineB._end_;
								int retVal = lineA.doLinesIntersect(p0,p1,p2,p3);
								if(retVal == 2 || retVal == 3 )
								{
									validLine = false;
									break;
								}
							}
						}
						if(validLine)
						{
							_lineSegmentList.add(lineA);
							neighbor.setConnected(true);
							newFilteredSortedMap.put(neighbor, distance);
							_lineSegmentIdSet.add(lineId+"-c");
							_lineSegmentIdSet.add(lineIdRev+"-c");
						}
						else
						{
							_lineSegmentIdSet.add(lineId+"-d");
							_lineSegmentIdSet.add(lineIdRev+"-d");
						}
					}
					
				}
				else
				{
					neighbor.setConnected(true);
					newFilteredSortedMap.put(neighbor, distance);
				}
			}
			currPoint.setNeighbors(newFilteredSortedMap);
		}
	}
	
	public static void searchAllNeighbors() 
	{
		// Give equal priority to all the nodes, so that the map has multiple routes.
		// Also try to first link the closest nodes.
		for(Point currentPoint : _listOfNodes)
		{
			HashMap<Point, Float> map = new HashMap<Point, Float>();
			// for each city, populate its neighbor list with distance.
			for(Point neighbor : _listOfNodes)
			{
				List<NeighboringNode> list = new ArrayList<NeighboringNode>();
				
				if(currentPoint.getId() != neighbor.getId())
				{
					NeighboringNode nNode = new NeighboringNode(neighbor);
					nNode.setEuclideanDistance(getEuclideanDistance(currentPoint, neighbor));
					nNode.setConnected(false);
					map.put(neighbor, getEuclideanDistance(currentPoint, neighbor));
					list.add(nNode);
				}
			}
			LinkedHashMap<Point, Float> sortedMap = new LinkedHashMap<Point, Float>();
			sortedMap = sortHashMapByValue(map);
			currentPoint.setNeighbors(sortedMap);
		}
	}
	
	public static float getEuclideanDistance(Point a, Point b)
	{
		/*calculateHeuristics(x, y):
		  Manhattan distance from point a to point b
		  abs(x - destinationNode.x) + abs(y - destinationNode.y)*/

		float score = 0;
		float arg1 = (java.lang.Math.abs(a.getX() - b.getX()))
				*(java.lang.Math.abs(a.getX() - b.getX()));
		float arg2 = (java.lang.Math.abs(a.getY() - b.getY()))
				*(java.lang.Math.abs(a.getY() - b.getY()));
		score = (float) java.lang.Math.sqrt(arg1+arg2);

		return score;
	}
	
	public static void printMap() 
	{
		System.out.println();
		for(int i = 0; i <_sizeOfMatrix ; i++) 
		{
			System.out.print("\t\t\t");
			for(int j = 0; j <_sizeOfMatrix; j++) 
			{
				System.out.print(_map[i][j]+" ");
			}
			System.out.println(i);
		}
	}
	
	public static void createMap()
	{
		_map = new char[_sizeOfMatrix][_sizeOfMatrix];
		for(int i = 0; i <_sizeOfMatrix ; i++) 
			for(int j = 0; j <_sizeOfMatrix; j++) 
				_map[i][j] = '.';
		
		Random randomNbrGenerator = new Random();
		_listOfNodes = new ArrayList<Point>();
		
		int i = 0;
		while(i < _nbrOfPoints)
		{
			int x = randomNbrGenerator.nextInt(_sizeOfMatrix-1);
			int y = randomNbrGenerator.nextInt(_sizeOfMatrix-1);
			
			if(!_CoordinatesSet.contains(String.valueOf(x)+","+String.valueOf(y)))
			{
				Point point = new Point(i, x, y);
				_listOfNodes.add(point);
				_CoordinatesSet.add(String.valueOf(x)+","+String.valueOf(y));
				_map[x][y] = 'c'; // c denotes city
				i++;
			}
		}
	}
	
	public static LinkedHashMap<Point, Float> sortHashMapByValue(HashMap<Point, Float> map) 
	{
		List<Point> mapKeys = new ArrayList<Point>(map.keySet());
		List<Float> mapValues = new ArrayList<Float>(map.values());
		Collections.sort(mapValues);

		LinkedHashMap<Point, Float> sortedMap = new LinkedHashMap<Point, Float>();

		Iterator<Float> valueIt = mapValues.iterator();
		while (valueIt.hasNext()) 
		{
			Float val = valueIt.next();
			Iterator<Point> keyIt = mapKeys.iterator();

			while (keyIt.hasNext()) 
			{
				Point key = keyIt.next();
				String comp1 = map.get(key).toString();
				String comp2 = val.toString();

				if (comp1.equals(comp2)) {
					map.remove(key);
					mapKeys.remove(key);
					sortedMap.put(key, val);
					break;
				}

			}

		}
		return sortedMap;
	}

}
