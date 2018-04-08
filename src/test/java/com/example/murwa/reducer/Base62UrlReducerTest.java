package com.example.murwa.reducer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class Base62UrlReducerTest {

	@Test
	public void testReducing_1_ShouldBe_1() {
        UrlReducer reducer = new Base62UrlReducer();
        String token = reducer.reduce(1L);

        assertEquals("1", token);
	}

	@Test
	public void testReducing_10_ShouldBe_a() {
        UrlReducer reducer = new Base62UrlReducer();
        String token = reducer.reduce(10L);

        assertEquals("a", token);
	}

	@Test
	public void testReducing_35_ShouldBe_z() {
        UrlReducer reducer = new Base62UrlReducer();
        String token = reducer.reduce(35L);

        assertEquals("z", token);
	}

	@Test
	public void testReducing_36_ShouldBe_A() {
        UrlReducer reducer = new Base62UrlReducer();
        String token = reducer.reduce(36L);

        assertEquals("A", token);
	}

	@Test
	public void testReducing_61_ShouldBe_Z() {
        UrlReducer reducer = new Base62UrlReducer();
        String token = reducer.reduce(61L);

        assertEquals("Z", token);
	}

	@Test
	public void testReducing_62_ShouldBe_10() {
        UrlReducer reducer = new Base62UrlReducer();
        String token = reducer.reduce(62L);

        assertEquals("10", token);
	}

	@Test
	public void testReducing_3843_ShouldBe_ZZ() {
        UrlReducer reducer = new Base62UrlReducer();
        String token = reducer.reduce(3843L);

        assertEquals("ZZ", token);
	}

	@Test
	public void testReducing_3844_ShouldBe_100() {
		UrlReducer reducer = new Base62UrlReducer();
		String token = reducer.reduce(3844L);

        assertEquals("100", token);
	}

}
