
import bank.*;
import bank.Transfer;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class TransferTest {

     static Transfer incomingTransfer;
    static Transfer outgoingTransfer;
    static Transfer copyTransfer;


    @BeforeAll
    public static void setUp() { try {
        incomingTransfer = new IncomingTransfer("03.03.2000",80, "IncomingTransfer from Peter to Dieter; 80");
        outgoingTransfer = new OutgoingTransfer("30.07.2020",1890, "OutgoingTransfer to Hans", "Dieter", "Hans");
        copyTransfer = new OutgoingTransfer(outgoingTransfer);}

    catch (Exception e){
        e.printStackTrace();
    }
    }


    @Test
    public void threeAttributesConstructorTest() {
        assertEquals("03.03.2000", incomingTransfer.getDate());
        assertEquals("IncomingTransfer from Peter to Dieter; 80", incomingTransfer.getDescription());
        assertEquals(80, incomingTransfer.getAmount());

    }

    @Test
    public void allAttributesConstructorTest() {
        assertEquals("30.07.2020", outgoingTransfer.getDate());
        assertEquals("OutgoingTransfer to Hans", outgoingTransfer.getDescription());
        assertEquals(1890, outgoingTransfer.getAmount());
        assertEquals("Hans", outgoingTransfer.getRecipient());
        assertEquals("Dieter", outgoingTransfer.getSender());
    }

    @Test
    public void copyConstructorTester() {
        assertEquals(outgoingTransfer.getAmount(), copyTransfer.getAmount());
        assertEquals(outgoingTransfer.getDate(), copyTransfer.getDate());
        assertEquals(outgoingTransfer.getRecipient(), copyTransfer.getRecipient());
        assertEquals(outgoingTransfer.getAmount(), copyTransfer.getAmount());
        assertEquals(outgoingTransfer.getSender(), copyTransfer.getSender());
        assertEquals(outgoingTransfer.getDescription(), copyTransfer.getDescription());
    }

    @Test
    public void calculateIncomingTransferTest() {
        assertInstanceOf(IncomingTransfer.class, incomingTransfer);
        assertEquals(incomingTransfer.getAmount(), incomingTransfer.calculate());
    }

    @Test
    public void calculateOutgoingTransferTest() {
        assertInstanceOf(OutgoingTransfer.class, outgoingTransfer);
        assertEquals(-outgoingTransfer.getAmount(), outgoingTransfer.calculate());
    }

    @Test
    public void equalsTrueTest() {
        assertEquals(outgoingTransfer, copyTransfer);
    }

    @Test
    public void equalsFalseTest() {
        assertNotEquals(incomingTransfer, outgoingTransfer);
    }

    @Test
    public void toStringTester() {
        assertEquals("Es handelt sich um ein OutgoingTransfer. Beschreibung: OutgoingTransfer to Hans. Transaktion in Höhe von -1890.0, getätigt am 30.07.2020\n"+
        "Sender: Dieter\n" +
        "Empfänger: Hans", outgoingTransfer.toString());
    }

}

