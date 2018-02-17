package projects.course.ozemsqlite.dao;

import java.util.List;

import projects.course.ozemsqlite.dao.dbo.DBO;

/**
 * Created by מאור סטודיו on 17/02/2018.
 */

public interface DAO<T extends DBO> {

    void insert(List<T> dbos);

    void update(List<T> dbos);

    void delete(List<T> dbos);

    List<T> getAll();
}
