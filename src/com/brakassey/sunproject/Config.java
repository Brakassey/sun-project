package com.brakassey.sunproject;

public class Config {

    public static final int     TILE_SIZE   = 16;
    public static final float   TILE_SCALE  = 1 / (float) TILE_SIZE;

    public static final int     TILE_ZOOM   = 4;

    public static final int     WIN_DIV     = TILE_SIZE * TILE_ZOOM;

    // No idea why, but maps are drawn with a shift of 8 from origin
    public static final int     MAP_SHIFT   = 8;

    public static final float   HULL        = 0.4f;
    public static final float   HULL_SIDE   = 0.35f;

}
