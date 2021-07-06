package domain;

import java.util.List;

public class User {
    private int id;
    private String name;
    private String surname;
    private String password;
    private List<CreditCard> creditCards;
    private String email;
    private Role role;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public static class Builder{
        private User newUser;

        public Builder(){
            newUser = new User();
        }

        public Builder withId(int id){
            newUser.id = id;
            return this;
        }

        public Builder withName(String name){
            newUser.name = name;
            return this;
        }

        public Builder withSurname(String surname){
            newUser.surname = surname;
            return this;
        }

        public Builder withPassword(String password){
            newUser.password = password;
            return this;
        }

        public Builder withCreditCards(List<CreditCard> creditCards){
            newUser.creditCards = creditCards;
            return this;
        }

        public Builder withEmail(String email){
            newUser.email = email;
            return this;
        }

        public Builder withRole(Role role){
            newUser.role = role;
            return this;
        }

        public User build(){
            return newUser;
        }
    }
}
