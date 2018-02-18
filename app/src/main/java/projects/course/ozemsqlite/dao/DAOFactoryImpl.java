package projects.course.ozemsqlite.dao;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import projects.course.ozemsqlite.dao.table.RatingPalTable;

/**
 * Created by מאור סטודיו on 17/02/2018.
 */

public class DAOFactoryImpl implements DAOFactory {

    private Map<String, DAO> daoMap;

    public DAOFactoryImpl(final Context context) {
        daoMap = new HashMap<String, DAO>() {{
            put(RatingPalTable.TABLE_NAME, new RatingPalDAOImpl(context));
        }};
    }

    @Override
    public DAO getDAO(String tableName) {
        return daoMap.get(tableName);
    }
}
