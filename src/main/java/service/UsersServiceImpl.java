package service;

import component.Pagination;
import dao.UserDAO;
import dao.UserDAOImpl;
import domain.User;

import java.util.List;
import java.util.Optional;

public class UsersServiceImpl implements UserService {

    private static final UsersServiceImpl usersService = new UsersServiceImpl();
    private final UserDAO userDAO = UserDAOImpl.getInstance();

    public static UsersServiceImpl getInstance() {
        return usersService;
    }

    private UsersServiceImpl() {
    }

    @Override
    public List<User> getAllUsers(Optional<String> page, Optional<String> sortByValue) {
        Integer paginationPage = null;
        String sortBy = null;
        if (page.isPresent()) {
            paginationPage = transformQueryPageToInteger(page.get());
        }

        if (sortByValue.isPresent()) {
            sortBy = getValidSortValue(sortByValue.get());
        }

        return userDAO.getAllUser(paginationPage, sortBy);
    }

    private String getValidSortValue(String sortByValue) {
        switch (sortByValue) {
            case "name":
                return "name";
            case "name-desc":
                return "name DESC";
            case "surname":
                return "surname";
            case "surname-desc":
                return "surname DESC";
            case "email":
                return "email";
            case "email-desc":
                return "email DESC";
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUser(id).orElseThrow(IllegalArgumentException::new);
    }

    private Integer transformQueryPageToInteger(String page) {
        return Integer.parseInt(page);
    }

    public String getPaginationForUsers(Optional<String> page, Optional<String> sortBy, String Uri){
        int currentPage = 1;
        if(page.isPresent()){
            currentPage = Integer.parseInt(page.get());
        }
        int amountUsers = userDAO.getAmountUsersInSystem();

        return new Pagination(currentPage, amountUsers , UserDAOImpl.getAmountUsersOnPage()
                , Uri, sortBy).get();
    }

}
