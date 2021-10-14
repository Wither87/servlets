package Accounts;

import DataBase.DBService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private static final AccountService _instance = new AccountService();
    public static AccountService getInstance() { return _instance; };

    private AccountService(){
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
        _dbService = new DBService();
        initializeDBService();
    }

    private final DBService _dbService;
    private final String homeDirectory = "D:\\test\\users\\";
    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;

    private void initializeDBService() {
        try{
            ResultSet rs = _dbService.getStatement().executeQuery("select * from users");
            while (rs.next()){
                loginToProfile.put(
                        rs.getString(1),
                        new UserProfile(rs.getString(1),rs.getString(2),rs.getString(3))
                );
                String login = rs.getString(1);
                System.out.println("Login: " + login);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertNewUser(String login, String email, String pass){
        try{
            String sql = String.format("insert into users (login, email, password) values ('%s', '%s', '%s');", login, email, pass);
            _dbService.getStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUserHomeDirectory(UserProfile profile) {
        return homeDirectory + profile.getLogin() + '\\';
    }
    public String getHomeDirectory() { return homeDirectory; }

    public void addNewUser(UserProfile userProfile){
        insertNewUser(userProfile.getLogin(), userProfile.getEmail(), userProfile.getPass());
        loginToProfile.put(userProfile.getLogin(), userProfile);
    }

    public UserProfile getUserByLogin(String login){
        return loginToProfile.get(login);
    }

    public UserProfile getUserBySessionId(String sessionId){
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile){
        sessionIdToProfile.put(sessionId, userProfile);
    }
    public void deleteSession(String sessionId){
        sessionIdToProfile.remove(sessionId);
    }
}
