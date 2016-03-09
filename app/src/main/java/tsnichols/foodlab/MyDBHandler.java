package tsnichols.foodlab;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ingredients.db";
    public static final String TABLE_INGREDIENTS = "ingredients";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_INGREDIENT = "ingredientName";
    public static final String COLUMN_SIZE = "ingredientSize";


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_INGREDIENTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_INGREDIENT + " TEXT, " +
                COLUMN_SIZE + " TEXT );";
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS);
        onCreate(db);
    }


    // Add ingredient to database
    public void addIngredient(String name, String size) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put(COLUMN_INGREDIENT, name);
        row.put(COLUMN_SIZE, size);

        db.insert(TABLE_INGREDIENTS, null, row);
        db.close();
    }

    // Edit size for ingredient
    public void changeSize(String ingredient, String size) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SIZE, size);
        String whereClause = COLUMN_INGREDIENT + " = ? ";
        String[] whereArgs = {ingredient};

        db.update(TABLE_INGREDIENTS, values, whereClause, whereArgs);
        db.close();
    }

    // Delete a row from the database
    public void deleteIngredient(String ingredientName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_INGREDIENTS + " WHERE " + COLUMN_INGREDIENT + "=\"" + ingredientName + "\";");
    }

    // Get Ingredient ID for selected ingredient
    public String getIngredientSize(String selectedIngredient) {

        String ingredientSize = "";

        SQLiteDatabase db = getReadableDatabase();

        String[] columns = new String[]{COLUMN_SIZE};
        String selection = COLUMN_INGREDIENT + " = ? ";
        String[] selectArgs = new String[]{selectedIngredient};

        Cursor c = db.query(TABLE_INGREDIENTS, columns, selection, selectArgs, null, null, null);

        c.moveToFirst();

        while(!c.isAfterLast()){
            ingredientSize = c.getString(c.getColumnIndex(COLUMN_SIZE));
            c.moveToNext();
        }

        c.close();
        db.close();

        return ingredientSize;
    }

    // Retrieve all ingredients and populate List<String>
    public List<String> databaseIngredientList() {

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT " +
                COLUMN_INGREDIENT +
                " FROM " + TABLE_INGREDIENTS +
                " ORDER BY " + COLUMN_INGREDIENT +
                " COLLATE NOCASE ASC";
        List<String> ingredientNameList = new ArrayList<>();

        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                ingredientNameList.add(c.getString(c.getColumnIndex(COLUMN_INGREDIENT)));
            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return ingredientNameList;
    }

    // Retrieve distinct sizes and populate List<String>
    public List<String> databaseSizeList() {

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT DISTINCT " +
                COLUMN_SIZE +
                " FROM " + TABLE_INGREDIENTS +
                " ORDER BY " + COLUMN_SIZE +
                " COLLATE NOCASE ASC";
        List<String> ingredientSizeList = new ArrayList<>();

        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                ingredientSizeList.add(c.getString(c.getColumnIndex(COLUMN_SIZE)));
            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return ingredientSizeList;
    }

    // Retrieve all records and populate into List<DBAddIngredient>
    public List<DBAddIngredient> databaseAllToList() {

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT " +
                COLUMN_ID + "," +
                COLUMN_INGREDIENT + "," +
                COLUMN_SIZE +
                " FROM " + TABLE_INGREDIENTS +
                " ORDER BY " + COLUMN_INGREDIENT +
                " COLLATE NOCASE ASC";
        List<DBAddIngredient> ingredientList = new ArrayList<DBAddIngredient>();

        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                DBAddIngredient ingredient = new DBAddIngredient();
                ingredient.set_id(c.getInt(c.getColumnIndex(COLUMN_ID)));
                ingredient.set_ingredientName(c.getString(c.getColumnIndex(COLUMN_INGREDIENT)));
                ingredient.set_ingredientSize(c.getString(c.getColumnIndex(COLUMN_SIZE)));
                ingredientList.add(ingredient);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return ingredientList;
    }

    // Retrieve list of unique sizes
    public List<DBAddIngredient> databaseToSizeList() {

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT DISTINCT " +
                COLUMN_SIZE +
                " FROM " + TABLE_INGREDIENTS +
                " ORDER BY " + COLUMN_SIZE +
                " COLLATE NOCASE ASC";
        List<DBAddIngredient> ingredientSizeList = new ArrayList<DBAddIngredient>();

        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                DBAddIngredient ingredientSize = new DBAddIngredient();
                ingredientSize.set_ingredientSize(c.getString(c.getColumnIndex(COLUMN_SIZE)));
                ingredientSizeList.add(ingredientSize);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return ingredientSizeList;
    }


    // Output DB as a string - not a functional way to use this
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_INGREDIENTS + " WHERE 1";

        // Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);

        // Move to first row in results of query
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("ingredientName"))!= null) {
                dbString += c.getString(c.getColumnIndex("ingredientName"));
                dbString += " ";
                dbString += c.getString(c.getColumnIndex("ingredientSize"));
                dbString += "\n";
            }
        }
        c.close();
        db.close();
        return dbString;
    }
    //**********************************************************
}
