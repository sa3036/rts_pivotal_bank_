package io.pivotal.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.isA;

import java.util.Map;
import java.util.Optional;

import io.pivotal.user.configuration.ServiceTestConfiguration;
import io.pivotal.user.domain.User;
import io.pivotal.user.exception.AuthenticationException;
import io.pivotal.user.exception.NoRecordsFoundException;
import io.pivotal.user.repository.UserRepository;
import io.pivotal.user.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
/**
 * Tests for the AccountService.
 * 
 * @author David Ferreira Pinto
 *
 */
public class UserServiceTest {
	MockMvc mockMvc;

	@InjectMocks
	UserService service;
	
	@Mock
	UserRepository repo;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

	    this.mockMvc = MockMvcBuilders.standaloneSetup(service).build();
	}

	/**
	 * test retrieval of account by integer.
	 */
	@Test
	public void doFindUser() {
		when(repo.findById(ServiceTestConfiguration.PROFILE_ID)).thenReturn(Optional.of(ServiceTestConfiguration.user()));
		assertEquals(service.findUser(ServiceTestConfiguration.PROFILE_ID).toString(),ServiceTestConfiguration.user().toString());
	}
	/**
	 * test retrieval of account by string - userid.
	 */
	@Test
	public void doFindAccountUserId() {
		when(repo.findByUserid(ServiceTestConfiguration.USER_ID)).thenReturn(ServiceTestConfiguration.user());
		assertEquals(service.findUser(ServiceTestConfiguration.USER_ID).toString(),ServiceTestConfiguration.user().toString());
	}
	/**
	 * test retrieval of account by string - userid, with no account found.
	 */
	@Test(expected=NoRecordsFoundException.class)
	public void doFindAccountUserIdNotFound() {
		when(repo.findByUserid(ServiceTestConfiguration.BAD_USER_ID)).thenReturn(null);
		service.findUser(ServiceTestConfiguration.BAD_USER_ID);
	}
	/**
	 * test retrieval of account not found.
	 */
	@Test(expected=NoRecordsFoundException.class)
	public void doFindNullAccount() {
		when(repo.findById(999)).thenReturn(Optional.empty());
		service.findUser(999);
	}
	
	/**
	 * test retrieval of account by authtoken.
	 */
	@Test
	public void doFindAccountByAuthToken() {
		when(repo.findByAuthtoken(ServiceTestConfiguration.AUTH_TOKEN)).thenReturn(ServiceTestConfiguration.user());
		
		assertEquals(service.findAccountprofileByAuthtoken(ServiceTestConfiguration.AUTH_TOKEN).toString(),ServiceTestConfiguration.user().toString());
	}
	/**
	 * test retrieval of account with no valid authtoken.
	 */
	@Test(expected=AuthenticationException.class)
	public void doFindNullAccountByAuthToken() {
		when(repo.findByAuthtoken("faef8649-280d-4ba4-bdf6-574e758a04a8")).thenReturn(null);
		
		service.findAccountprofileByAuthtoken("faef8649-280d-4ba4-bdf6-574e758a04a8");
	}
	/**
	 * test retrieval of account by authtoken with null.
	 */
	@Test(expected=AuthenticationException.class)
	public void doFindAccountByAuthTokenNull() {
		
		service.findAccountprofileByAuthtoken(null);
	}
	/**
	 * test saving of account.
	 */
	@Test
	public void saveAccount() {
		User acc = ServiceTestConfiguration.user();
		when(repo.save(acc)).thenReturn(acc);
		assertEquals(service.saveUser(acc),acc.getId());
	}
	
	/**
	 * test saving of account with nulls.
	 */
	@Test
	public void saveAccountWithNullCounts() {
		User acc = ServiceTestConfiguration.user();
		User accNull = ServiceTestConfiguration.user();
		accNull.setLogincount(null);
		accNull.setLogoutcount(null);
		acc.setLogincount(0);
		acc.setLogoutcount(0);
		
		when(repo.save(accNull)).thenReturn(acc);
		assertEquals(service.saveUser(accNull),acc.getId());
	}
	
	/**
	 * test login
	 */
	@Test
	public void testLogin() {
		User acc = ServiceTestConfiguration.user();
		when(repo.findByUseridAndPasswd(ServiceTestConfiguration.USER_ID, ServiceTestConfiguration.PASSWORD)).thenReturn(acc);
		when(repo.save(isA(User.class))).thenReturn(acc);
		
		Map<String,Object> result = service.login(ServiceTestConfiguration.USER_ID, ServiceTestConfiguration.PASSWORD);
		assertEquals(result.get("accountid"),ServiceTestConfiguration.PROFILE_ID);
		assertNotNull(result.get("authToken"));
	}
	
	/**
	 * test login
	 */
	@Test(expected=AuthenticationException.class)
	public void testLoginNull() {
		User acc = ServiceTestConfiguration.user();
		when(repo.findByUseridAndPasswd(ServiceTestConfiguration.USER_ID, ServiceTestConfiguration.PASSWORD)).thenReturn(null);
		
		service.login(ServiceTestConfiguration.USER_ID, ServiceTestConfiguration.PASSWORD);
	}
	/**
	 * test logout with no account found.
	 */
	@Test
	public void testLogoutNull() {
		when(repo.findByUserid(ServiceTestConfiguration.USER_ID)).thenReturn(null);
		
		User result = service.logout(ServiceTestConfiguration.USER_ID);
		
		assertNull(result);
	}
	/**
	 * test logout.
	 */
	@Test
	public void testLogout() {
		User acc = ServiceTestConfiguration.user();
		
		when(repo.findByUserid(ServiceTestConfiguration.USER_ID)).thenReturn(acc);
		
		User result = service.logout(ServiceTestConfiguration.USER_ID);
		
		Integer i = Math.addExact(1, ServiceTestConfiguration.LOGOUT_COUNT );
		
		assertEquals(result.getLogoutcount(), i);
		
		assertNull(result.getAuthtoken());
	}
	
	/**
	 * Test Account domain object hashcode.
	 */
	@Test
	public void testAccountObject() {
		User acc1 = ServiceTestConfiguration.user();
		User acc2 = ServiceTestConfiguration.user();
		
		assertEquals(acc1.hashCode(),acc2.hashCode());
	}
}
