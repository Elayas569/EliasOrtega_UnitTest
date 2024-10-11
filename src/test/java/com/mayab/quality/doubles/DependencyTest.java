package com.mayab.quality.doubles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

class DependencyTest {

	private SubDependency subdependency;
	private Dependency dependency;
	@BeforeEach
	void setup() {
		subdependency = mock(SubDependency.class);
		dependency = new Dependency(subdependency);
		when(subdependency.getClassName()).thenReturn("Subdependency");
	}
	@Test
	void test() {
		String name = dependency.getSubDependencyClassName();
		assertThat(name, is("Subdependency"));
	}
	
	@Test
	public void testAnswer() {
		Dependency dependency = Mockito.mock(Dependency.class);
		when(dependency.addTwo(anyInt())).thenAnswer(new Answer<Integer>(){
			public Integer answer(InvocationOnMock invocation) throws Throwable{
				int arg = (Integer)invocation.getArguments()[0];
				return arg + 20;
			}
		});
		assertEquals(30, dependency.addTwo(10));
	}
	
	

} 	
