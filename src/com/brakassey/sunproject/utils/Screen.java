package com.brakassey.sunproject.utils;

/**
 * Screen on which the game is displayed.
 */
public class Screen {
	/** Width of the screen. */
	protected float m_width ;
	/** Height of the screen. */
	protected float m_height ;

	/** Half of the width of the screen. */
	protected float m_halfWidth ;
	/** Half of the height of the screen. */
	protected float m_halfHeight ;

	/**
	 * Create a new screen.
	 * @param	width	Width of the screen.
	 * @param	height	Height of the screen.
	 */
	public Screen(float width, float height) {
		update(width, height) ;
	}

	/** Update data on the screen size. */
	protected void update(float width, float height) {
		m_width = width ;
		m_height = height ;
		m_halfWidth = width / 2.f ;
		m_halfHeight = height / 2.f ;
	}

																				/** SETTERS **/
	/** Update the size of the screen. */
	public void setSize(float width, float height) {
		update(width, height) ;
	}

																				/** GETTERS **/
	/** Get width of the screen. */
	public float getWidth() {
		return m_width ;
	}

	/** Get height of the screen. */
	public float getHeight() {
		return m_height ;
	}

	/** Get width of the screen. */
	public float getHalfWidth() {
		return m_halfWidth ;
	}

	/** Get height of the screen. */
	public float getHalfHeight() {
		return m_halfHeight ;
	}

	/**
	 * Get a percentage of width of the screen.
	 * @param	percent		Percentage of the screen between 0 and 1.
	 */
	public float getWidthPercent(float percent) {
		return m_width * percent ;
	}

	/**
	 * Get a percentage of width of the screen.
	 * @param	percent		Percentage of the screen between 0 and 1.
	 */
	public float getHeightPercent(float percent) {
		return m_height * percent ;
	}
}
