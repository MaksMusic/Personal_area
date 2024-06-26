package ru.maksmusic.repository.Admin;

import ru.maksmusic.dataBase.AccountDatabase;
import ru.maksmusic.model.AdminAccount;
import ru.maksmusic.model.UserAccount;

import java.util.List;

public class AdminRepositoryImpl implements AdminRepository {
    private final AccountDatabase accountDatabase;
    private final List<AdminAccount> adminAccounts;
    private final List<UserAccount> userAccounts;

    public AdminRepositoryImpl(AccountDatabase accountDatabase) {
        this.accountDatabase = accountDatabase;
        this.adminAccounts = accountDatabase.getAdminAccounts();
        this.userAccounts = accountDatabase.getUserAccounts();
    }

    //Регистрация в системе
    @Override
    public void addAdmin(AdminAccount adminAccount) {
        if (adminAccount == null) {
            return;
        }

        for (AdminAccount account : adminAccounts) {
            if (account.equals(adminAccount)) {
                return;
            }
        }

        adminAccounts.add(adminAccount);
    }

    //Вход в личный кабинет
    @Override
    public AdminAccount getAdminByLoginAndPassword(String login, String password) {
        for (AdminAccount account : adminAccounts) {
            if (account != null){
                if (account.getLogin().equals(login) && account.getPassword().equals(password)){
                    return account;
                }
            }
        }
        return null;
    }

    //Просмотр списка пользователей
    @Override
    public List<UserAccount> getUserList() {
        return userAccounts;
    }

    //Удаление пользователей
    @Override
    public void deleteUserFromList(String username) {
        userAccounts.removeIf(user -> user.getLogin().equals(username));
    }

    //Изменение пароля пользователей
    @Override
    public void changeUserPassword(String username, String newPassword) {
        List<UserAccount> userAccounts = accountDatabase.getUserAccounts();
        for (UserAccount user : userAccounts) {
            if (user.getLogin().equals(username)) {
                user.setPassword(newPassword);
                accountDatabase.updateUserAccount(user);
                break;
            }
        }
    }
}
