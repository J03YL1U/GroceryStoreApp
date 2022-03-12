package mcgill.ecse321.grocerystore.service;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import mcgill.ecse321.grocerystore.dao.AccountRepository;
import mcgill.ecse321.grocerystore.dao.CartRepository;
import mcgill.ecse321.grocerystore.dao.PersonRepository;
import mcgill.ecse321.grocerystore.dao.ReceiptRepository;
import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.model.Receipt;
import mcgill.ecse321.grocerystore.model.Receipt.ReceiptStatus;
import mcgill.ecse321.grocerystore.model.Receipt.ReceiptType;

@ExtendWith(MockitoExtension.class)
 public class TestReceiptService {

 	@Mock
 	private ReceiptRepository receiptDao;


 	@InjectMocks
 	private ReceiptService service;
 	
 	@Mock 
	private AccountRepository accountDao;
	
	@Mock
	private PersonRepository personDao;
	
	@InjectMocks
	private AccountService accountService;
	
	@InjectMocks
	private PersonService personService;
 	
 	private static int ID = 0;
 	
 	private static final String USERNAME = "Bob";
	private static final String NEWUSERNAME = "Bob L'Eponge";
	private static final String PASSWORD = "101";
	private static final String NEWPASSWORD = "111";
	private static final boolean INTOWN = true;
	private static final boolean NEWINTOWN = false;
	private static final int TOTALPOINTS = 0;
	private static final int NEWTOTALPOINTS = 10;
	
	private static final String EMAIL = "abc@gmail.com";
	private static final String PHONENUMBER = "1112223333";
	private static final String ADDRESS = "845 Sherbrooke St W, Montreal, Quebec H3A 0G4";
	private static final String FIRSTNAME = "Bob";
	private static final String LASTNAME = "Smith";
	private static final Date date = java.sql.Date.valueOf(LocalDate.of(2022, Month.DECEMBER, 31));;

 	@BeforeEach
 	public void setMockOutput() {
 		lenient().when(receiptDao.findReceiptByReceiptNum(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
 			if(invocation.getArgument(0).equals(ID)) {
 				Receipt curr = new Receipt();
 				curr.setCart(null);
 				curr.setReceiptStatus(ReceiptStatus.Processed);
 				curr.setReceiptType(ReceiptType.Pickup);
 				return curr;
 			} else {
 				return null;
 			}
 		});
 		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
 			return invocation.getArgument(0);
 		};
 		lenient().when(receiptDao.save(any(Receipt.class))).thenAnswer(returnParameterAsAnswer);
 	}

 	@Test
 	public void testCreateReceipt() {
 		
 		Account account = new Account();
 		account.setUsername(USERNAME);
        account.setPassword(PASSWORD);
        account.setInTown(INTOWN);
        account.setTotalPoints(TOTALPOINTS);
        account.setPerson(personService.createPerson(EMAIL, FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS));

        Cart cart = new Cart();
        cart.setDate(date);
        
        Receipt curr = service.createReceipt(cart, ReceiptStatus.Processed, ReceiptType.Pickup);
        assertEquals(curr.getCart(), cart);
        assertEquals(curr.getReceiptStatus(), ReceiptStatus.Processed);
        assertEquals(curr.getReceiptType(), ReceiptType.Pickup);
        
 		
 	}


 } 