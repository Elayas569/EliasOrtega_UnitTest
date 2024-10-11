package com.mayab.quality.doubles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DependencyTest {

	private SubDependency subdependency;
	private Dependency dependency;
	@BeforeEach
	void setup() {
		subdependency = mock(SubDependency.class);
		dependency = new Dependency(subdependency);
	}
	@Test
	void test() {
		String name = dependency.getSubDependencyClassName();
		assertThat(name, is("Subdependency"));
	}

} 	
