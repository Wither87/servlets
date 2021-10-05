package Accounts;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private static final Map<String, UserProfile> loginToProfile;
    private static final Map<String, UserProfile> sessionIdToProfile;

    static {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();

        loginToProfile.put("admin", new UserProfile("admin"));
    }

    public static void addNewUser(UserProfile userProfile){
        loginToProfile.put(userProfile.getLogin(), userProfile);
    }

    public static UserProfile getUserByLogin(String login){
        return loginToProfile.get(login);
    }

    public static UserProfile getUserBySessionId(String sessionId){
        return sessionIdToProfile.get(sessionId);
    }

    public static void addSession(String sessionId, UserProfile userProfile){
        sessionIdToProfile.put(sessionId, userProfile);
    }
    public static void deleteSession(String sessionId){
        sessionIdToProfile.remove(sessionId);
    }
}
