package dao;


public interface LogonDAO {

    int count();
    
    int register(String username, String hash);
    
    //Hier even enum van maken bij systeem? Leesbare code is mooier
    int login(String username, String hash, String systeem);

    int addRight(String username, String right);

    int removeRight(String username, String right);

    int remove(String username);
}