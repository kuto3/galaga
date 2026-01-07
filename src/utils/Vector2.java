package utils;

/**
 * Classe représentant un vecteur 2D avec coordonnées x et y.
 * 
 * Fournit des opérations mathématiques vectorielles comme l'addition,
 * la soustraction, la multiplication, la division, et l'interpolation linéaire.
 * 
 * @version 1.0
 */
public class Vector2 {

    /**
     * Coordonnée x du vecteur.
     */
    private double x = 0;

    /**
     * Coordonnée y du vecteur.
     */
    private double y = 0;

    /**
     * Crée un vecteur 2D avec les coordonnées spécifiées.
     * 
     * @param x La coordonnée x
     * @param y La coordonnée y
     */
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Obtient la coordonnée x du vecteur.
     * 
     * @return La coordonnée x
     */
    public double x() {
        return x;
    }

    /**
     * Obtient la coordonnée y du vecteur.
     * 
     * @return La coordonnée y
     */
    public double y() {
        return y;
    }

    /**
     * Effectue une interpolation linéaire entre ce vecteur et un autre.
     * 
     * @param target Le vecteur cible
     * @param alpha  Le facteur d'interpolation (0 = ce vecteur, 1 = vecteur cible)
     * @return Un nouveau vecteur interpolé
     */
    public Vector2 lerp(Vector2 target, double alpha) {
        return new Vector2(
                x + (target.x - x) * alpha,
                y + (target.y - y) * alpha);
    }

    /**
     * Vérifie si ce vecteur est dans une boîte de délimitation définie de (0,0) à
     * box.
     * 
     * @param box Le vecteur définissant les limites supérieures
     * @return true si ce vecteur est dans la boîte, false sinon
     */
    public boolean isInBoundBox(Vector2 box) {
        return x > 0 && x < box.x && y > 0 && y < box.y;
    }

    /**
     * Vérifie si ce vecteur est dans une boîte de délimitation définie par deux
     * points.
     * 
     * @param minBox Le vecteur définissant le coin minimum
     * @param maxBox Le vecteur définissant le coin maximum
     * @return true si ce vecteur est dans la boîte, false sinon
     */
    public boolean isInBoundBox(Vector2 minBox, Vector2 maxBox) {
        return x >= minBox.x && x <= maxBox.x && y >= minBox.y && y <= maxBox.y;
    }

    /**
     * Restreint ce vecteur dans une boîte de délimitation définie de (0,0) à box.
     * 
     * Modifie ce vecteur directement.
     * 
     * @param box Le vecteur définissant les limites supérieures
     */
    public void clampToBoundBox(Vector2 box) {
        x = Math.clamp(x, 0, box.x);
        y = Math.clamp(y, 0, box.y);
    }

    /**
     * Restreint ce vecteur dans une boîte de délimitation définie par deux points.
     * 
     * Modifie ce vecteur directement.
     * 
     * @param minBox Le vecteur définissant le coin minimum
     * @param maxBox Le vecteur définissant le coin maximum
     */
    public void clampToBoundBox(Vector2 minBox, Vector2 maxBox) {
        x = Math.clamp(x, minBox.x, maxBox.x);
        y = Math.clamp(y, minBox.y, maxBox.y);
    }

    /**
     * Calcule la distance euclidienne entre ce vecteur et un autre.
     * 
     * @param b Le vecteur avec lequel calculer la distance
     * @return La distance entre les deux vecteurs
     */
    public double distanceOf(Vector2 b) {
        return (double) Math.sqrt(Math.pow(b.x() - x, 2) + Math.pow(b.y() - y, 2));
    }

    /**
     * Ajoute un vecteur à ce vecteur.
     * 
     * @param b Le vecteur à ajouter
     * @return Un nouveau vecteur résultat de l'addition
     */
    public Vector2 add(Vector2 b) {
        return new Vector2(x + b.x, y + b.y);
    }

    /**
     * Soustrait un vecteur de ce vecteur.
     * 
     * @param b Le vecteur à soustraire
     * @return Un nouveau vecteur résultat de la soustraction
     */
    public Vector2 sub(Vector2 b) {
        return new Vector2(x - b.x, y - b.y);
    }

    /**
     * Multiplie ce vecteur par un autre vecteur (multiplication composante par
     * composante).
     * 
     * @param b Le vecteur par lequel multiplier
     * @return Un nouveau vecteur résultat de la multiplication
     */
    public Vector2 mul(Vector2 b) {
        return new Vector2(x * b.x, y * b.y);
    }

    /**
     * Divise ce vecteur par un autre vecteur (division composante par composante).
     * 
     * @param b Le vecteur par lequel diviser
     * @return Un nouveau vecteur résultat de la division
     */
    public Vector2 div(Vector2 b) {
        return new Vector2(x / b.x, y / b.y);
    }

    /**
     * Retourne une représentation textuelle du vecteur au format "(x, y)".
     * 
     * @return Une chaîne de caractères représentant le vecteur
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
