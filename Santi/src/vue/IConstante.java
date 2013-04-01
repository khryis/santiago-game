package vue;

import java.awt.Color;
import java.awt.Font;

public interface IConstante {
	public final static Font POLICE_15 = new Font("Helvetica", Font.TYPE1_FONT,
			15);
	public final static Font POLICE_30 = new Font("Helvetica", Font.TYPE1_FONT,
			30);
	public final static Font POLICE_60 = new Font("Helvetica", Font.TYPE1_FONT,
			60);
	public final static Color BG_TRANSPARENT = new Color(0, 0, 0, 0);
	public final static Color FG_COLOR = new Color(102, 204, 255);
	public final static Color BG_COLOR = new Color(204, 255, 255, 50);
	public final static Color GREEN_BORDER = new Color(51, 102, 51);
	public final static Color NICE_GREY = new Color(120, 120, 120);

	public final static Color BG_BUTTON = new Color(204, 255, 255, 50);
	public final static Color FG_BUTTON = new Color(102, 204, 255);
}
