package tsnichols.foodlab;

public class DBAddIngredient {

    private int _id;
    private String _ingredientName;
    private String _ingredientSize;

    public DBAddIngredient(){
    }

    public DBAddIngredient(String ingredientName, String ingredientSize) {
        this._ingredientName = ingredientName;
        this._ingredientSize = ingredientSize;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_ingredientName(String _ingredientName) {
        this._ingredientName = _ingredientName;
    }

    public void set_ingredientSize(String _ingredientSize) {
        this._ingredientSize = _ingredientSize;
    }

    public int get_id() {
        return _id;
    }

    public String get_ingredientName() {
        return _ingredientName;
    }

    public String get_ingredientSize() {
        return _ingredientSize;
    }
}
