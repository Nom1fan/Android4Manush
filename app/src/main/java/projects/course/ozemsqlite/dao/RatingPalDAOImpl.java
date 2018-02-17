package projects.course.ozemsqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ParseException;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import projects.course.ozemsqlite.dao.dbo.RatingPalDBO;
import projects.course.ozemsqlite.dao.table.RatingPalTable;

import static projects.course.ozemsqlite.dao.table.RatingPalTable.*;

/**
 * Created by מאור סטודיו on 17/02/2018.
 */

public class RatingPalDAOImpl extends BaseDAO implements RatingPalDAO {


    public RatingPalDAOImpl(Context context) {
        super(context);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override // Create
    public void insert(List<RatingPalDBO> dbos) {
        ContentValues contentValues = populateContentValues(dbos);
        getWritableDatabase().insert(TABLE_NAME, null, contentValues);
    }

    @Override // Read
    public List<RatingPalDBO> getAll() {
        List<RatingPalDBO> ratingPalDBOS = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + RatingPalTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                RatingPalDBO dbo = new RatingPalDBO();
                // Passing values
                dbo.setJourneyId(c.getInt(0));
                dbo.setCustomerNumber(c.getInt(1));
                dbo.setLocalPalNumber(c.getInt(2));
                dbo.setRate(c.getInt(3));
                dbo.setDate(c.getString(4));
                ratingPalDBOS.add(dbo);
            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return ratingPalDBOS;
    }

    @Override // Update
    public int update(List<RatingPalDBO> dbos) {
        int numRowsDeleted = delete(dbos);
        if (numRowsDeleted !=0) {
            insert(dbos);
        }
        return numRowsDeleted;
    }

    @Override // Delete
    public int delete(List<RatingPalDBO> dbos) {
        int totalRowsDeleted = 0;
        for (RatingPalDBO dbo : dbos) {
            int numRowsDeleted = getWritableDatabase().delete(RatingPalTable.TABLE_NAME, getWhereClause(), getWhereArgs(dbo));
            totalRowsDeleted += numRowsDeleted;
        }
        return totalRowsDeleted;
    }

    @NonNull
    private ContentValues populateContentValues(List<RatingPalDBO> dbos) {
        ContentValues contentValues = new ContentValues();

        for (RatingPalDBO dbo : dbos) {
            contentValues.put(COL_DATE, dbo.getDate());
            contentValues.put(COL_CUSTOMER_NUMBER, dbo.getCustomerNumber());
            contentValues.put(COL_JOURNEY_ID, dbo.getJourneyId());
            contentValues.put(COL_LOCAL_PAL_NUMBER, dbo.getLocalPalNumber());
            contentValues.put(COL_RATE, dbo.getRate());
        }
        return contentValues;
    }

    @NonNull
    private String getWhereClause() {
        return COL_CUSTOMER_NUMBER + " = ? AND " +
                COL_JOURNEY_ID + " = ? AND " +
                COL_LOCAL_PAL_NUMBER + " = ?";
    }

    @NonNull
    private String[] getWhereArgs(RatingPalDBO dbo) {
        return new String[]{String.valueOf(
                dbo.getCustomerNumber()),
                String.valueOf(dbo.getJourneyId()),
                String.valueOf(dbo.getLocalPalNumber())};
    }
}
