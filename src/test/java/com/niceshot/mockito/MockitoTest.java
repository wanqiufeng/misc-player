package com.niceshot.mockito;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.*;

public class MockitoTest {
    @Test
    public void test() {
        List mockList = Mockito.mock(List.class);
        when(mockList.get(0)).thenThrow(new RuntimeException());
        doThrow(new RuntimeException()).when(mockList).clear();

        //when(mockList.get(0)).thenReturn("adaa");
        doReturn("abc").when(mockList).clear();
        
    }
}
