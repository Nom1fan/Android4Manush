package projects.course.ozemsqlite.dao.table;

import android.content.Context;

import projects.course.ozemsqlite.dao.DAOFactory;
import projects.course.ozemsqlite.dao.DAOFactoryImpl;

/**
 * Created by מאור סטודיו on 17/02/2018.
 */

public abstract class DAOFactoryProvider {

    public static DAOFactory createDAOFactory(Context context) {
        return new DAOFactoryImpl(context);
    }
}
