package com.brakassey.sunproject.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class TextBox extends StyledTable {
	private final Label label;

	// protected TextBoxStyle style;
	public TextBox(TableStyle style) {
		this("", style);
	}

	public TextBox(String text, TableStyle style) {
		super(style);
		// this.style = style;
		this.label = new Label(text, new Label.LabelStyle(style.font,
				style.fontColor));
		label.setAlignment(Align.top | Align.left, Align.left);
		label.setX(getX() + this.style.padX);
		label.setY(getY() + this.style.padY);
		label.setWrap(true);
	}

	public Label getLabel() {
		return label;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		this.setBackground(style.background);
		validate();
		super.draw(batch, parentAlpha);
		label.setPosition(style.padX, style.padY);
		label.setSize(getWidth() - style.padX, getHeight() - style.padY);
		label.draw(batch, parentAlpha);
	}


	public void setText(String text) {
		label.setText(text);
	}


	public void setStyle(TableStyle style) {
		this.style = style;
	}

	@Override
	public void setPosition(float x, float y) {
		setX(x);
		setY(y);
	}

	@Override
	public void setX(float x) {
		super.setX(x);
		this.label.setX(x + this.style.padX);
	}

	@Override
	public void setY(float y) {
		super.setY(y);
		this.label.setY(y - this.style.padY);
	}
}