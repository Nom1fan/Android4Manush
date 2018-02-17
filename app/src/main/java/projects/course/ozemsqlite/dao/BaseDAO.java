package projects.course.ozemsqlite.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import projects.course.ozemsqlite.dao.table.RatingPalTable;

/**
 * Created by מאור סטודיו on 17/02/2018.
 */

public class BaseDAO extends SQLiteOpenHelper {

    private static final String DB_NAME = "RAFIKI";

    public BaseDAO(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + RatingPalTable.TABLE_NAME + " ( `journeyId` INT, `customerNumber` INT, `localPalNumber` INT, `rate` DOUBLE , `date` VARCHAR);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
