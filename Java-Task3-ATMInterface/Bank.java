import java.util.HashMap;
import java.util.Map;

public class Bank {

    private Map<String, Account> accounts;

    public Bank() {

        accounts = new HashMap<>();

        // Sample account 1
        accounts.put("1001",
                new Account("1001", "user1", "1234", 10000));

        // Sample account 2
        accounts.put("1002",
                new Account("1002", "user2", "5678", 5000));
    }

    public Account authenticate(String userId, String pin) {

        for (Account account : accounts.values()) {

            if (account.getUserId().equals(userId)
                    && account.getPin().equals(pin)) {

                return account;
            }
        }

        return null;
    }

    public Account findAccount(String accountId) {

        return accounts.get(accountId);
    }
}