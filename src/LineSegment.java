/**
 * 
 */

/**
 * @author Piyush
 *
 */
public class LineSegment {

	public Point _begin_;
	public Point _end_;
	public String lineId;

	public LineSegment(Point b, Point e)
	{
		_begin_ = b;
		_end_ = e;
	}

	public LineSegment() {
		// TODO Auto-generated constructor stub
	}

	public LineSegment(Point b, Point e, String id)
	{
		_begin_ = b;
		_end_ = e;
		lineId = id;
	}

	public int Intersect(LineSegment other_line)
	{
		float denom = ((other_line._end_.getY() - other_line._begin_.getY())*(_end_.getX() - _begin_.getX())) -
				((other_line._end_.getX() - other_line._begin_.getX())*(_end_.getY() - _begin_.getY()));

		float nume_a = ((other_line._end_.getX() - other_line._begin_.getX())*(_begin_.getY() - other_line._begin_.getY())) -
				((other_line._end_.getY() - other_line._begin_.getY())*(_begin_.getX() - other_line._begin_.getX()));

		float nume_b = ((_end_.getX() - _begin_.getX())*(_begin_.getY() - other_line._begin_.getY())) -
				((_end_.getY() - _begin_.getY())*(_begin_.getX() - other_line._begin_.getX()));

		if(denom == 0.0f)
		{
			if(nume_a == 0.0f && nume_b == 0.0f)
			{
				return 2;
			}
			return 1;
		}

		float ua = nume_a / denom;
		float ub = nume_b / denom;

		if(ua >= 0.0f && ua <= 1.0f && ub >= 0.0f && ub <= 1.0f)
		{
			// Get the intersection point.
			Point p = new Point();
           float x = (_begin_.getX() + ua*(_end_.getX() - _begin_.getX()));
           float y = _begin_.getY() + ua*(_end_.getY() - _begin_.getY());
           
           // if the point of intersection is the begin or end node, we don't count that
           // as intersection.
           if((x == _begin_.getX() & y == _begin_.getY()) ||
        		   (x == _end_.getX() & y == _end_.getY()))
           {
        	   return 4;
           }

			return 3;
		}

		return 4;
	}

	public static void DoLineSegmentIntersection(Point p0, Point p1, Point p2, Point p3)
	{
		LineSegment linesegment0 = new LineSegment(p0, p1);
		LineSegment linesegment1 = new LineSegment(p2, p3);

		switch(linesegment0.Intersect(linesegment1))
		{
		case 1:
			System.out.println("The lines are parallel\n\n");
			break;
		case 2:
			System.out.println("The lines are coincident\n\n");
			break;
		case 3:
			System.out.println("The lines are intersecting\n\n");
			break;
		case 4:
			System.out.println("The lines are not intersecting\n\n");
			break;
		}
	}

	public int doLinesIntersect(Point p0, Point p1, Point p2, Point p3)
	{
		int retVal = 0;
		LineSegment linesegment0 = new LineSegment(p0, p1);
		LineSegment linesegment1 = new LineSegment(p2, p3);

		switch(linesegment0.Intersect(linesegment1))
		{
		case 1:
			//System.out.println("The lines are parallel\n\n");
			retVal = 1;
			break;
		case 2:
			//System.out.println("The lines are coincident\n\n");
			retVal = 2;
			break;
		case 3:
			//System.out.println("The lines are intersecting\n\n");
			retVal = 3;
			break;
		case 4:
			//System.out.println("The lines are not intersecting\n\n");
			retVal = 4;
			break;
		}
		return retVal;
	}
	
	public int doLinesIntersect(LineSegment b)
	{
		int retVal = 0;
		switch(Intersect(b))
		{
		case 1:
			System.out.println(_begin_.getX()+","+_begin_.getY()+" and line "+b._begin_.getX()+","+b._begin_.getY());
			System.out.println("The lines are parallel\n\n");
			retVal = 1;
			break;
		case 2:
			System.out.println(_begin_.getX()+","+_begin_.getY()+" and line "+b._begin_.getX()+","+b._begin_.getY());
			System.out.println("The lines are coincident\n\n");
			retVal = 2;
			break;
		case 3:
			System.out.println(_begin_.getX()+","+_begin_.getY()+" and line "+b._begin_.getX()+","+b._begin_.getY());
			System.out.println("The lines are intersecting\n\n");
			retVal = 3;
			break;
		case 4:
			System.out.println(_begin_.getX()+","+_begin_.getY()+" and line "+b._begin_.getX()+","+b._begin_.getY());
			System.out.println("The lines are not intersecting\n\n");
			retVal = 4;
			break;
		}
		return retVal;
	}

	public static void main(String[] args)
	{
		DoLineSegmentIntersection(new Point(0, 0), new Point(5, 5), new Point(5, 0), new Point(0, 5));
		DoLineSegmentIntersection(new Point(0, 0), new Point(2, 2), new Point(0, 2), new Point(2, 4));
		DoLineSegmentIntersection(new Point(10, 0), new Point(0, 10), new Point(5, 0), new Point(10, 10));
	}
}
