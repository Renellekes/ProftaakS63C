package service;

import java.util.List;
import javax.ejb.Stateless;
import dao.LogonDAO;
import javax.inject.Inject;

@Stateless
public class LogonService {

    @Inject
    private LogonDAO logonDAO;

    public LogonService() {
    }

    public int login(String username, String hash, String systeem) {
        return logonDAO.login(username, hash, systeem);
    }
    
    public int register(String username, String hash) {
        return logonDAO.register(username, hash);
    }
    
    public void addRight(String username, String systeem) {
        logonDAO.addRight(username,systeem);
    }

    public void removeRight(String username, String systeem) {
        logonDAO.removeRight(username,systeem);
    }

    public void remove(String username) {
        logonDAO.remove(username);
    }
    
    public int count() {
        return logonDAO.count();
    }

}