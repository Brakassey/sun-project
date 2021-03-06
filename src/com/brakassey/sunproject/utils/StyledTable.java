package com.brakassey.sunproject.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class StyledTable extends Table {
	protected TableStyle style;

	public StyledTable(TableStyle style) {
		this.style = style;
	}


	public TableStyle getStyle() {
		return style;
	}

	public void setStyle(TableStyle style) {
		this.style = style;
	}

	public static class TableStyle {
		public Drawable background;
		public BitmapFont font;
		public Color fontColor;
		public int padX;
		public int padY;

		public TableStyle() {
			this.background = null;
			this.font = new BitmapFont(); // default font
			this.fontColor = new Color(Color.WHITE);
			this.padX = 0;
			this.padY = 0;
		}
	}

}