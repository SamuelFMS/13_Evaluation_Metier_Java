package dao;

import models.Formation;

import java.util.List;

/**
 * Interface of table Formation sql request
 */
public interface FormationDao {
    /**
     * Return all the existing formation
     *
     * @return
     */
    List<Formation> findAllAvailable();
}
