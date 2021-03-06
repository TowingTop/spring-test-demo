package top.towing.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import top.towing.demo.domain.Employee;
import top.towing.demo.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
public class EmployeeServiceUnitTest {
	
	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {
		@Bean
		public EmployeeService employeeService() {
			return new EmployeeService();
		}
	}
	
	@Autowired
	private EmployeeService employeeService;
	
	@MockBean
	private EmployeeRepository employeeRepository;
	
	@Before
	public void setUp() {
	    Employee alex = new Employee("alex");
	    Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
	}
	
	@Test
	public void whenValidName_thenEmployeeShouldBeFound() {
	    String name = "alex";
	    Employee found = employeeService.getEmployeeByName(name);
	    assertThat(found.getName()).isEqualTo(name);
	 }
}
