package service;

import dao.UserDAOImpl;
import domain.User;

import java.util.List;
import java.util.Optional;

public class UsersServiceImpl implements UserService {

    private static final UsersServiceImpl usersService = new UsersServiceImpl();

    public static UsersServiceImpl getInstance(){
        return usersService;
    }

    private UsersServiceImpl() {
    }

    @Override
    public List<User> getAllUsers(Optional<String> page, Optional<String> sortByValue){
        Integer paginationPage = null;
        String sortBy = null;
        if(page.isPresent()){
          paginationPage =  transformQueryPageToInteger(page.get());
        }

        if(sortByValue.isPresent()){
            sortBy = getValidSortValue(sortByValue.get());
        }

        return new UserDAOImpl().getAllUser(paginationPage, sortBy);
    }

    private String getValidSortValue(String sortByValue){
        switch (sortByValue){
            case "name": return "name";
            case "name-desc": return "name DESC";
            case "surname": return "surname";
            case "surname-desc": return "surname DESC";
            case "email": return "email";
            case "email-desc": return "email DESC";
            default: throw  new IllegalArgumentException();
        }
    }

    @Override
    public User getUserById(int id){
        return new UserDAOImpl().getUser(id);
    }

    private Integer transformQueryPageToInteger(String page){
        int paginationPage = 0;
        try {
             paginationPage =  Integer.parseInt(page);
        }catch (NumberFormatException e){
            System.out.println("wrong format page");
        }
        return paginationPage;
    }

}