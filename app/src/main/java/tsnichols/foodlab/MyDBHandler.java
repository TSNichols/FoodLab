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


    // Add a new row to the database
    public void addIngredient(DBAddIngredient ingredient) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_INGREDIENT, ingredient.get_ingredientName());
        values.put(COLUMN_SIZE, ingredient.get_ingredientSize());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_INGREDIENTS, null, values);
        db.close();
    }


    // Delete a row from the database
    public void deleteIngredient(String ingredientName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_INGREDIENTS + " WHERE " + COLUMN_INGREDIENT + "=\"" + ingredientName + "\";");
    }


    // Retrieve all records and populate into List<String>
    public List<DBAddIngredient> databaseToList() {

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


    // Output DB as a string - not a function way to use this
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
}
