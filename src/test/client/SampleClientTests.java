package client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class SampleClientTests {

    @Test
    public void testGetAverageOfList() {
        SampleClient client = new SampleClient();
        List<Long> list = new ArrayList<>();
        list.add(Long.valueOf(1L));
        list.add(Long.valueOf(2L));
        list.add(Long.valueOf(3L));
        list.add(Long.valueOf(4L));
        list.add(Long.valueOf(5L));
        double average = client.getAverageOfList(list);

        Assertions.assertEquals(3.0, average);

    }
}
