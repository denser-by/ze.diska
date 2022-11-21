package mydev.about;

public class Pticca3D extends Colorfull {
	protected Point2D a;
	protected Point2D b;
	protected Point2D c;
	protected Point2D d;
	protected int[] pvzh;
	protected short fwz;
	protected short fhz;

	public Pticca3D(Point2D a, Point2D b, Point2D c, Point2D d) {
		super(Color.rgb(255, 255, 255));
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.fwz = 122;
		this.fhz = 122;
		this.pvzh = new int[fwz * fhz];
		int c1 = Color.rgb(141, 133, 133);
		for (int i = 0; i < fhz * fwz; i++)
			pvzh[i] = c1;
	}

	void rt(int[] pvx, short fwx, short fhx, int i, int j, int r, int g, int b) {
		pvx[j * fwx + i] = Color.rgb(r, g, b);
	}

	public void represent(Matrix2D matrix) {
		matrix.addPticca(this);
	}

	public void toPen(Canvas ics, Paint pn) {
		ics.drawLine(a.getX(), a.getY(), b.getX(), b.getY(), pn);
		ics.drawLine(b.getX(), b.getY(), c.getX(), c.getY(), pn);
		ics.drawLine(c.getX(), c.getY(), d.getX(), d.getY(), pn);
		ics.drawLine(d.getX(), d.getY(), a.getX(), a.getY(), pn);
	}
}