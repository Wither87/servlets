package Accounts;

import DataBase.DBService;
import DataBase.DataSets.UsersDataSet;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private static final AccountService _instance = new AccountService();
    public static AccountService getInstance() { return _instance; };

    private AccountService(){
        sessionIdToProfile = new HashMap<>();
        _dbService = new DBService();
    }

    private final DBService _dbService;
    private final String homeDirectory = "D:\\test\\users\\";
    private final Map<String, UserProfile> sessionIdToProfile;

    public String getUserHomeDirectory(UserProfile profile) {
        return homeDirectory + profile.getLogin() + '\\';
    }
    public String getHomeDirectory() { return homeDirectory; }

    public void addNewUser(UserProfile userProfile) {
        _dbService.addUser(
                userProfile.getLogin(),
                userProfile.getPass(),
                userProfile.getEmail()
        );
    }

    public UserProfile getUserByLogin(String login) {
        UsersDataSet usersDS = _dbService.getUserByLogin(login);
        return new UserProfile(
            usersDS.getLogin(),
            usersDS.getPass(),
            usersDS.getEmail());
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
