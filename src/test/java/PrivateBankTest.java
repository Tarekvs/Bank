
import bank.*;
import bank.exceptions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test methods for PrivateBank")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PrivateBankTest {

    static PrivateBank privateBank;
    static PrivateBank copyPrivateBank;

    @DisplayName("Set up a PrivateBank")
    @BeforeAll
    public static void setUp() throws AccountAlreadyExistsException, IOException, TransactionAlreadyExistException, AccountDoesNotExistException, TransactionAttributeException {

        final File folder = new File(System.getProperty("user.dir")+"/src/main/java/bank/Konten/");
        if (folder.exists()) {
            final File[] listOfFiles = folder.listFiles();
            assert listOfFiles != null;
            for (File file : listOfFiles)
                file.delete();
            //Files.deleteIfExists(Path.of(System.getProperty("user.dir")+"/src/main/java/bank/Konten/"));
        }
        //folder.delete();

        privateBank = new PrivateBank("JUnit 5",  0.0, 0.12,System.getProperty("user.dir")+"/src/main/java/bank/Konten/");

        privateBank.createAccount("Hans");
        privateBank.createAccount("Tim");
        privateBank.addTransaction("Hans", new Payment("19.01.2011",-789.0, "Payment",  0.9, 0.25));
        privateBank.addTransaction("Tim", new IncomingTransfer("03.03.2000",80, "IncomingTransfer from Adam to Tim; 80",  "Adam", "Tim"));
        copyPrivateBank = new PrivateBank(privateBank);

    }

    @DisplayName("Testing constructor")
    @Test
    @Order(0)
    public void constructorTest() {
        assertAll("PrivateBank",
                () -> assertEquals("JUnit 5", privateBank.getName()),
                () -> assertEquals(System.getProperty("user.dir")+"/src/main/java/bank/Konten/", privateBank.getDirectoryName()),
                () -> assertEquals(0, privateBank.getIncominginterest()),
                () -> assertEquals(0.12, privateBank.getOutgoinginterest()));
    }

    @DisplayName("Testing copy constructor")
    @Test
    @Order(1)
    public void copyConstructorTest() {
        assertAll("CopyPrivateBank",
                () -> assertEquals(privateBank.getName(), copyPrivateBank.getName()),
                () -> assertEquals(privateBank.getDirectoryName(), copyPrivateBank.getDirectoryName()),
                () -> assertEquals(privateBank.getIncominginterest(), copyPrivateBank.getIncominginterest()),
                () -> assertEquals(privateBank.getOutgoinginterest(), copyPrivateBank.getOutgoinginterest()));
        assertTrue(privateBank.equals(copyPrivateBank));
    }

    @DisplayName("Create a duplicate account")
    @ParameterizedTest
    @Order(2)
    @ValueSource(strings = {"Hans", "Tim"})
    public void createDuplicateAccountTest(String account) {
        Exception e = assertThrows(AccountAlreadyExistsException.class,
                () -> privateBank.createAccount(account));
        System.out.println(e.getMessage());
    }

    @DisplayName("Create a valid account")
    @ParameterizedTest
    @Order(3)
    @ValueSource(strings = {"Dinesh", "Bob", "Narsha"})
    public void createValidAccountTest(String account) {
        assertDoesNotThrow(
                () -> privateBank.createAccount(account)
        );
    }

    @DisplayName("Create a valid account with a transactions list")
    @ParameterizedTest
    @Order(4)
    @ValueSource(strings = {"Klaus", "Harsh", "Rastla"})
    public void createValidAccountWithTransactionsListTest(String account) {
        assertDoesNotThrow(
                () -> privateBank.createAccount(account, List.of(
                        new Payment("23.09.1897",-2500.0, "Payment numero dos",  0.8, 0.5),
                        new OutgoingTransfer("30.07.2020",1890, "OutgoingTransfer to Hans",  account, "Hans")
                ))
        );
    }

    @DisplayName("Create a duplicate account with a transactions list")
    @ParameterizedTest
    @Order(5)
    @ValueSource(strings = {"Hans", "Klaus", "Tim", "Bob", "Dinesh", "Narsha", "Harsh", "Rastla"})
    public void createInvalidAccountWithTransactionsListTest(String account) {
        Exception e = assertThrows(AccountAlreadyExistsException.class,
                () -> privateBank.createAccount(account, List.of(
                        new Payment("23.09.1897",-2500.0, "Payment numero dos",  0.8, 0.5)
                ))
        );
        System.out.println(e.getMessage());
    }

    @DisplayName("Add a valid transaction to a valid account")
    @ParameterizedTest
    @Order(6)
    @ValueSource(strings = {"Hans", "Bob", "Narsha", "Dinesh"})
    public void addValidTransactionValidAccountTest(String account) {
        assertDoesNotThrow(
                () -> privateBank.addTransaction(account, new IncomingTransfer("30.07.2020",1890, "Incoming Transfer from Tom",  "Tom", account))
        );
    }

    @DisplayName("Add a duplicate transaction to a valid account")
    @ParameterizedTest
    @Order(7)
    @ValueSource(strings = {"Klaus", "Harsh", "Rastla"})
    public void addDuplicateTransactionTest(String account) {
        Exception e = assertThrows(TransactionAlreadyExistException.class,
                () -> privateBank.addTransaction(account, new Payment("23.09.1897",-2500.0, "Payment numero dos",  0.8, 0.5))
        );
        System.out.println(e.getMessage());
    }

    @DisplayName("Add a valid transaction to an invalid account")
    @ParameterizedTest
    @Order(8)
    @ValueSource(strings = {"Gina", "Bona", "Yang"})
    public void addTransactionInvalidAccountTest(String account) {
        Exception e = assertThrows(AccountDoesNotExistException.class,
                () -> privateBank.addTransaction(account, new Payment("19.01.2011",-789.0, "Payment",  0.9, 0.25))
        );
        System.out.println(e.getMessage());
    }

   /* @DisplayName("Remove a valid transaction")
    @ParameterizedTest
    @Order(9)
    @ValueSource(strings = {"Harsh", "Rastla" , "Klaus"})
    public void removeValidTransactionTest(String account) {
        System.out.println(privateBank);
        assertDoesNotThrow(
                () -> privateBank.removeTransaction(account, new Payment("23.09.1897",-2500.0, "Payment numero dos",0.8,  0.5))
        );
    }*/

    @DisplayName("Remove an invalid transaction")
    @ParameterizedTest
    @Order(10)
    @ValueSource(strings = {"Harsh", "Rastla", "Klaus"})
    public void removeInvalidTransactionTest(String account) {
        Exception e = assertThrows(TransactionDoesNotExistException.class,
                () -> privateBank.removeTransaction(account, new Payment("19.01.2011",-789.0, "Payment",  0.9, 0.25))
        );
        System.out.println(e.getMessage());
    }

    @DisplayName("Contains a transaction is true")
    @ParameterizedTest
    @Order(11)
    @ValueSource(strings = {"Harsh", "Rastla", "Klaus"})
    public void containsTransactionTrueTest(String account) {
        try {
            assertTrue(privateBank.containsTransaction(account, new OutgoingTransfer("30.07.2020", 1890, "OutgoingTransfer to Hans", account, "Hans")));
            System.out.println("containsTransactionTrueTest in <" + account + "> is correct.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DisplayName("Contains a transaction is false")
    @ParameterizedTest
    @Order(12)
    @ValueSource(strings = {"Hans", "Bob", "Narsha", "Dinesh", "Tim"})
    public void containsTransactionFalseTest(String account) {
        try {
            assertFalse(privateBank.containsTransaction(account, new OutgoingTransfer("30.07.2020", 1890, "OutgoingTransfer to Hans", account, "Hans")));
            System.out.println("containsTransactionFalseTest in <" + account + "> is correct.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @DisplayName("Get account balance")
    @ParameterizedTest
    @Order(14)
    @CsvSource({"Klaus, -5640.0", "Tim, 80", "Hans, 1007.0"})
    public void getAccountBalanceTest(String account, double balance) {
        System.out.println("Expected <" + balance + "> in account <" + account + ">");
        assertEquals(balance, privateBank.getAccountBalance(account));
    }

    @DisplayName("Get transactions list")
    @Test  @Order(15)
    public void getTransactionTest() {
        try {
        List<Transaction> transactionList = List.of(
                new Payment("19.01.2011",-789.0, "Payment",  0.0, 0.12),
                new IncomingTransfer("30.07.2020",1890, "Incoming Transfer from Tom",  "Tom", "Hans"));
        assertEquals(transactionList, privateBank.getTransactions("Hans"));
        System.out.println("getTransactionTest in <Hans> is correct.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*@DisplayName("Get transactions list by type")
    @Test @Order(16)
    public void getTransactionsByTypeTest() {
        try {
            List<Transaction> transactionList = List.of(
                    new OutgoingTransfer("30.07.2020",1890,  "OutgoingTransfer to Hans", "Klaus", "Hans"));
            assertEquals(transactionList, privateBank.getTransactionsByType("Klaus", false));
            System.out.println("getTransactionByTypeTest in <Klaus> is correct.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    @Test @Order(17)
    @DisplayName("Get sorted transactions list")
    public void getTransactionsSortedTest() { try {
        assertEquals(List.of(
                        new IncomingTransfer("03.03.2000",80,  "IncomingTransfer from Adam to Tim; 80", "Adam", "Tim"))
                , privateBank.getTransactionsSorted("Tim", true));

        } catch (Exception e){

        }
    }
}
