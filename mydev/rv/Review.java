package mydev.rv;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Point;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.DirectColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import mydev.aaa.Sleeper;
import mydev.about.Canvas;
import mydev.about.Circle2D;
import mydev.about.Colorfull;
import mydev.about.ColorsArray;
import mydev.about.Paint;
import mydev.about.Point2D;
import mydev.about.Sector2D;
import mydev.bbb.Enter2;
import mydev.bbb.Location;
import mydev.cndata.AbstractDailyObservation;
import mydev.cndata.DailyObservationRepo;
import mydev.uix.CommonCanvas;
import mydev.vutils.AverageRec;
import mydev.vutils.Data;
import mydev.vutils.Ester;
import mydev.vutils.Files;
import mydev.vutils.Filter;
import mydev.vutils.Karta;
import mydev.vutils.Metr;
import mydev.vutils.Order;
import mydev.vutils.Queue;
import mydev.vutils.SharedGreatesArray;
import mydev.vutils.Spiska;
import mydev.vutils.Stachko;

public class Review {
	final static boolean D = false;
	static String pictureName = "name.bmp";

	public Review() {
		super();
	}

	public static void main(String[] args) {
		Review main = new Review();
		main.setCaption("Leaves Days Analytics");
		main.setImagePath(args.length > 0 ? args[0] : "");
		main.createWindow();
		main.reMoveRnd();
		main.resize(1024, (int) (768 * .6439f));
		main.start();
		Sleeper sleeper = new Sleeper();
		while (WorkingRvHolder.working) {
			main.refreshUpdatable();
			sleeper.sleep(123);
		}
		main.destroyWindow();
		System.exit(1 - 1);
	}

	private void resize(int width, int height) {
		WindowProvider.instance().resizeWindow(width, height);
	}

	protected void reMoveRnd() {
		Location ol = Enter2.instance().location();
		Point p = ol.getRandomExternalPos((short) 1024, (short) 768);
		WindowProvider.instance().moveWindow(p.x, p.y);
	}

	protected void refreshUpdatable() {
	}

	public void destroyWindow() {
		WindowProvider.instance().destroyWindow();
	}

	public void start() {
		WindowProvider.instance().start();
	}

	public void createWindow() {
		WindowProvider.instance().createWindow();
	}

	public void setImagePath(String path) {
		WindowProvider.instance().setImagePath(path);
	}

	public void setCaption(String title) {
		WindowProvider.instance().setCaption(title);
	}
}

class WindowProvider {
	private static WindowProvider inst;
	private String title;
	private String imgPath;
	private boolean bgMode;
	private BgWindow bgWindow;
	private NormalWindow normalWindow;

	WindowProvider() {
		super();
		this.bgMode = false;
	}

	public void resizeWindow(int width1, int height1) {
		getCurrentWindow().resizeWindow(width1, height1);
	}

	public void moveWindow(int x1, int y1) {
		getCurrentWindow().moveWindow(x1, y1);
	}

	public void setCaption(String title) {
		this.title = title;
	}

	public void setImagePath(String imgPath) {
		this.imgPath = imgPath;
	}

	public static WindowProvider instance() {
		if (inst == null)
			inst = new WindowProvider();
		return inst;
	}

	public void createWindow() {
		getCurrentWindow();
	}

	public void start() {
		normalWindow.start();
	}

	public void destroyWindow() {
		destroyBgWindow();
		destroyNormalWindow();
	}

	private AbstractWindow getCurrentWindow() {
		if (bgMode == true)
			return getBgWindow();
		return getNormalWindow();
	}

	private AbstractWindow getNormalWindow() {
		if (bgWindow != null) {
			destroyBgWindow();
			bgWindow = null;
		}
		if (normalWindow == null)
			createNormalWindow();
		return normalWindow;
	}

	private void createNormalWindow() {
		normalWindow = new NormalWindow();
		normalWindow.setTitle(title);
		normalWindow.resize(Mode.N_WIDTH, Mode.N_HEIGHT);
		normalWindow.show();
	}

	private void destroyNormalWindow() {
		if (normalWindow != null) {
			normalWindow.hide();
			normalWindow.dispose();
		}
	}

	private void createBgWindow() {
		bgWindow = new BgWindow(BgWindow.createFrame());
		bgWindow.resize(1920, 1080);
		bgWindow.show();
	}

	private void destroyBgWindow() {
		if (bgWindow != null) {
			bgWindow.hide();
			bgWindow.dispose();
		}
	}

	private AbstractWindow getBgWindow() {
		if (normalWindow != null) {
			destroyNormalWindow();
			normalWindow = null;
		}
		if (bgWindow == null)
			createBgWindow();
		return bgWindow;
	}
}

interface AbstractWindow {
	void cvReview(String cp);

	void resizeWindow(int width1, int height1);

	void moveWindow(int x1, int y1);

	void setPath(String path);

	void setCaption(String string);
}

class BgWindowFrame extends Frame {
	public BgWindowFrame(String title) {
		super(title);
	}

	public boolean handleEvent(Event et) {
		return super.handleEvent(et);
	}
}

class BgWindow extends Window implements AbstractWindow {
	private BgWindowFrame frame;

	public BgWindow(BgWindowFrame frame) {
		super(frame);
		this.frame = frame;
	}

	public static BgWindowFrame createFrame() {
		return new BgWindowFrame("");
	}

	public void moveWindow(int x1, int y1) {
	}

	public void resizeWindow(int width1, int height1) {
	}

	public void cvReview(String cp) {
	}

	public void setPath(String path) {
	}

	public void setCaption(String string) {
	}
}

class NormalWindow extends Frame implements AbstractWindow, Runnable {
	TextField tfPath;
	Choice chExt;
	Checkbox chEmbed;
	Label lbPath;
	Label lbPicture;
	Current c1;
	FramePs f1;
	Bandwidth b1;
	RotateAutoBtn autoPanel;
	PicturePanel pp;
	ReviewPieChart round;
	BarChart sq;
	Checkbox chTrans;
	private boolean normalMode;
	private Spiska allViews = new Spiska();
	private int curIdx;
	private int origX = 5;
	private int origY = 5;

	public NormalWindow() {
		super();
		setLayout(new FlowLayout());
		setBackground(Props.bgTopColor);
		add(lbPath = new Label("Pictures Path:"));
		lbPath.setBackground(Color.white);
		add(tfPath = new TextField(100 - 30 + 10 - 40));
		tfPath.setText("C:\\Users\\fantom\\Desktop\\Leaves Days\\first");
		tfPath.setText("C:\\Users\\fantom\\Desktop\\Leaves Days\\second");
		tfPath.setText("C:\\Users\\fantom\\Desktop\\Leaves Days\\third");
		tfPath.setText("C:\\Users\\fantom\\Desktop\\Leaves Days\\forth");
		tfPath.setText("C:\\Users\\fantom\\Desktop\\Leaves Days\\fifth");
		tfPath.setText("C:\\Users\\fantom\\Desktop\\Leaves Days\\sixth");
		tfPath.setText("C:\\Users\\fantom\\Desktop\\Leaves Days\\seventh");
		tfPath.setText("C:\\Users\\fantom\\Desktop\\Leaves Days\\eighth");
		tfPath.setText("C:\\Users\\fantom\\Desktop\\Leaves Days\\nineth");
		tfPath.setText("C:\\Users\\fantom\\Desktop\\Leaves Days\\tenth");
		tfPath.setText("C:\\Users\\fantom\\Desktop\\Leaves Days\\eleventh");
		tfPath.setText("C:\\Users\\fantom\\Desktop\\Leaves Days\\twelveth");
		tfPath.setText("C:\\Users\\fantom\\Desktop\\Leaves Days\\thirteenth");
		tfPath.setText("C:\\Users\\fantom\\Desktop\\Leaves Days\\fourteenth");
		tfPath.setText("C:\\Users\\fantom\\Desktop\\Leaves Days\\fifteenth");
		tfPath.setText("C:\\Users\\fantom\\Desktop\\Leaves Days\\sixteenth");
		tfPath.setText("C:\\Users\\fantom\\Desktop\\Leaves Days\\seventeenth");
		tfPath.setText("C:\\Users\\fantom\\Desktop\\Leaves Days\\eighteenth");
		tfPath.setText("C:\\Users\\fantom\\Desktop\\Leaves Days\\nineteenth");
		tfPath.setText("C:\\Users\\fantom\\Desktop\\Leaves Days\\twenties");
		tfPath.setText("C:\\Users\\fantom\\Desktop\\Leaves Days\\twenty first");
		tfPath.setText("C:\\Users\\fantom\\Desktop\\Leaves Days\\twenty second");
		tfPath.setText("C:\\Users\\fantom\\Desktop\\Leaves Days\\twenty third");
		add(chExt = new Choice());
		chExt.addItem("*.jpg");
		chExt.addItem("*.png");
		chExt.addItem("*.im1");
		chExt.addItem("*.im2");
		if (1 + 1 >= 1 + 1)
			chExt.select("*.im2");
		else
			chExt.select("*.im1");
		add(c1 = new Current("12/34"));
		add(lbPicture = new Label(""));
		lbPicture.setBackground(Color.white);
		lbPicture.setText(Review.pictureName);
		add(autoPanel = new RotateAutoBtn(Props.AUTO_DELAY, !false));
		if (1 + 0 >= 1 + 1)
			add(b1 = new Bandwidth("Bandwidth"));
		add(f1 = new FramePs("Freq"));
		add(chTrans = new Checkbox("trans"));
		chTrans.setState(false);
		round = new ReviewPieChart();
		sq = new BarChart();
		add(pp = new PicturePanel(b1, f1, round, sq));
		allViews = new Spiska();
		add(chEmbed = new Checkbox("Embeded"));
		chEmbed.setState(!true);
	}

	public void cvReview(String cp) {
	}

	public void setPath(String path) {
	}

	public void setCaption(String string) {
	}

	public void moveWindow(int x1, int y1) {
		move(x1, y1);
		origX = x1;
		origY = y1;
	}

	public void resizeWindow(int width1, int height1) {
		resize(width1, height1);
	}

	public boolean handleEvent(Event et) {
		if (et.id == Event.WINDOW_DESTROY)
			WorkingRvHolder.working = false;
		if (et.target == autoPanel.btNext && et.id == Event.ACTION_EVENT)
			next();
		if (et.target == autoPanel.btPrev && et.id == Event.ACTION_EVENT)
			prev();
		if (et.target == autoPanel.btAuto && et.id == Event.ACTION_EVENT)
			auto();
		if (et.target != null && et.target.equals(tfPath)
				&& et.id == Event.LOST_FOCUS)
			start();
		if (et.target != null && et.target.equals(chExt)
				&& et.id == Event.ACTION_EVENT) {
			System.out.println(" " + et);
			stopAuto();
			start();
		}
		if (et.target == chEmbed && et.id == Event.ACTION_EVENT)
			restart();
		if (et.target != null && et.target.equals(autoPanel.tfDelay)
				&& et.id == Event.LOST_FOCUS)
			autoReplace();
		if (et.key == Event.F1 && et.id == Event.KEY_ACTION) {
			if (normalMode)
				bgMode();
			else
				normalMode();
		}
		if (et.key == Event.F2 && et.id == Event.KEY_ACTION)
			auto();
		return super.handleEvent(et);
	}

	private void next() {
		stopAuto();
		if (Review.D)
			System.out.println("next");
		procNext();
	}

	private void prev() {
		stopAuto();
		if (Review.D)
			System.out.println("previous");
		curIdx -= 1;
		if (curIdx < 0)
			curIdx = (int) (allViews.size() - 1);
		Current.update(curIdx, allViews, c1);
		reload();
	}

	private void reload() {
		if (allViews.size() > 0) {
			String cp;
			String cpName = "";
			boolean trans = chTrans.getState();
			cp = (String) allViews.at(curIdx);
			if (cp != null) {
				cpName = new File(cp).getName();
				lbPicture.setText(cpName);
			}
			if (Review.D)
				System.out.println("" + cpName);
			pp.cv.review(cp, trans);
		} else
			pp.cv.clean();
	}

	private void stopAuto() {
		autoPanel.setAutoEnable(false);
	}

	private void procPrevious() {
		curIdx -= 1;
		if (curIdx < 0)
			curIdx = (int) (allViews.size() - 1);
		Current.update(curIdx, allViews, c1);
		reload();
	}

	private void procNext() {
		curIdx += 1;
		if (curIdx > allViews.size() - 1)
			curIdx = 0;
		Current.update(curIdx, allViews, c1);
		reload();
	}

	public void run() {
		Sleeper sleeper = new Sleeper();
		while (autoPanel.isAutoEnable()) {
			if (Review.D)
				System.out.println("1");
			if (autoPanel.isReverse())
				procPrevious();
			else
				procNext();
			if (Review.D)
				System.out.println("2");
			int au = autoPanel.getAutoDelay();
			sleeper.sleep(au);
			if (Review.D)
				System.out.println("3");
		}
	}

	private void auto() {
		if (autoPanel.isAutoEnable() == false) {
			autoPanel.setAutoEnable(true);
			new Thread(this).start();
		} else
			autoPanel.setAutoEnable(false);
	}

	public void restart() {
		if (chEmbed.getState()) {
			autoPanel.tfDelay.setText(Props.AUTO_DELAY_EMBED);
			refreshEmbeded();
		} else {
			autoPanel.tfDelay.setText(Props.AUTO_DELAY);
			refresh();
		}
		curIdx = -1;
		if (allViews.size() > 0)
			curIdx = 0;
		Current.update(curIdx, allViews, c1);
		reload();
	}

	public void start() {
		restart();
		if (autoPanel.isAutoEnable())
			new Thread(this).start();
	}

	protected void refresh() {
		allViews = new Spiska();
		String curPath = tfPath.getText();
		String curExt = chExt.getSelectedItem();
		int iExt = curExt.lastIndexOf('*');
		if (iExt > -1)
			curExt = curExt.substring(iExt + 1);
		Ester[] items = new Filter().endsWithIncensitive(
				new Files().tree(new Ester(curPath)), new Ester(curExt));
		for (int i = 0; i < items.length; i++)
			allViews.append(items[i].toString());
		System.out.println("" + allViews.size());
	}

	protected void refreshEmbeded() {
		allViews = new Spiska();
		long dailyNum = DailyObservationRepo.instance().getDailyNum();
		int idx = (int) (dailyNum - 1);
		AbstractDailyObservation selectDay = DailyObservationRepo.instance()
				.getDaily(idx);
		long itemsLength = selectDay.getPicturesNum();
		for (int i = 0; i < itemsLength; i++)
			allViews.append("day-" + idx + "-picture-" + i);
		System.out.println("" + allViews.size());
	}

	private void autoReplace() {
		int autoDelay = autoPanel.getAutoDelay();
		if (Review.D)
			System.out.println(" " + autoDelay);
	}

	private void normalMode() {
		normalMode = true;
		reshape(origX, origY, Mode.N_WIDTH, Mode.N_HEIGHT);
		pp.cv.soop(Mode.N_WIDTH, Mode.N_HEIGHT);
		System.out.println("norm");
	}

	private void bgMode() {
		normalMode = false;
		reshape(5, 5, 1920, 1080);
		pp.cv.soop(1920, 1080);
		System.out.println("bg");
	}
}

class PicturePanel extends Panel {
	Design cv;
	ProgressChart pt;
	ProgressChart progressVert;
	RightChartPanel rightChartPanel;

	public PicturePanel(Bandwidth b1, FramePs f1, ReviewPieChart round,
			BarChart sq) {
		super();
		setLayout(new BorderLayout());
		setBackground(Color.gray);
		add("Center", cv = new Design(b1, f1));
		Dimension d1 = cv.size();
		add("North", pt = new ProgressChart(false));
		add("West", progressVert = new ProgressChart(true));
		pt.resize(d1.width, 20);
		progressVert.resize(20, d1.height);
		cv.setPalette(pt);
		cv.setVertProgrChart(progressVert);
		cv.setRound(round);
		cv.setSq(sq);
		add("East", rightChartPanel = new RightChartPanel(round, sq));
	}
}

class ColorGroup {
	int color;
	private Spiska colors = new Spiska();
	float pc;
	private int r;
	private int g;
	private int b;
	private float aR;
	private float aG;
	private float aB;
	private int n;

	public ColorGroup(Color c1) {
		super();
		this.color = c1.getRGB();
		this.r = c1.getRed();
		this.g = c1.getGreen();
		this.b = c1.getBlue();
	}

	public long size() {
		return colors.size() + 1;
	}

	public void updatePercent(long totalColors) {
		pc = (float) size() / (float) totalColors * 100.f;
	}

	public void clearAdditionalColors() {
		colors = new Spiska();
		n = 0;
		aR = 0.f;
		aG = 0.f;
		aB = 0.f;
	}

	public Color getColor() {
		Color c1 = null;
		try {
			c1 = new Color((int) aR, (int) aG, (int) aB);
		} catch (Throwable th) {
			System.out.println(" " + aR + " " + aG + " " + aB);
			th.printStackTrace();
		}
		return c1;
	}

	public void addColor(int color) {
		colors.append(new Integer(color));
		Color c1 = new Color(color);
		aR = (aR * n + c1.getRed()) / (n + 1);
		aG = (aG * n + c1.getGreen()) / (n + 1);
		aB = (aB * n + c1.getBlue()) / (n + 1);
		n += 1;
	}

	public double diff(int r2, int g2, int b2) {
		return (r - r2) * (r - r2) + (g - g2) * (g - g2) + (b - b2) * (b - b2);
	}
}

class ColorMap extends Karta {
	public ColorMap() {
		super();
	}

	public void addColorGroup(ColorGroup colorGroup) {
		String colorKey = "" + colorGroup.color;
		put(colorKey, colorGroup);
		recount();
	}

	public void recount() {
		Object[] cgs = (Object[]) values();
		long totalColors = 0;
		for (int i = 0; i < cgs.length; i++)
			totalColors += ((ColorGroup) cgs[i]).size();
		String[] ckeys = (String[]) keys();
		System.out.println(" colors " + totalColors + " groups "
				+ (ckeys.length - 1));
		for (int i = 0; i < ckeys.length; i++) {
			String key = ckeys[i];
			if (key != null) {
				ColorGroup colorGroup = (ColorGroup) get(key);
				colorGroup.updatePercent(totalColors);
				put(key, colorGroup);
			}
		}
	}

	public long size() {
		return values().length;
	}

	public void regColorGroup(Color c1) {
		addColorGroup(new ColorGroup(c1));
	}

	public void clearColors() {
		Object[] cgs = (Object[]) values();
		for (int i = 0; i < cgs.length; i++)
			((ColorGroup) cgs[i]).clearAdditionalColors();
	}

	public void classColorsIf(int[] paletAr) {
		Object[] cgs = (Object[]) values();
		for (int i = 0; i < paletAr.length; i++) {
			int color = paletAr[i];
			Color c1 = new Color(color);
			int r2 = c1.getRed();
			int g2 = c1.getGreen();
			int b2 = c1.getBlue();
			ColorGroup belongsGrp = null;
			double minDt = 256.f * 256.f * 256.f;
			for (int j = 0; j < cgs.length; j++) {
				ColorGroup cg = (ColorGroup) cgs[j];
				double dt = cg.diff(r2, g2, b2);
				if (dt < minDt) {
					minDt = dt;
					belongsGrp = cg;
				}
			}
			belongsGrp.addColor(color);
		}
	}

	public void dumpPercents() {
		float sum = 0.f;
		Object[] cgs = (Object[]) values();
		for (int i = 0; i < cgs.length; i++) {
			sum += ((ColorGroup) cgs[i]).pc;
			if (i > 0)
				System.out.print(", ");
			System.out.print(percent2F(((ColorGroup) cgs[i]).pc) + "%");
		}
		System.out.println(" total=" + sum + "%");
	}

	String percent2F(float percent) {
		int d = (int) (percent * 100);
		String result = "" + ((float) d / 100.f);
		int dt1 = result.lastIndexOf('.');
		int dt2 = result.length() - 1;
		return dt2 - dt1 < 2 ? result + "0" : result;
	}

	public void loadPalette(int[] paletArr) {
		System.out.print(" palette size " + paletArr.length);
		clearColors();
		AverageRec classTimeIf = new AverageRec();
		classTimeIf.start();
		classColorsIf(paletArr);
		classTimeIf.trust();
		System.out.print(", color diff in " + classTimeIf.getAverageTime()
				+ "s");
		AverageRec recountTime = new AverageRec();
		recountTime.start();
		recount();
		recountTime.trust();
		System.out.println(", recount complete in "
				+ recountTime.getAverageTime() + "s");
		dumpPercents();
	}
}

class Design extends CommonCanvas implements ImageObserver, Runnable {
	Karta proxy = new Karta();
	Image curImage;
	private Date dLast;
	private Bandwidth b1;
	private FramePs f1;
	private long total;
	private long num;
	private ProgressChart pt;
	private ProgressChart progressVert;
	private ReviewPieChart round;
	private BarChart sq;
	ColorMap colorMap = new ColorMap();
	ImagesConveer conv;
	String picturePath;
	String ext;
	boolean trans;

	public Design(Bandwidth b1, FramePs f1) {
		super();
		setBackground(Color.green);
		setBorderColor(Color.yellow);
		this.b1 = b1;
		this.f1 = f1;
		initColors();
		soop(Mode.N_WIDTH, Mode.N_HEIGHT);
	}

	public void setVertProgrChart(ProgressChart progressVert) {
		this.progressVert = progressVert;
	}

	public void setSq(BarChart sq) {
		this.sq = sq;
	}

	public void setRound(ReviewPieChart round) {
		this.round = round;
	}

	public void setPalette(ProgressChart pt) {
		this.pt = pt;
	}

	public void clean() {
		curImage = null;
		refreshUpdatable();
	}

	public void soop(int w, int h) {
		resize(w - 100 + 50 + 20 - 10, h - 30 - 40 - 50 + 20);
	}

	private void initColors() {
		colorMap = new ColorMap();
		boolean full = false;
		colorMap.regColorGroup(Color.green);
		colorMap.regColorGroup(Color.blue);
		colorMap.regColorGroup(Color.white);
		if (full) {
			colorMap.regColorGroup(Color.black);
			colorMap.regColorGroup(Color.yellow);
			colorMap.regColorGroup(Color.gray);
			colorMap.regColorGroup(Color.darkGray);
			colorMap.regColorGroup(Color.red);
			colorMap.regColorGroup(Color.pink);
			colorMap.regColorGroup(Color.orange);
			colorMap.regColorGroup(Color.lightGray);
			colorMap.regColorGroup(Color.magenta);
			colorMap.regColorGroup(Color.cyan);
		}
	}

	public void run() {
		Image image2 = reloadBackupImage2(picturePath, ext, trans);
		conv.regResult(image2);
	}

	protected Image reloadBackupImage(String picturePath, String ext,
			boolean trans) {
		this.picturePath = picturePath;
		this.ext = ext;
		this.trans = trans;
		if (conv == null) {
			conv = new ImagesConveer(this);
		}
		while (conv.hasNext() == false)
			conv.improve();
		Image image2 = conv.next();
		return image2;
	}

	int curEmbeded;

	private Image reloadEmbededImage(String path, boolean trans) {
		if (Review.D)
			System.out.println("EMBED " + path + " trans=" + trans);
		long pictureIdx = new Ester(path).index(new Ester("picture-"));
		Ester idxDay = new Ester(path).sub(new String("day-").length(),
				pictureIdx);
		idxDay = new Data().replace(idxDay, new Ester("-"), new Ester(""));
		int dayIdx = Integer.parseInt(idxDay.toString());
		Ester idxPict = new Ester(path).sub(new String("picture-").length()
				+ pictureIdx);
		int colorIdx = Integer.parseInt(idxPict.toString());
		AbstractDailyObservation observationDay = DailyObservationRepo
				.instance().getDaily(dayIdx);
		if (curEmbeded > observationDay.getPicturesNum() - 1)
			curEmbeded = 0;
		Image img1 = observationDay.getImage(curEmbeded, this);
		applyPalette(observationDay.getImagePalette(curEmbeded));
		curEmbeded += 1;
		return img1;
	}

	protected Image reloadBackupImage2(String picturePath, String ext,
			boolean trans) {
		ImageReviewFile file = null;
		Image image = null;
		try {
			if (ext.endsWith(".im1"))
				file = ImageReviewFile.loadImage(picturePath);
			else if (ext.endsWith(".im2")) {
				file = ImageReviewFile.loadImage2(picturePath, trans);
				applyPalette(file.paletArr);
			}
			int[] dataRef = file.getData();
			short width1 = (short) file.getWidth();
			short heigth1 = (short) file.getHeight();
			CommonPalette.instance().checkPalette(picturePath, dataRef, width1,
					heigth1);
			System.out.println(" dataSize=" + dataRef.length + " ext=" + ext
					+ " paletteSize="
					+ (file.paletArr != null ? file.paletArr.length : 0)
					+ " commonPaletteSize="
					+ CommonPalette.instance().curPaletteColorsNum()
					+ " allPicturesPixelsNum="
					+ CommonPalette.instance().curAllPicturesPixelsNum());
		} catch (ImageReviewFileException e) {
			e.printStackTrace();
		}
		if (file != null)
			image = createImage(file.getWidth(), file.getHeight(),
					file.getData());
		return image;
	}

	private void applyPalette(int[] paletArr) {
		colorMap.loadPalette(paletArr);
		pt.updateImage(colorMap);
		progressVert.updateImage(colorMap);
		round.updateImage(colorMap);
		sq.updateImage(colorMap);
	}

	private Image createImage(int width, int height, int[] pix) {
		int bits = 32;
		int redMask = 16711680;
		int greenMask = 65280;
		int blueMask = 255;
		DirectColorModel cm = new DirectColorModel(bits, redMask, greenMask,
				blueMask);
		Image image = createImage(new MemoryImageSource(width, height, cm, pix,
				0, width));
		return image;
	}

	public void review(String path, boolean trans) {
		if (path != null && path.length() > 0) {
			if (proxy.containsKey(path))
				curImage = (Image) proxy.get(path);
			else
				getImage(path, trans);
			updateBandwidth(path);
		}
		refreshUpdatable();
	}

	private void getImage(String path, boolean trans) {
		if (path.endsWith(".im1"))
			curImage = reloadBackupImage(path, ".im1", trans);
		else if (path.endsWith(".im2"))
			curImage = reloadBackupImage(path, ".im2", trans);
		else if (path.endsWith(".jpg"))
			curImage = Toolkit.getDefaultToolkit().getImage(path);
		else if (path.endsWith(".png"))
			curImage = Toolkit.getDefaultToolkit().getImage(path);
		else if (path.endsWith(".gif"))
			curImage = Toolkit.getDefaultToolkit().getImage(path);
		else
			curImage = reloadEmbededImage(path, trans);
	}

	protected void updateBandwidth(String path) {
		long size = new File(path).length();
		total += size;
		num += 1;
		if (f1 != null)
			f1.step();
		if (dLast == null)
			dLast = new Date();
		Date dCur = new Date();
		long dt = dCur.getTime() - dLast.getTime();
		if (dt > 5 * 997) {
			dLast = dCur;
			if (b1 != null)
				Bandwidth.update2(total, dt, num, b1);
			if (f1 != null)
				f1.publish(dt, 997).restart();
			total = 0;
			num = 0;
		}
	}

	// Dimension last = null;

	// public void clearItems() {
	// }
	//
	// protected void drawItems(Graphics ics) {
	// }

	// public void paint(Graphics ics) {
	// super.paint(ics);
	// Dimension d1 = size();
	// if (last != null)
	// if (notEq(d1, last)) {
	// Dimension ptSize = pt.size();
	// pt.setDimensions(d1.width, ptSize.height);
	// }
	// drawBorder(ics);
	// if (curImage != null)
	// ics.drawImage(curImage, 1, 1, d1.width - 1 - 1, d1.height - 1 - 1,
	// this);
	// }

	// @Override
	public void clearItems() {
		// TODO Auto-generated method stub

	}

	// @Override
	protected void drawItems(Canvas ics, Paint pn) {
		// TODO Auto-generated method stub
		if (curImage != null)
			ics.drawImage(curImage, 1, 1, sCur.width - 1 - 1,
					sCur.height - 1 - 1, this);

	}

	public boolean imageUpdate(Image img, int infoflags, int x, int y,
			int width, int height) {
		if (Review.D)
			System.out.println(" " + infoflags + " " + x + " " + y + " "
					+ width + " " + height);
		if (infoflags == ImageObserver.ALLBITS) {
			refreshUpdatable();
			return false;
		}
		return true;
	}
}

interface Mode {
	int N_WIDTH = 640;
	int N_HEIGHT = 480;
}

class ImageItem {
	private String fullPath;
	private Image image;

	public ImageItem(String fullPath, Image image) {
		super();
		this.fullPath = fullPath;
		this.image = image;
	}

	public String getFullPath() {
		return fullPath;
	}

	public Image getImage() {
		return image;
	}

	public String getName() {
		return new File(fullPath).getName();
	}
}

class ImageProxy {
	private int curIdx;
	private ImageProcessor proc;
	private Integer syncObj = new Integer(1);

	public ImageProxy() {
		super();
		this.proc = new ImageProcessor();
	}

	public ImageObserver getImageObserver() {
		return proc;
	}

	public void loadImage(String fullPath) {
		proc.pushLoading(fullPath);
	}

	public long getImageNum() {
		synchronized (syncObj) {
			return proc.getImageNum();
		}
	}

	public synchronized ImageItem getImage(int idx) {
		synchronized (syncObj) {
			if (idx < 0)
				return null;
			if (idx >= proc.getImageNum())
				return null;
			if (proc.getImageNum() < 1)
				return null;
			return proc.getImage(idx).getImageRes();
		}
	}

	public ImageItem nextImage() {
		synchronized (syncObj) {
			curIdx += 1;
			if (curIdx >= proc.getImageNum())
				curIdx = 0;
			if (proc.getImageNum() < 1)
				return null;
			return getImage(curIdx);
		}
	}

	public ImageItem prevImage() {
		synchronized (syncObj) {
			curIdx -= 1;
			if (curIdx < 0)
				curIdx = (int) proc.getImageNum() - 1;
			if (proc.getImageNum() < 1)
				return null;
			return getImage(curIdx);
		}
	}

	public void cancelLoading() {
		proc.cancelLoading();
	}

	public void clearAll() {
		cancelLoading();
		proc.clearAll();
	}

	public ImageItem getImage(String path) {
		System.out.println(" ready " + proc.getImageNum());
		return proc.getImage(0).getImageRes();
	}
}

class ImageWrap {
	String path;
	Image ref;
	boolean ready;

	public ImageWrap(String path) {
		super();
		this.path = path;
		this.ready = false;
	}

	public boolean isReady() {
		return ready;
	}

	public ImageItem getImageRes() {
		return new ImageItem(path, ref);
	}

	public Image getRef() {
		return ref;
	}

	public String getPath() {
		return path;
	}

	public void setRef(Image ref) {
		this.ref = ref;
	}

	public String getKey() {
		return ImageWrap.getKey(ref);
	}

	public static String getKey(Image imgRef) {
		if (imgRef == null)
			return "<null>";
		return "<" + imgRef.hashCode() + ">";
	}

	public void setReady() {
		this.ready = true;
	}
}

class ImageProcessor implements ImageObserver, Runnable {
	private Spiska readyImages;
	private Stachko orders;
	private Karta waitingMap;
	private Thread worker1;
	private boolean loading;

	ImageProcessor() {
		super();
		clearAll();
	}

	public ImageWrap getImage(int idx) {
		return (ImageWrap) readyImages.at(idx);
	}

	public long getImageNum() {
		return readyImages.size();
	}

	public void run() {
		loading = true;
		while (loading) {
			ImageWrap wrap = null;
			synchronized (orders) {
				if (orders.has())
					wrap = (ImageWrap) orders.pop();
				else
					loading = false;
			}
			if (wrap == null)
				continue;
			System.out.println("getImage() " + wrap.getPath());
			wrap.setRef(Toolkit.getDefaultToolkit().getImage(wrap.getPath()));
			synchronized (waitingMap) {
				waitingMap.put(wrap.getKey(), wrap);
			}
			System.out.println("putImage() ");
		}
		worker1 = null;
	}

	void clearAll() {
		this.loading = false;
		this.orders = new Stachko();
		this.waitingMap = new Karta();
		this.readyImages = new Spiska();
		this.worker1 = null;
	}

	void cancelLoading() {
		loading = false;
		worker1 = null;
		orders = new Stachko();
	}

	void pushLoading(String fullPath) {
		synchronized (orders) {
			orders.push(new ImageWrap(fullPath));
			if (worker1 == null) {
				worker1 = new Thread(this);
				worker1.start();
			}
		}
	}

	public boolean imageUpdate(Image img, int infoflags, int x, int y,
			int width, int height) {
		System.out.println("imageUpdate() " + img);
		if (infoflags == ImageObserver.ALLBITS) {
			ImageWrap imageWrap = null;
			synchronized (waitingMap) {
				imageWrap = (ImageWrap) waitingMap.get(ImageWrap.getKey(img));
			}
			imageWrap.setReady();
			synchronized (readyImages) {
				readyImages.append(imageWrap);
			}
			return false;
		}
		return true;
	}
}

class Current extends Label {
	public Current(String text) {
		super(text);
		setBackground(Props.bgTopColor);
	}

	public void paint(Graphics ics) {
		super.paint(ics);
		Dimension d1 = size();
		ics.setColor(Color.blue);
		ics.drawRect(0, 0, d1.width - 1, d1.height - 1);
	}

	public static void update(int curIdx, Spiska allViews, Current c1) {
		String s1 = "" + (curIdx + 1) + "/" + allViews.size();
		c1.setText(s1);
	}
}

class FramePs extends Current {
	private int steps;

	public FramePs(String text) {
		super(text);
	}

	public void step() {
		++steps;
	}

	public void restart() {
		steps -= steps;
	}

	public FramePs publish(long time, int secVal) {
		String s1 = "" + (steps / (time / secVal)) + " fps";
		setText(s1);
		if (Review.D)
			System.out.println("bbb" + s1);
		return this;
	}
}

class Bandwidth extends Current {
	public Bandwidth(String text) {
		super(text);
	}

	public static void update2(long sizeSee, long time, long num, Bandwidth b1) {
		String s1 = "" + new Metr(sizeSee / 1024 / (time / 997)).repr(',')
				+ " Kb/s " + (num / (time / 997)) + " item/s";
		b1.setText(s1);
		if (Review.D)
			System.out.println("aaa" + s1);
	}
}

class ImageReviewFile {
	protected static final int BUF_LIM = 256;
	private String fullPath;
	private int width;
	private int height;
	private int pix[];
	int[] paletArr;

	ImageReviewFile(String fullPath) {
		super();
		this.fullPath = fullPath;
	}

	public void check() {
		for (int i = 0; i < paletArr.length; i++)
			paletArr[i] = i < paletArr.length / 10 ? Color.white.getRGB()
					: Color.blue.getRGB();
	}

	public int[] getData() {
		return pix;
	}

	public String getName() {
		return new File(fullPath).getName();
	}

	public String getFullPath() {
		return fullPath;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private static int read4b(FileInputStream is) throws IOException {
		int s = 0;
		int r1 = is.read();
		int r2 = is.read();
		int r3 = is.read();
		int r4 = is.read();
		s = ((r4 * 255 + r3) * 255 + r2) * 255 + r1;
		return s;
	}

	private static void readIntBuf(FileInputStream is, int[] intBuf)
			throws IOException {
		SharedGreatesArray sga = SharedGreatesArray.createByteArray(0);
		int lim = intBuf.length * 4;
		while (lim > 0) {
			byte[] b1 = new byte[lim];
			int count = is.read(b1);
			if (count > 0) {
				sga.append(b1);
				lim -= count;
			}
		}
		byte[] buf = sga.getMemoryViewByte();
		for (int i = 0; i < intBuf.length; i++) {
			int s = 0;
			int r1 = buf[i * 4 + 1 - 1];
			int r2 = buf[i * 4 + 1];
			int r3 = buf[i * 4 + 2];
			int r4 = buf[i * 4 + 3];
			s = ((r4 * 255 + r3) * 255 + r2) * 255 + r1;
			intBuf[i] = s;
		}
	}

	public static ImageReviewFile loadImage(String fullPath)
			throws ImageReviewFileException {
		try {
			ImageReviewFile imageFile = new ImageReviewFile(fullPath);
			File file = new File(fullPath);
			FileInputStream is = new FileInputStream(file);
			imageFile.width = read4b(is);
			imageFile.height = read4b(is);
			System.out.print(" WH " + imageFile.width + "x" + imageFile.height);
			imageFile.pix = new int[imageFile.width * imageFile.height];
			ParserReviewAdapter parser = new ParserReviewAdapter(imageFile.pix);
			byte[] buf = new byte[4 * BUF_LIM];
			int count;
			do {
				count = is.read(buf);
				if (count > 0)
					parser.push(buf, count);
			} while (count >= 0);
			is.close();
			return imageFile;
		} catch (IOException ex) {
			throw new ImageReviewFileException("Can't load. " + ex.getMessage());
		}
	}

	public static ImageReviewFile loadImage2(String fullPath, boolean trans)
			throws ImageReviewFileException {
		try {
			ImageReviewFile imageFile = new ImageReviewFile(fullPath);
			File file = new File(fullPath);
			FileInputStream is = new FileInputStream(file);
			imageFile.width = read4b(is);
			imageFile.height = read4b(is);
			System.out
					.print(" WH2 " + imageFile.width + "x" + imageFile.height);
			imageFile.pix = new int[imageFile.width * imageFile.height];
			int paletArrLn = read4b(is);
			imageFile.paletArr = new int[paletArrLn];
			readIntBuf(is, imageFile.paletArr);
			if (trans) {
				HelloWorld hw = new HelloWorld();
				for (int i = 0; i < paletArrLn; i++) {
					int c1 = imageFile.paletArr[i];
					if (hw.isIt(c1))
						imageFile.paletArr[i] = Color.green.getRGB();
				}
			}
			if (Review.D)
				System.out.println(" palette size is " + paletArrLn);
			ParserWithPaletteReviewAdapter parser = new ParserWithPaletteReviewAdapter(
					imageFile.pix, imageFile.paletArr);
			byte[] buf = new byte[4 * BUF_LIM];
			int count;
			do {
				count = is.read(buf);
				if (count > 0)
					parser.push(buf, count);
			} while (count >= 0);
			is.close();
			return imageFile;
		} catch (IOException ex) {
			throw new ImageReviewFileException("Can't load. " + ex.getMessage());
		}
	}
}

class ImageReviewFileException extends Exception {
	public ImageReviewFileException() {
		super();
	}

	public ImageReviewFileException(String message) {
		super(message);
	}
}

class ParserWithPaletteReviewAdapter extends ParserReviewAdapter {
	int[] paletArr;

	public ParserWithPaletteReviewAdapter(int[] pix, int[] paletArr) {
		super(pix);
		this.paletArr = paletArr;
	}

	public void push(int b) {
		switch (n) {
		case 0:
			r1 = b;
			break;
		case 1:
			r2 = b;
			break;
		case 2:
			r3 = b;
			break;
		default:
			r4 = b;
			break;
		}
		n++;
		if (n == 4) {
			n = 0;
			if (r1 < 0)
				r1 += 256;
			if (r2 < 0)
				r2 += 256;
			if (r3 < 0)
				r3 += 256;
			if (r4 < 0)
				r4 += 256;
			if (r4 == 0) {
				s = (r3 * 255 + r2) * 255 + r1;
				pix[idx++] = paletArr[s];
			} else {
				s = (r3 * 255 + r2) * 255 + r1;
				for (int i = 0; i <= r4; i++)
					pix[idx++] = paletArr[s];
			}
		}
	}
}

class ParserReviewAdapter {
	int s;
	int r1;
	int r2;
	int r3;
	int r4;
	int n = 0;
	int idx = 0;
	int[] pix;

	public ParserReviewAdapter(int[] pix) {
		this.pix = pix;
	}

	public void push(int b) {
		switch (n) {
		case 0:
			r1 = b;
			break;
		case 1:
			r2 = b;
			break;
		case 2:
			r3 = b;
			break;
		default:
			r4 = b;
			break;
		}
		n++;
		if (n == 4) {
			n = 0;
			if (r1 < 0)
				r1 += 256;
			if (r2 < 0)
				r2 += 256;
			if (r3 < 0)
				r3 += 256;
			if (r4 < 0)
				r4 += 256;
			if (r4 == 0) {
				pix[idx++] = new Color(r1, r2, r3).getRGB();
			} else {
				s = new Color(r1, r2, r3).getRGB();
				for (int i = 0; i <= r4; i++)
					pix[idx++] = s;
			}
		}
	}

	public void push(byte[] buf, int count) {
		for (int j = 0; j < count; j++)
			push(buf[j]);
	}
}

class WorkingRvHolder {
	static boolean working = true;
}

class RotateAutoBtn extends Panel {
	Checkbox chRev;
	Button btNext;
	Button btPrev;
	TextField tfDelay;
	Checkbox chAuto;
	Button btAuto;

	public RotateAutoBtn(String autoDelay, boolean initialState) {
		super();
		setLayout(new FlowLayout(FlowLayout.LEFT, 2 + 1, 2));
		setBackground(Color.yellow);
		add(btNext = new Button("Next"));
		add(btPrev = new Button("Prev"));
		add(tfDelay = new TextField(4));
		tfDelay.setText("" + autoDelay);
		add(chAuto = new Checkbox(""));
		chAuto.setState(initialState);
		chAuto.enable(false);
		add(btAuto = new Button("Auto"));
		add(chRev = new Checkbox("Reverse"));
		chRev.setState(!true);
	}

	public boolean isReverse() {
		return chRev.getState();
	}

	public int getAutoDelay() {
		String delayText = tfDelay.getText();
		if (delayText.length() < 1)
			delayText = Props.AUTO_DELAY;
		return Integer.parseInt(delayText);
	}

	public void setAutoEnable(boolean ae) {
		chAuto.setState(ae);
	}

	public boolean isAutoEnable() {
		return chAuto.getState();
	}
}

class Props {
	public static final Color objRibs = new Color(248, 186, 111);
	public static final String AUTO_DELAY = "20";
	public static final String AUTO_DELAY_EMBED = "105";
	public static final Color bgTopColor = objRibs;
	public static final Color northBorderColor = Color.cyan;
	public static final Color northTextColor = northBorderColor;
	public static final Color northBgColor = bgTopColor;
	public static final Color bgChartColor = Color.green;
	public static final Color borderChartColor = Color.yellow;
}

class Plant {
	final int minD = -5;

	public boolean isIt(int c1) {
		Color c2 = new Color(c1);
		int r = c2.getRed();
		int g = c2.getGreen();
		int b = c2.getBlue();
		if (g - r >= minD && g - b >= minD)
			return true;
		return false;
	}
}

class HelloWorld {
	final int mis = 90 + 50 - 5 - 5 - 5 - 5 - 5 - 5 - 5 - 5;
	final int minV = 25 + mis;
	final int maxD1 = 20 + mis;
	final int maxD2 = 20 + mis;

	public boolean isIt(int c1) {
		Color c2 = new Color(c1);
		int r = c2.getRed();
		int g = c2.getGreen();
		int b = c2.getBlue();
		int e = r > g ? r - g : g - r;
		int t = g > b ? g - b : b - g;
		if (e <= maxD1 && t <= maxD2 && r >= minV && g >= minV && b >= minV)
			return true;
		return false;
	}
}

interface ColorChart {
	void updateImage(ColorMap colorMap);
}

class BarChart extends ReviewPieChart {
	public BarChart() {
		super();
	}
}

class ReviewPieChart extends CommonCanvas implements ColorChart {
	protected static int defStartAngle = (int) (System.currentTimeMillis() % 166 + new Date()
			.getTime() % 90);
	protected Spiska drawItems = new Spiska();
	protected Point2D center;
	protected int[] colors = new ColorsArray().getDefColors();
	protected int r;
	protected long total;
	protected float curPos = defStartAngle;
	private boolean recounted;
	ColorMap colorMap;

	public ReviewPieChart() {
		super(150, 150, Props.bgChartColor, Props.borderChartColor);
	}

	public void clearItems() {
		this.drawItems = new Spiska();
		total = 0L;
		recounted = false;
	}

	// @Override
	protected void drawItems(Canvas ics, Paint pn) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// protected void drawItems(Graphics ics) {
		if (center == null)
			center = new Point2D(getCcHalfWidth(), getCcHalfHeight(),
					bgColor.getRGB());
		else
			center.moveTo(getCcHalfWidth(), getCcHalfHeight());
		if (!recounted)
			recountTotal();
		// Paint pn = new Paint();
		// Canvas canvas = new Canvas(ics);
		for (int i = 0; i < drawItems.size(); i++) {
			Colorfull cf = (Colorfull) drawItems.at(i);
			cf.toPen(ics, pn.select(cf));
		}
	}

	public void refreshUpdatable() {
		recounted = false;
		super.refreshUpdatable();
	}

	public void markRecountComplete() {
		recounted = true;
	}

	public void setStartAngle(int start) {
		defStartAngle = start;
	}

	protected float fillPercents(int color, float pc, float startAngle,
			Spiska newItems) {
		float finishAngle = startAngle + pc * 360f / 100.f;
		Sector2D arcItem = new Sector2D(center, r, color, startAngle,
				finishAngle - startAngle, true);
		newItems.append(arcItem);
		return finishAngle;
	}

	public void recountTotal() {
		if (getCcWidth() < 1)
			return;
		if (colorMap == null)
			return;
		String[] colorKeys = colorMap.keys();
		Spiska newItems = new Spiska();
		r = (getCcWidth() - 2) / 2;
		for (int i = 0; i < colorKeys.length; i++)
			if (colorKeys[i] != null) {
				ColorGroup cg = (ColorGroup) colorMap.get(colorKeys[i]);
				curPos = fillPercents(cg.getColor().getRGB(), cg.pc, curPos,
						newItems);
			}
		newItems.append(new Circle2D(center, r, Color.black.getRGB()));
		r /= 2;
		curPos = defStartAngle;
		for (int i = 0; i < colorKeys.length; i++)
			if (colorKeys[i] != null) {
				ColorGroup cg = (ColorGroup) colorMap.get(colorKeys[i]);
				curPos = fillPercents(cg.color, cg.pc, curPos, newItems);
			}
		this.drawItems = newItems;
	}

	public void updateImage(ColorMap colorMap) {
		this.colorMap = colorMap;
		recountTotal();
		refreshUpdatable();
	}
}

class ProgressChart extends CommonCanvas implements ImageObserver, ColorChart {
	private Image curImage;
	// private Dimension d1;
	private boolean vert;

	public ProgressChart(boolean vert) {
		super();
		setBackground(Color.green);
		setBorderColor(Color.yellow);
		this.vert = vert;
	}

	public void updateImage(ColorMap colorMap) {
		if (sCur == null)
			return;
		String[] colorKeys = colorMap.keys();
		int w = sCur.width - 2;
		int h = sCur.height - 2;
		int[] pix = new int[w * h];
		float curPos = 0.f;
		for (int i = 0; i < colorKeys.length; i++) {
			if (colorKeys[i] != null) {
				ColorGroup cg = ((ColorGroup) colorMap.get(colorKeys[i]));
				curPos = fillPercents(pix, w, h, cg.color, cg.getColor()
						.getRGB(), cg.pc, curPos);
			}
		}
		int bits = 32;
		int redMask = 16711680;
		int greenMask = 65280;
		int blueMask = 255;
		DirectColorModel cm = new DirectColorModel(bits, redMask, greenMask,
				blueMask);
		curImage = createImage(new MemoryImageSource(w, h, cm, pix, 0, w));
		invalidate();
		repaint();
	}

	private float fillPercents(int[] pix, int w, int h, int color,
			int avgColor, float pc, float start) {
		float finish = start + pc;
		if (vert)
			for (int y = 0; y < h; y++) {
				float cpc = (float) y * 100.f / (float) h;
				if (cpc >= start && cpc < finish)
					for (int x = 0; x < w; x++)
						pix[y * w + x] = x < w / 2 ? color : avgColor;
			}
		else
			for (int x = 0; x < w; x++) {
				float cpc = (float) x * 100.f / (float) w;
				if (cpc >= start && cpc < finish)
					for (int y = 0; y < h; y++)
						pix[y * w + x] = y < h / 2 ? color : avgColor;
			}
		return finish;
	}

	public void setDimensions(int width, int height) {
		resize(width, height);
	}

	// public void paint(Graphics ics) {
	// super.paint(ics);
	// d1 = size();
	// drawBorder(ics);
	// if (curImage != null)
	// ics.drawImage(curImage, 1, 1, d1.width - 1 - 1, d1.height - 1 - 1,
	// this);
	// }

	// @Override
	public void clearItems() {
		// TODO Auto-generated method stub

	}

	// @Override
	protected void drawItems(Canvas ics, Paint pn) {
		// TODO Auto-generated method stub
		if (curImage != null)
			ics.drawImage(curImage, 1, 1, sCur.width - 1 - 1,
					sCur.height - 1 - 1, this);

	}

	// public void clearItems() {
	// }
	//
	// protected void drawItems(Graphics ics) {
	// }
}

class RightChartPanel extends Panel {
	ReviewPieChart round;
	BarChart sq;

	public RightChartPanel(ReviewPieChart round, BarChart sq) {
		super();
		setBackground(Color.cyan);
		setLayout(new BorderLayout(2, 2));
		add("North", round);
		add("Center", sq);
		this.round = round;
		this.sq = sq;
	}
}

class ImagesConveer {
	Runnable loader;
	boolean started;
	Spiska resReady = new Spiska();
	Object sync = new Integer(1);
	protected short minWorkers;
	protected short maxWorkers;

	public ImagesConveer(Runnable loader) {
		this(loader, (short) 2, (short) 9);
		this.loader = loader;
	}

	public ImagesConveer(Runnable loader, short minWorkers, short maxWorkers) {
		super();
		this.loader = loader;
		this.minWorkers = minWorkers;
		this.maxWorkers = maxWorkers;
	}

	public void regResult(Image image2) {
		synchronized (sync) {
			resReady.append(image2);
		}
		this.started = false;
	}

	public Image next() {
		Image image2 = null;
		synchronized (sync) {
			image2 = (Image) resReady.at(0);
			resReady.remove(image2);
		}
		return image2;
	}

	public boolean hasNext() {
		boolean has = false;
		synchronized (sync) {
			has = resReady.size() > 0;
		}
		return has;
	}

	public void improve() {
		if (started == false) {
			started = true;
			new Thread(loader).start();
		}
		new Sleeper().sleep(5 / 4);
	}
}

class CommonPalette {
	private Hashtable colorSet;
	private Hashtable images;
	private long commonPixelsNum;
	private short width;
	private short height;
	private short repeatCount;
	private static CommonPalette inst;

	private CommonPalette() {
		super();
		this.colorSet = new Hashtable();
		this.images = new Hashtable();
		this.commonPixelsNum = 0L;
	}

	public static CommonPalette instance() {
		if (inst == null)
			inst = new CommonPalette();
		return inst;
	}

	public void clearCurresnt() {
		colorSet.clear();
	}

	public void checkPalette(String picturePath, int[] dataRef, short width,
			short height) {
		if (dataRef != null && picturePath != null) {
			if (images.containsKey(picturePath)) {
				if (repeatCount != -1)
					repeatCount++;
			} else {
				images.put(picturePath, dataRef);
				commonPixelsNum += dataRef.length;
				this.width = width;
				this.height = height;
				for (int i = 0; i < dataRef.length; i++) {
					int colorItem = dataRef[i];
					colorSet.put(new Integer(colorItem), "" + colorItem);
				}
			}
			if (repeatCount == 1000) {
				repeatCount = -1;
				String fileName = new File(picturePath).getName();
				String folderName = new Data().replace(new Ester(picturePath),
						new Ester(fileName), new Ester("")).toString();
				System.out.println(" folderName=" + folderName);
				String[] pathParts = new PathSplitter(folderName, "/\\")
						.getParts();
				String nameCls = "First";
				if (pathParts != null && pathParts.length > 0)
					nameCls = pathParts[pathParts.length - 1];
				nameCls = new UpFirstDailyObservationName(nameCls).getName();
				if (1 + 1 > 1 + 1)
					picturesExportWrite(nameCls);
			}
		}
	}

	private void picturesExportWrite(String nameCls) {
		System.out.println("Write casette" + " - " + nameCls);
		try {
			int paletteSize = colorSet.size();
			Enumeration itColor = colorSet.keys();
			int[] paletteArr = new int[paletteSize];
			int i = 0;
			while (itColor.hasMoreElements())
				paletteArr[i++] = ((Integer) itColor.nextElement()).intValue();
			FileOutputStream fos = new FileOutputStream("C:\\Photo2\\"
					+ nameCls + ".java");
			writeStr(fos, new String("package mydev.cndata;\r\n\r\n"));
			writeStr(fos, new String("import java.awt.Color;\r\n\r\n"));
			writeStr(fos, new String("import mydev.vutils.Ester;\r\n\r\n"));
			writeStr(fos, new String("class " + nameCls
					+ " extends AbstractDailyObservation {\r\n\r\n"));
			writeStr(fos, new String("  " + nameCls + "() {\r\n"));
			writeStr(fos, new String("    colors = new int[" + paletteSize
					+ "];\r\n"));
			writeStr(fos, new String("    idx = 0;\r\n"));
			writeStr(fos, new String("    createPalette();\r\n"));
			writeStr(fos, new String("    createPicture((short)" + width
					+ ", (short)" + height + ");\r\n"));
			writeStr(fos, new String(
					"    DailyObservationRepo.instance().addDaily(this);\r\n"));
			writeStr(fos, new String("  }\r\n\r\n"));
			writeStr(fos, new String("  void q4(String colorHex6) {\r\n"));
			writeStr(fos, new String(
					"    colors[idx++] = recovery(colorHex6);\r\n"));
			writeStr(fos, new String("  }\r\n\r\n"));
			writeStr(fos, new String("  int recovery(String colorHex6) {\r\n"));
			writeStr(fos, new String("    return new Color(\r\n"));
			writeStr(
					fos,
					new String(
							"      getHexInt(new Ester(colorHex6).sub(0, 2).toString()),\r\n"));
			writeStr(
					fos,
					new String(
							"      getHexInt(new Ester(colorHex6).sub(2, 4).toString()),\r\n"));
			writeStr(
					fos,
					new String(
							"      getHexInt(new Ester(colorHex6).sub(4, 6).toString())\r\n"));
			writeStr(fos, new String("    ).getRGB();\r\n"));
			writeStr(fos, new String("  }\r\n\r\n"));
			int cpNum = 1;
			i = 0;
			while (i < paletteSize) {
				if (i - i / 300 * 300 == 0) {
					if (i > 0) {
						writeStr(fos, new String("    createPalette"
								+ (1 + cpNum++) + "();\r\n"));
						writeStr(fos, new String("  }\r\n\r\n"));
					}
					writeStr(fos, new String("  void createPalette" + cpNum
							+ "() {\r\n"));
				}
				if (i + 11 < paletteSize) {
					Ester line2 = new Ester("q412(");
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++));
					line2.append(");\r\n");
					writeStr(fos, line2.toString());
				} else if (i + 9 < paletteSize) {
					Ester line2 = new Ester("q410(");
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++));
					line2.append(");\r\n");
					writeStr(fos, line2.toString());
				} else if (i + 7 < paletteSize) {
					Ester line2 = new Ester("q48(");
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++)).append(',');
					line2.append(colorLine(paletteArr, i++));
					line2.append(");\r\n");
					writeStr(fos, line2.toString());
				} else {
					Ester line2 = new Ester("q4(");
					line2.append(colorLine(paletteArr, i++));
					line2.append(");\r\n");
					writeStr(fos, line2.toString());
				}
			}
			writeStr(fos, new String("  }\r\n\r\n"));
			cpNum = 1;
			Enumeration itPictures = images.elements();
			while (itPictures.hasMoreElements()) {
				int[] dataRef = (int[]) itPictures.nextElement();
				writeStr(fos, new String("  void createPicture" + cpNum++
						+ "() {\r\n"));
				writeStr(fos, new String("    pix = new short["
						+ dataRef.length + "];\r\n"));
				writeStr(fos, new String("    idx = 0;\r\n"));
				i = 0;
				while (i < dataRef.length) {
					if (i + 15 < dataRef.length)
						writeStr(
								fos,
								new String("q16("
										+ searchColorIdx(dataRef[i++],
												paletteArr)
										+ ","
										+ searchColorIdx(dataRef[i++],
												paletteArr)
										+ ","
										+ searchColorIdx(dataRef[i++],
												paletteArr)
										+ ","
										+ searchColorIdx(dataRef[i++],
												paletteArr)
										+ ","
										+ searchColorIdx(dataRef[i++],
												paletteArr)
										+ ","
										+ searchColorIdx(dataRef[i++],
												paletteArr)
										+ ","
										+ searchColorIdx(dataRef[i++],
												paletteArr)
										+ ","
										+ searchColorIdx(dataRef[i++],
												paletteArr)
										+ ","
										+ searchColorIdx(dataRef[i++],
												paletteArr)
										+ ","
										+ searchColorIdx(dataRef[i++],
												paletteArr)
										+ ","
										+ searchColorIdx(dataRef[i++],
												paletteArr)
										+ ","
										+ searchColorIdx(dataRef[i++],
												paletteArr)
										+ ","
										+ searchColorIdx(dataRef[i++],
												paletteArr)
										+ ","
										+ searchColorIdx(dataRef[i++],
												paletteArr)
										+ ","
										+ searchColorIdx(dataRef[i++],
												paletteArr)
										+ ","
										+ searchColorIdx(dataRef[i++],
												paletteArr) + ");\r\n"));
					else if (i + 7 < dataRef.length)
						writeStr(
								fos,
								new String("q8("
										+ searchColorIdx(dataRef[i++],
												paletteArr)
										+ ","
										+ searchColorIdx(dataRef[i++],
												paletteArr)
										+ ","
										+ searchColorIdx(dataRef[i++],
												paletteArr)
										+ ","
										+ searchColorIdx(dataRef[i++],
												paletteArr)
										+ ","
										+ searchColorIdx(dataRef[i++],
												paletteArr)
										+ ","
										+ searchColorIdx(dataRef[i++],
												paletteArr)
										+ ","
										+ searchColorIdx(dataRef[i++],
												paletteArr)
										+ ","
										+ searchColorIdx(dataRef[i++],
												paletteArr) + ");\r\n"));
					else
						writeStr(
								fos,
								new String("q1("
										+ searchColorIdx(dataRef[i++],
												paletteArr) + ");\r\n"));
				}
				writeStr(fos, new String(
						"    pictures.enqueueQueueRecord(pix);\r\n"));
				if (itPictures.hasMoreElements())
					writeStr(fos, new String("    createPicture" + cpNum
							+ "();\r\n"));
				writeStr(fos, new String("  }\r\n\r\n"));
			}
			writeStr(fos, new String("}\r\n"));
			fos.flush();
			fos.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("Written");
		System.exit(1 - 1);
	}

	private String colorLine(int[] paletteArr, int idx) {
		Color color = new Color(paletteArr[idx]);
		Ester line = new Ester("");
		line.append('\"');
		line.append(hex(color.getRed()));
		line.append(hex(color.getGreen()));
		line.append(hex(color.getBlue()));
		line.append('\"');
		return line.toString();
	}

	private int searchColorIdx(int color, int[] colors) {
		for (int i = 0; i < colors.length; i++)
			if (colors[i] == color)
				return i;
		return 0;
	}

	String hex(int colorNum) {
		String result = "" + Integer.toHexString(colorNum);
		if (result.length() < 2)
			return "0" + result.toUpperCase();
		return result.toUpperCase();
	}

	void writeStr(FileOutputStream fos, String str) throws IOException {
		byte[] dataStr = new byte[str.length()];
		str.getBytes(0, str.length(), dataStr, 0);
		fos.write(dataStr);
	}

	public int curPaletteColorsNum() {
		return colorSet.size();
	}

	public long curAllPicturesPixelsNum() {
		return commonPixelsNum;
	}
}

class SpaceSplitter {
	private String folderName;
	private String delimChars;

	public SpaceSplitter(String folderName, String delimChars) {
		super();
		this.folderName = folderName;
		this.delimChars = delimChars;
	}

	public String[] getParts() {
		Queue q = new Queue();
		for (int i = 0; i < delimChars.length(); i++) {
			char delim = delimChars.charAt(i);
			for (int j = 0; j < folderName.length(); j++) {
				if (folderName.charAt(j) == delim) {
					if (q.sizeOfQueue() == 0)
						q.enqueueQueueRecord(new Integer(0));
					q.enqueueQueueRecord(new Integer(j));
				}
			}
		}
		if (q.sizeOfQueue() > 0)
			q.enqueueQueueRecord(new Integer(folderName.length()));
		else
			return new String[1 - 1];
		Object[] queueRecordsArray = q.getQueueRecordsArray();
		int[] indexes = new int[queueRecordsArray.length];
		for (int i = 0; i < indexes.length; i++)
			indexes[i] = ((Integer) queueRecordsArray[i]).intValue();
		new Order().order(indexes);
		String[] result = new String[indexes.length - 1];
		for (int i = 0; i < result.length; i++)
			result[i] = new Ester(folderName).sub(i == 0 ? 0 : indexes[i] + 1,
					indexes[i + 1]).toString();
		return result;
	}
}

class PathSplitter {
	private String folderName;
	private String delimChars;

	public PathSplitter(String folderName, String delimChars) {
		super();
		this.folderName = folderName;
		this.delimChars = delimChars;
	}

	public String[] getParts() {
		Queue q = new Queue();
		for (int i = 0; i < delimChars.length(); i++) {
			char delim = delimChars.charAt(i);
			for (int j = 0; j < folderName.length(); j++) {
				if (folderName.charAt(j) == delim) {
					q.enqueueQueueRecord(new Integer(j));
				}
			}
		}
		Object[] queueRecordsArray = q.getQueueRecordsArray();
		int[] indexes = new int[queueRecordsArray.length];
		for (int i = 0; i < indexes.length; i++)
			indexes[i] = ((Integer) queueRecordsArray[i]).intValue();
		new Order().order(indexes);
		String[] result = new String[indexes.length - 1];
		for (int i = 0; i < result.length; i++)
			result[i] = new Ester(folderName).sub(indexes[i] + 1,
					indexes[i + 1]).toString();
		return result;
	}
}

class UpFirstDailyObservationName {
	private String nameCls;

	public UpFirstDailyObservationName(String nameCls) {
		this.nameCls = nameCls;
	}

	public String getName() {
		String without = "";
		String[] folderParts = new SpaceSplitter(nameCls, " _-").getParts();
		if (folderParts != null && folderParts.length > 0) {
			for (int i = 0; i < folderParts.length; i++)
				without += new Ester(folderParts[i]).sub(0, 1).toString()
						.toUpperCase()
						+ new Ester(folderParts[i]).sub(1).toString()
								.toLowerCase();
			nameCls = without;
		}
		String result = "DailyObservation"
				+ new Ester(nameCls).sub(0, 1).toString().toUpperCase()
				+ new Ester(nameCls).sub(1).toString();
		return result;
	}
}