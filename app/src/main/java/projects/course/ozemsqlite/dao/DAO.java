package projects.course.ozemsqlite.dao;

import java.util.List;

import projects.course.ozemsqlite.dao.dbo.DBO;

/**
 * Created by מאור סטודיו on 17/02/2018.
 */

public interface DAO<T extends DBO> {

    void insert(List<T> dbos);

    int update(List<T> dbos);

    int delete(List<T> dbos);

    List<T> getAll();
}
