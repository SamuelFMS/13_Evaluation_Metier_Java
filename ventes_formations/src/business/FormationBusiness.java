package business;

import dao.FormationDao;
import dao.FormationDaoImpl;
import models.Formation;

import java.util.Collections;
import java.util.List;

public class FormationBusiness {
    private static final FormationDaoImpl formationDao = new FormationDaoImpl();

    public List<Formation> getAllAvailableFormation(){
        return formationDao.findAllAvailable();
    }
}
