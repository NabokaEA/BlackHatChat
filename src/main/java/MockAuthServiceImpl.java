import java.util.HashMap;

public class MockAuthServiceImpl implements AuthService{
    private HashMap<String,String> userDao;
    private static MockAuthServiceImpl instance;

    private MockAuthServiceImpl() {
        userDao=new HashMap<>();
        userDao.put("user1","pass1");
        userDao.put("user2","pass2");
    }

    public static MockAuthServiceImpl getInstance() {
        if (instance==null){
            instance=new MockAuthServiceImpl();

        }

        return instance;
    }

    @Override
    public void addUser(String name, String pass) {
        userDao.put(name, pass);

    }

    @Override
    public boolean auth(String name, String pass) {
        return userDao.get(name)!=null;

    }
}
