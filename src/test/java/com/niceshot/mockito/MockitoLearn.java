package com.niceshot.mockito;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.verification.VerificationMode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class MockitoLearn {
    @Mock
    private Set mockSet;
    public MockitoLearn() {
        MockitoAnnotations.openMocks(MockitoLearn.class);
    }
    @Test
    public void test() {
        //mock creation
        List mockedList = mock(List.class);

        //using mock object
        mockedList.add("one");
        mockedList.clear();

        //verification
        verify(mockedList).add("one");
        verify(mockedList).clear();


        //stubbing using built-in anyInt() argument matcher
        when(mockedList.get(anyInt())).thenReturn("element");



        //following prints "element"
        System.out.println(mockedList.get(999));

        //you can also verify using an argument matcher
        verify(mockedList).get(anyInt());

        /*//argument matchers can also be written as Java 8 Lambdas
        verify(mockedList).add(argThat(someString -> someString.length() > 5));*/

        //when(mockedList.contains("fb")).thenReturn(true);
        mockedList.contains("fb");
        verify(mockedList).contains("fb");

        doThrow(new RuntimeException()).when(mockedList).clear();
        mockedList.clear();
    }

    @Test
    public void testVerifyInOrder() {

        // A. Single mock whose methods must be invoked in a particular order
        List singleMock = mock(List.class);

        //using a single mock
        singleMock.add("was added first");
        singleMock.add("was added second");

        //create an inOrder verifier for a single mock
        InOrder inOrder = inOrder(singleMock);

        //following will make sure that add is first called with "was added first", then with "was added second"
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        // B. Multiple mocks that must be used in a particular order
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        //using mocks
        secondMock.add("was called second");
        firstMock.add("was called first");


        //create inOrder object passing any mocks that need to be verified in order
        InOrder inOrder1 = inOrder(secondMock,firstMock );

        //following will make sure that firstMock was called before secondMock
        inOrder1.verify(secondMock).add("was called second");
        inOrder1.verify(firstMock).add("was called first");


        // Oh, and A + B can be mixed together at will
    }

    @Test
    public void redundantInvocation() {
        List mockedList = mock(List.class);
        //using mocks
        mockedList.add("one");
        mockedList.add("two");

        verify(mockedList).add("one");
        verify(mockedList).add("two");

        //following verification will fail
        verifyNoMoreInteractions(mockedList);
    }

    @Test
    public void testAnnotation(){
        List mockList = mock(List.class);
        when(mockList.get(1))
                .thenReturn("hello ")
                .thenReturn("world");
        System.out.println(mockList.get(1));
        System.out.println(mockList.get(1));

        when(mockList.get(1)).thenReturn("hello","world");
        System.out.println(mockList.get(1));
        System.out.println(mockList.get(1));
    }

    @Test
    public void doSerial() {
        ArrayList list = new ArrayList();
        List spy = spy(list);

        //Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
        //when(spy.get(0)).thenReturn("foo").thenThrow().thenCallRealMethod()
        //You have to use doReturn() for stubbing:
        doReturn("foo").when(spy).get(0);
    }

    @Test
    public void paramCapture() {
        List mock = mock(List.class);
        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
        mock.get(8);
        verify(mock).get(argument.capture());
        assertEquals(Integer.valueOf(8), argument.getValue());
    }

    @Test
    public void paramCapture1() {
        List mock = mock(List.class);
        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
        mock.get(8);
        verify(mock).get(argument.capture());
        assertEquals(Integer.valueOf(8), argument.getValue());
    }

    @Test
    public void partialMock() {
        ArrayList mock = mock(ArrayList.class);
        //Be sure the real implementation is 'safe'.
        //If real implementation throws exceptions or depends on specific state of the object then you're in trouble.
        mock.add(1);
        when(mock.size()).thenCallRealMethod();
        mock.add(1);
        System.out.println(mock.size());
    }

    @Test
    public void testStyle(){
        //given
        List mockList = mock(List.class);
        when(mockList.get(0)).thenReturn("hello world");

        //when
        String invokeResult = (String) mockList.get(0);

        //then
        verify(mockList).get(0); //get方法有被调用过
        assertEquals("hello world",invokeResult); //验证调用的结果是hello world


    }

    @Test
    public void testStyle2(){
        //given
        List mockList = mock(List.class);
        given(mockList.get(0)).willReturn("hello world");

        //when
        String invokeResult = (String) mockList.get(0);

        //then
        then(mockList).should().get(0);//get方法有被调用过
        assertEquals("hello world",invokeResult); //验证调用的结果是hello world
    }

    @Test
    public void testAnswer() {
        //given
        StringFormat stringFormat = mock(StringFormat.class);
        when(stringFormat.upperCase(anyString())).thenAnswer(new Answer<String>() {
            public String answer(InvocationOnMock invocation) {
                String argument = invocation.getArgument(0);
                String trim = argument.trim();
                return trim.toUpperCase();
            }
        });

        //when
        String result = stringFormat.upperCase(" hello world ");

        //then
        verify(stringFormat).upperCase(anyString());
        Assert.assertEquals("HELLO WORLD",result);
    }

    public class StringFormat {
        public String upperCase(String input) {
            String trim = input.trim();
            return trim.toUpperCase();
        }

        public String udfFunction(String input,Function<String,String> udfFunction){
            return udfFunction.apply(input);
        }
    }


}
