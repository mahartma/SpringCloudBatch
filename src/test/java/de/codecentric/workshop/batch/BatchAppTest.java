package de.codecentric.workshop.batch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BatchAppTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @MockBean
    private ItemReader itemReader;

    @Test
    public void shouldRunJobWithMockedReader() throws Exception {
        Mockito.when(itemReader.read()).thenReturn("Hello", "World", null);
        jobLauncherTestUtils.launchJob();
    }
}
