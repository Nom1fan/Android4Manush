package projects.course.ozemsqlite.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import projects.course.ozemsqlite.R;
import projects.course.ozemsqlite.dao.DAOFactory;
import projects.course.ozemsqlite.dao.RatingPalDAO;
import projects.course.ozemsqlite.dao.dbo.RatingPalDBO;
import projects.course.ozemsqlite.dao.table.DAOFactoryProvider;
import projects.course.ozemsqlite.dao.table.RatingPalTable;

public class MainActivity extends Activity implements OnClickListener {
    EditText editTextJourneyId, editTextCustomerId, editTextLocalPalNum, editTextRate, editTextDate;
    Button btnAdd, btnDelete, btnModify, btnView, btnViewAll, btnShowInfo;
    View myLayout;

    DAOFactory daoFactory;

    RatingPalDAO ratingPalDao;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myLayout = findViewById(R.id.myLayout);

        editTextJourneyId = findViewById(R.id.editText_journeyId);
        editTextCustomerId = findViewById(R.id.editText_customerNum);
        editTextLocalPalNum = findViewById(R.id.editText_localPalNum);
        editTextRate = findViewById(R.id.editText_rate);
        editTextDate = findViewById(R.id.editText_date);

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
        ratingPalDao = daoFactory.getDAO(RatingPalTable.TABLE_NAME);
    }

    public void onClick(View view) {
        handleBtnAdd(view);

        handleBtnViewAll(view);

        handleBtnDelete(view);

        handleBtnModify(view);
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
//                db.execSQL("UPDATE student SET name='" + editTextCustomerId.getText() + "',marks='" + editTextLocalPalNum.getText() +
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
//                editTextCustomerId.setText(c.getString(1));
//                editTextLocalPalNum.setText(c.getString(2));
//            } else {
//                showMessage("Error", "Invalid Rollno");
//                clearText();
//            }
//        }

//        if (view == btnShowInfo) {
//            showMessage("Student Management Application", "- SMA");
//        }
    }



    public void clearText() {
        List<EditText> allEditTextsInView = getAllEditTextsInView((AbsoluteLayout) myLayout);
        for (EditText editText : allEditTextsInView) {
            editText.setText("");
        }
        editTextJourneyId.requestFocus();
    }

    private void handleBtnViewAll(View view) {
        if (view == btnViewAll) {
            List<RatingPalDBO> ratingPalDBOS = ratingPalDao.getAll();
            if (ratingPalDBOS.size() == 0) {
                showMessage("Error", "No records found");
                return;
            }
            StringBuilder builder = new StringBuilder();
            for (RatingPalDBO ratingPalDBO : ratingPalDBOS) {
                builder.append(ratingPalDBO.toString()).append("\n");
            }

            showMessage("Rating Pal Details", builder.toString());
        }
    }

    private void handleBtnAdd(View view) {
        if (view == btnAdd) {
            if (!areAllFieldsValid()) {
                showMessage("Error", "All fields must be filled out");
                return;
            }

            List<RatingPalDBO> ratingPalDBOS = extractRatingPalDBOSFromUI();
            ratingPalDao.insert(ratingPalDBOS);

            showMessage("Success", "Record added");
            clearText();
        }
    }

    @NonNull
    private List<EditText> getAllEditTextsInView(AbsoluteLayout layout) {
        List<EditText> myEditTextList = new ArrayList<>();

        for (int i = 0; i < layout.getChildCount(); i++)
            if (layout.getChildAt(i) instanceof EditText)
                myEditTextList.add((EditText) layout.getChildAt(i));
        return myEditTextList;
    }

    private boolean areAllFieldsValid() {
        List<EditText> myEditTextList = getAllEditTextsInView((AbsoluteLayout) myLayout);

        for (EditText editText : myEditTextList) {
            if (editText.getText().toString().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void showMessage(String title, String message) {
        Builder builder = new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    @NonNull
    private List<RatingPalDBO> extractRatingPalDBOSFromUI() {
        List<RatingPalDBO> ratingPalDBOS = new ArrayList<>();

        final RatingPalDBO ratingPalDBO = new RatingPalDBO();
        ratingPalDBO.setCustomerNumber(Integer.valueOf(editTextCustomerId.getText().toString()));
        ratingPalDBO.setJourneyId(Integer.valueOf(editTextJourneyId.getText().toString()));
        ratingPalDBO.setLocalPalNumber(Integer.valueOf(editTextLocalPalNum.getText().toString()));

        String date = editTextDate.getText().toString();
        String rateString = editTextRate.getText().toString();
        Integer rate = rateString.isEmpty() ? 0 : Integer.valueOf(rateString);

        ratingPalDBO.setDate(date.isEmpty() ? null : date);
        ratingPalDBO.setRate(rate);

        ratingPalDBOS.add(ratingPalDBO);

        return ratingPalDBOS;
    }

    private void handleBtnDelete(View view) {
        if(view == btnDelete) {

            if (!areAllFieldsForDeleteValid()) {
                showMessage("Error", "For delete following fields must be filled out:\nJourney ID, Customer ID, Local Pal Num");
                return;
            }

            List<RatingPalDBO> ratingPalDBOS = extractRatingPalDBOSFromUI();
            int numRowsDeleted = ratingPalDao.delete(ratingPalDBOS);
            if (numRowsDeleted == 0) {
                showMessage("Error", "Record not found!");
                return;
            }

            showMessage("Success", "Record deleted");
            clearText();
        }
    }

    private boolean areAllFieldsForDeleteValid() {
        return !editTextJourneyId.getText().toString().trim().isEmpty() &&
                !editTextCustomerId.getText().toString().trim().isEmpty() &&
                !editTextLocalPalNum.toString().trim().isEmpty();
    }

    private void handleBtnModify(View view) {
        if(view == btnModify) {
            if (!areAllFieldsValid()) {
                showMessage("Error", "All fields must be filled out");
                return;
            }

            List<RatingPalDBO> ratingPalDBOS = extractRatingPalDBOSFromUI();
            int numRowsUpdated = ratingPalDao.update(ratingPalDBOS);
            if (numRowsUpdated == 0) {
                showMessage("Error", "Record not found!");
                return;
            }

            showMessage("Success", "Record modified");
            clearText();
        }
    }

}