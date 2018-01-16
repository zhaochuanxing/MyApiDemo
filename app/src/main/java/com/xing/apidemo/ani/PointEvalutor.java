package com.xing.apidemo.ani;

import android.animation.TypeEvaluator;

/**
 * Created by zhao on 18-1-16.
 */

public class PointEvalutor implements TypeEvaluator<Point> {

    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        float x = startValue.getX() + fraction * (endValue.getX() - startValue.getX());
        float y = startValue.getY() + fraction * (endValue.getY() - startValue.getY());
        Point point = new Point(x, y);
        return point;
    }
}
