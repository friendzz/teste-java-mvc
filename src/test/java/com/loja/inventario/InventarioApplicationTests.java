package com.loja.inventario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.*;


@SpringBootTest
class InventarioApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	void mockito(){
		//mock creation
		List mockedList = mock(List.class);

		String message = "mocked";
		//using mock object
		mockedList.add("one");
		mockedList.clear();

		//mocking
		when(mockedList.get(anyInt())).thenReturn(message);

		//verification
		verify(mockedList).add("one");
		verify(mockedList).clear();

		//Assertions
		Assertions.assertEquals(mockedList.get(0),message);
		System.out.println(mockedList.get(0));

	}

}
