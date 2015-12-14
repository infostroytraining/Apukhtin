package dao.connection.configer;

/**
 * Created by Vlad on 14.12.2015.
 */
public class ConnectionConfig {
    private String url;
    private String user;
    private String password;

    public ConnectionConfig(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public ConnectionConfig() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
