package com.brakassey.sunproject.utils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tileset {
	private TextureRegion[] tiles;
	private int width;
	private int height;
	private int numTiles;
	private int tileWidth;
	private int tileHeight;
	private int margin;
	private int spacing;

	Tileset(TextureRegion texture, int tileWidth, int tileHeight, int margin, int spacing) {
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.margin = margin;
		this.spacing = spacing;
		this.width = (texture.getRegionWidth() - margin) / (tileWidth + spacing);
		this.height = (texture.getRegionHeight() - margin) / (tileHeight + spacing);
		this.tiles = new TextureRegion[width * height];

		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				int tileX = x * (tileWidth + spacing) + margin;
				int tileY = y * (tileHeight + spacing) + margin;
				this.tiles[coordToIndex(x, y)] = new TextureRegion(texture, tileX, tileY, tileWidth, tileHeight);
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getNumTiles() {
		return numTiles;
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public int getMargin() {
		return margin;
	}

	public int getSpacing() {
		return spacing;
	}

	public TextureRegion getTile(int index) {
		return tiles[index];
	}

	public TextureRegion[] getTileRange(int start, int length) {
		TextureRegion[] tileRange = new TextureRegion[length];
		for (int i = 0; i < length; ++i) {
			tileRange[i] = tiles[start + i];
		}
		return tileRange;
	}

	public int coordToIndex(int x, int y) {
		return y * width + x;
	}
}
