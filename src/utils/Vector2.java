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

    public void clampToBoundBox(Vector2 box) {
        x = Math.clamp(x, 0, box.x);
        y = Math.clamp(y, 0, box.y);
    }

    public void clampToBoundBox(Vector2 minBox, Vector2 maxBox) {
        x = Math.clamp(x, minBox.x, maxBox.x);
        y = Math.clamp(y, minBox.y, maxBox.y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
