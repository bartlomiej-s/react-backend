package pw.react.backend.reactbackend;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.context.properties.ConfigurationPropertiesReportEndpoint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import pw.react.backend.reactbackend.entity.User;
import pw.react.backend.reactbackend.service.UserExistsService;
import pw.react.backend.reactbackend.service.UserService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import javax.servlet.ServletContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("it")
@ContextConfiguration(classes = { ConfigurationPropertiesReportEndpoint.ApplicationConfigurationProperties.class })
@WebAppConfiguration
public class ReactBackendApplicationTests {

	private UserService userService;
	private UserExistsService userExistsService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void findUserTest1()
	{
		User usr = new User();
		usr.setId("1");
		usr.setLogin("jacek123");
		usr.setFirstName("Jacek");
		usr.setLastName("Kmicic");
		usr.setDateOfBirth("2001-03-10");
		usr.setActive("false");
		Object expected = usr;
		Object actual = userService.findUser("jacek123");
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void findUserTest2()
	{
		ResponseEntity expected = new ResponseEntity("User with such login is not present in the database.", HttpStatus.CONFLICT);
		Object actual = userService.findUser("xyz");
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void insertUserTest1()
	{
		User usr = new User();
		usr.setId("1");
		usr.setLogin("jacek123");
		usr.setFirstName("Jacek");
		usr.setLastName("Kmicic");
		usr.setDateOfBirth("2001-03-10");
		usr.setActive("false");
		ResponseEntity expected = new ResponseEntity("User with such login is already present in the database.", HttpStatus.CONFLICT);
		ResponseEntity actual = userService.insertUser(usr);
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void insertUserTest2()
	{
		User usr = new User();
		usr.setLogin("xyz");
		ResponseEntity expected = new ResponseEntity(HttpStatus.OK);
		ResponseEntity actual = userService.insertUser(usr);
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void updatetUserTest1()
	{
		User usr = new User();
		usr.setId("1");
		usr.setLogin("jacek123");
		usr.setFirstName("Jacek");
		usr.setLastName("Niewiadomski");
		usr.setDateOfBirth("2001-03-10");
		usr.setActive("true");
		ResponseEntity expected = new ResponseEntity(HttpStatus.OK);
		ResponseEntity actual = userService.updateUser(usr);
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void updateUserTest2()
	{
		User usr = new User();
		usr.setLogin("xyz");
		ResponseEntity expected = new ResponseEntity("User with such a login is not present in the database.", HttpStatus.CONFLICT);
		ResponseEntity actual = userService.updateUser(usr);
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void deleteUserTest1()
	{
		ResponseEntity expected = new ResponseEntity(HttpStatus.OK);
		ResponseEntity actual = userService.deleteUser("jacek123");
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void deleteUserTest2()
	{
		ResponseEntity expected = new ResponseEntity("User with such a login is not present in the database.", HttpStatus.CONFLICT);
		ResponseEntity actual = userService.deleteUser("xyz");
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void existsTest1()
	{
		boolean expected = true;
		User usr = new User();
		usr.setId("1");
		usr.setLogin("jacek123");
		usr.setFirstName("Jacek");
		usr.setLastName("Kmicic");
		usr.setDateOfBirth("2001-03-10");
		usr.setActive("false");
		boolean actual = userExistsService.exists(usr);
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void existsTest2()
	{
		boolean expected = false;
		User usr = new User();
		usr.setLogin("xyz");
		boolean actual = userExistsService.exists(usr);
		Assert.assertEquals(actual, expected);
	}

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	@Test
	public void configurationTest() {
		ServletContext servletContext = wac.getServletContext();

		Assert.assertNotNull(servletContext);
		Assert.assertTrue(servletContext instanceof MockServletContext);
		Assert.assertNotNull(wac.getBean("ApplicationController"));
	}

	@Test
	public void getTest1() throws Exception{
		User usr = new User();
		usr.setId("1");
		usr.setLogin("jacek123");
		usr.setFirstName("Jacek");
		usr.setLastName("Kmicic");
		usr.setDateOfBirth("2001-03-10");
		usr.setActive("false");

		MvcResult mvcResult = this.mockMvc.perform(get("/user")
				.param("login", "jacek123")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andReturn();

		Assert.assertEquals(usr, mvcResult.getResponse());
	}

	@Test
	public void getTest2() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/user")
				.param("login", "jacek12")).andDo(print()).andExpect(status().isConflict())
				.andExpect(content().contentType("text/plain;charset=UTF-8"))
				.andReturn();

		Assert.assertEquals("User with such login is not present in the database.", mvcResult.getResponse());
	}

	@Test
	public void postTest1() throws Exception{
		MvcResult mvcResult = this.mockMvc.perform(post("/user").param("id", "2")
				.param("login", "jacek123").param("firstName","Jacek")
				.param("lastName","Kmicic").param("dateOfBirth","2001-03-10")
				.param("active", "false"))
				.andDo(print()).andExpect(status().isConflict())
				.andExpect(content().contentType("text/plain;charset=UTF-8"))
				.andReturn();

		Assert.assertEquals("User with such login is already present in the database.", mvcResult.getResponse());
	}

	@Test
	public void postTest2() throws Exception{
		this.mockMvc.perform(post("/user").param("id", "2")
				.param("login", "xyz").param("firstName","Jacek")
				.param("lastName","Kmicic").param("dateOfBirth","2001-03-10")
				.param("active", "false"))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void putTest1() throws Exception{
		this.mockMvc.perform(put("/user").param("id", "1")
				.param("login", "jacek123").param("firstName","Jacek")
				.param("lastName","Niewiadomski").param("dateOfBirth","2001-03-10")
				.param("active", "true"))
				.andDo(print()).andExpect(status().isOk())
				.andReturn();
	}

	@Test
	public void putTest2() throws Exception{
		MvcResult mvcResult = this.mockMvc.perform(put("/user").param("id", "1")
				.param("login", "xyz").param("firstName","Jacek")
				.param("lastName","Kmicic").param("dateOfBirth","2001-03-10")
				.param("active", "false"))
				.andDo(print()).andExpect(status().isConflict())
				.andExpect(content().contentType("text/plain;charset=UTF-8"))
				.andReturn();

		Assert.assertEquals("User with such a login is not present in the database.", mvcResult.getResponse());
	}

	@Test
	public void deleteTest1() throws Exception{
		this.mockMvc.perform(delete("/user")
				.param("login", "jacek123"))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void deleteTest2() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(delete("/user")
				.param("login", "xyz")).andDo(print()).andExpect(status().isConflict())
				.andExpect(content().contentType("text/plain;charset=UTF-8"))
				.andReturn();

		Assert.assertEquals("User with such a login is not present in the database.", mvcResult.getResponse());
	}
}
