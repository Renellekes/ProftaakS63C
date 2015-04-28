package dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class LogonDAOCollectionImpl implements LogonDAO {
    
    @Inject
    DataBase db;
    
    public LogonDAOCollectionImpl() {
    }

    @Override
    public int count() {
        return db.count();
    }

    @Override
    public int register(String username, String hash) {
        return db.register(username, hash);
    }

    @Override
    public int login(String username, String hash, String systeem) {
        return db.login(username, hash, systeem);
    }

    @Override
    public int addRight(String username, String systeem) {
        return db.addRight(username, systeem);
    }

    @Override
    public int removeRight(String username, String systeem) {
        return db.removeRight(username, systeem);
    }

    @Override
    public int remove(String username) {
        return db.remove(username);
    }   

}
