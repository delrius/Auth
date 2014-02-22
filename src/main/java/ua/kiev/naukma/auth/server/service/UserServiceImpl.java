package ua.kiev.naukma.auth.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ua.kiev.naukma.auth.client.service.UserService;
import ua.kiev.naukma.auth.server.db.hibernate.user.UserDAOImpl;
import ua.kiev.naukma.auth.shared.model.LoginModel;
import ua.kiev.naukma.auth.shared.model.PagingResult;
import ua.kiev.naukma.auth.shared.model.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("serial")
public class UserServiceImpl extends RemoteServiceServlet implements
        UserService {
    private static UserDAOImpl userDAO = new UserDAOImpl();

    private List<LoginModel> convert(List<User> users) {
        List<LoginModel> list = new ArrayList<>();
        for (User user : users) {
            LoginModel model = new LoginModel();
            model.setLogin(user.getUserName());
            model.setPassword(user.getPassword());
            list.add(model);
        }
        return list;
    }

    public PagingResult<LoginModel> getUsers(int start, int length, final boolean isAscending) {
        final List<LoginModel> users = convert(userDAO.getAll());

        Collections.sort(users, new Comparator<LoginModel>() {
            public int compare(LoginModel o1, LoginModel o2) {
                if (o1 == o2) {
                    return 0;
                }

                int diff = -1;
                if (o1 != null) {
                    diff = (o2 != null) ? o1.getLogin().compareTo(o2.getLogin()) : 1;
                }
                return isAscending ? diff : -diff;
            }
        });

        ArrayList<LoginModel> result = new ArrayList<LoginModel>();

        for (LoginModel model : users.subList(start, length > users.size() - start ? users.size() : start + length)) {
            result.add(model);
        }

        PagingResult<LoginModel> res = new PagingResult<LoginModel>();
        res.setResult(result);
        res.setAllLength(users.size());
        return res;
    }
}
