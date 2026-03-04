package org.jfree.data;

import static org.mockito.Mockito.*;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.function.LineFunction2D;
import org.junit.jupiter.api.Test;
import org.jfree.data.general.DatasetUtils;

public class TestableDesignTest {

    @Test
    public void testSampleIntoCollection_BehavioralCheck() {

        XYSeriesCollection<String> mockCollection = mock(XYSeriesCollection.class);
        LineFunction2D f = new LineFunction2D(0, 1); // y = x

        
        DatasetUtils.sampleIntoCollection(f, 0.0, 1.0, 5, "TestSeries", mockCollection);

        verify(mockCollection, times(1)).addSeries(any());
        
        System.out.println("Design Success: Verified interaction with injected mock.");
    }
}