package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.function.LineFunction2D;
import org.junit.jupiter.api.Test;
import org.jfree.data.general.DatasetUtils;

public class TestableDesignTest {

    @Test
    public void testSampleIntoCollection_StateCheck() {
        XYSeriesCollection<String> collection = new XYSeriesCollection<>();
        LineFunction2D f = new LineFunction2D(0, 1);

        DatasetUtils.sampleIntoCollection(f, 0.0, 10.0, 5, "Series 1", collection);

        assertEquals(1, collection.getSeriesCount(), "Should have added 1 series");
        assertEquals(5, collection.getSeries(0).getItemCount(), "Should have 5 data points");
        assertEquals("Series 1", collection.getSeriesKey(0), "Series key should match");
    }
}