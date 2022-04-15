import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import tasklist.*;

public class DukeTest {
    
    @Test
    public void TodoTest(){
        String expectedResult = "call publisher";
        String input = "call publisher";
        Todo testTodo = new Todo(input);
        assertEquals(testTodo.getDescription(), expectedResult);
	}

    @Test
    public void DeadlineTest(){
        String expectedResult = "read book";
        String input1 = "read book";
        String input2 = "2021-12-10";
        Deadline testDeadline= new Deadline(input1, input2);
        assertEquals(testDeadline.getDescription(), expectedResult);
    }

    @Test
    public void EventTest(){
        String expectedResult = "visit author conference";
        String input1 = "visit author conference";
        String input2 = "2022-08-10T20:45";
        Event testEvent = new Event(input1, input2);
        assertEquals(testEvent.getDescription(), expectedResult);
    }

}