package utils;

public class Vector2 {
    private double x = 0;
    private double y = 0;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    // Interpolation linéaire entre ce vecteur et un autre vecteur
    public Vector2 lerp(Vector2 target, double alpha) {
        return new Vector2(
                x + (target.x - x) * alpha,
                y + (target.y - y) * alpha);
    }

    public boolean isInBoundBox(Vector2 box) {
        return x > 0 && x < box.x && y > 0 && y < box.y;
    }

    public boolean isInBoundBox(Vector2 minBox, Vector2 maxBox) {
        return x >= minBox.x && x <= maxBox.x && y >= minBox.y && y <= maxBox.y;
    }

    public void clampToBoundBox(Vector2 box) {
        x = Math.clamp(x, 0, box.x);
        y = Math.clamp(y, 0, box.y);
    }

    public void clampToBoundBox(Vector2 minBox, Vector2 maxBox) {
        x = Math.clamp(x, minBox.x, maxBox.x);
        y = Math.clamp(y, minBox.y, maxBox.y);
    }

    public double distanceOf(Vector2 b) {
        return (double) Math.sqrt(Math.pow(b.x() - x, 2) + Math.pow(b.y() - y, 2));
    }

    public Vector2 mul(Vector2 b) {
        x *= b.x;
        y *= b.y;
        return this;
    }

    public Vector2 add(Vector2 b) {
        x += b.x;
        y += b.y;
        return this;
    }

    public Vector2 sub(Vector2 b) {
        x -= b.x;
        y -= b.y;
        return this;
    }

    public Vector2 div(Vector2 b) {
        x /= b.x;
        y /= b.y;
        return this;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
