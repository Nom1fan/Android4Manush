package projects.course.ozemsqlite.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.database.Cursor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import projects.course.ozemsqlite.R;
import projects.course.ozemsqlite.dao.DAO;
import projects.course.ozemsqlite.dao.DAOFactory;
import projects.course.ozemsqlite.dao.DAOFactoryImpl;
import projects.course.ozemsqlite.dao.dbo.RatingPalDBO;
import projects.course.ozemsqlite.dao.table.DAOFactoryProvider;
import projects.course.ozemsqlite.dao.table.RatingPalTable;

public class MainActivity extends Activity implements OnClickListener {
    EditText editRollno, editName, editMarks;
    Button btnAdd, btnDelete, btnModify, btnView, btnViewAll, btnShowInfo;

    DAOFactory daoFactory;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editRollno = findViewById(R.id.editRollno);
        editName = findViewById(R.id.editName);
        editMarks = findViewById(R.id.editMarks);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnModify = findViewById(R.id.btnModify);
        btnView = findViewById(R.id.btnView);
        btnViewAll = findViewById(R.id.btnViewAll);
        btnShowInfo = findViewById(R.id.btnShowInfo);
        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnModify.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnViewAll.setOnClickListener(this);
        btnShowInfo.setOnClickListener(this);

        daoFactory = DAOFactoryProvider.createDAOFactory(this);
    }

    public void onClick(View view) {
        handleBtnAdd(view);


//        if (view == btnDelete) {
//            if (text.toString().trim().length() == 0) {
//                showMessage("Error", "Please enter Rollno");
//                return;
//            }
//            Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno='" + text + "'", null);
//            if (c.moveToFirst()) {
//                db.execSQL("DELETE FROM student WHERE rollno='" + text + "'");
//                showMessage("Success", "Record Deleted");
//            } else {
//                showMessage("Error", "Invalid Rollno");
//            }
//            clearText();
//        }
//        if (view == btnModify) {
//            if (text.toString().trim().length() == 0) {
//                showMessage("Error", "Please enter Rollno");
//                return;
//            }
//            Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno='" + text + "'", null);
//            if (c.moveToFirst()) {
//                db.execSQL("UPDATE student SET name='" + editName.getText() + "',marks='" + editMarks.getText() +
//                        "' WHERE rollno='" + text + "'");
//                showMessage("Success", "Record Modified");
//            } else {
//                showMessage("Error", "Invalid Rollno");
//            }
//            clearText();
//        }
//        if (view == btnView) {
//            if (text.toString().trim().length() == 0) {
//                showMessage("Error", "Please enter Rollno");
//                return;
//            }
//            Cursor c = db.rawQuery("SELECT * FROM student WHERE rollno='" + text + "'", null);
//            if (c.moveToFirst()) {
//                editName.setText(c.getString(1));
//                editMarks.setText(c.getString(2));
//            } else {
//                showMessage("Error", "Invalid Rollno");
//                clearText();
//            }
//        }
//        if (view == btnViewAll) {
//            Cursor c = db.rawQuery("SELECT * FROM student", null);
//            if (c.getCount() == 0) {
//                showMessage("Error", "No records found");
//                return;
//            }
//            StringBuffer buffer = new StringBuffer();
//            while (c.moveToNext()) {
//                buffer.append("Rollno: " + c.getString(0) + "\n");
//                buffer.append("Name: " + c.getString(1) + "\n");
//                buffer.append("Marks: " + c.getString(2) + "\n\n");
//            }
//            showMessage("Student Details", buffer.toString());
//        }
//        if (view == btnShowInfo) {
//            showMessage("Student Management Application", "- SMA");
//        }
    }

    private void handleBtnAdd(View view) {
        if (view == btnAdd) {
            if (areAllFieldsValid()) {
                return;
            }

            String date = editRollno.getText().toString();

            try {
                List<RatingPalDBO> ratingPalDBOS = extractRatingPalDBOSFromUI(date);
                DAO dao = daoFactory.getDAO(RatingPalTable.TABLE_NAME);
                dao.insert(ratingPalDBOS);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            showMessage("Success", "Record added");
            clearText();
        }
    }

    @NonNull
    private List<RatingPalDBO> extractRatingPalDBOSFromUI(String date) throws ParseException {
        final RatingPalDBO ratingPalDBO = new RatingPalDBO();
        ratingPalDBO.setDate((Date) new SimpleDateFormat("yyyy-HH-mm HH:mm:ss", Locale.UK).parse(date));
        ratingPalDBO.setCustomerNumber(1);
        ratingPalDBO.setJourneyId(1);
        ratingPalDBO.setLocalPalNumber(2);
        ratingPalDBO.setRate(1.9);

        final RatingPalDBO ratingPalDBO2 = new RatingPalDBO();
        ratingPalDBO2.setDate((Date) new SimpleDateFormat("yyyy-HH-mm HH:mm:ss", Locale.UK).parse(date));
        ratingPalDBO2.setCustomerNumber(1);
        ratingPalDBO2.setJourneyId(1);
        ratingPalDBO2.setLocalPalNumber(2);
        ratingPalDBO2.setRate(1.9);

        return new ArrayList<RatingPalDBO>() {{
            add(ratingPalDBO);
            add(ratingPalDBO2);
        }};
    }

    private boolean areAllFieldsValid() {
        return editRollno.getText().toString().trim().length() == 0 ||
                editName.getText().toString().trim().length() == 0 ||
                editMarks.getText().toString().trim().length() == 0;
    }

    public void showMessage(String title, String message) {
        Builder builder = new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void clearText() {
        editRollno.setText("");
        editName.setText("");
        editMarks.setText("");
        editRollno.requestFocus();
    }
}