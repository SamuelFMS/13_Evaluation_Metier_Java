package dao;

import models.Formation;

import java.util.List;

public interface FormationDao {
    /**
     * Return all the existing formation
     *
     * @return
     */
    List<Formation> findAllAvailable();
}
