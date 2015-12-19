package com.apukhtin.services;

import com.apukhtin.dao.DAOException;
import com.apukhtin.dao.LogDAO;

import java.util.List;

/**
 * Created by vlad on 19.12.2015.
 */
public class LogService {
    private LogDAO dao;

    public LogService(LogDAO logDAO) {
        this.dao = logDAO;
    }

    public void log(String msg) throws ServiceException {
        if (msg != null) {
            try {
                dao.log(msg);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
    }

    public List<String> getLogs() throws ServiceException {
        try {
            return dao.getLogs();
        } catch (DAOException e) {
            throw new ServiceException();
        }
    }

    public LogDAO getDao() {
        return dao;
    }

    public void setDao(LogDAO dao) {
        this.dao = dao;
    }
}
