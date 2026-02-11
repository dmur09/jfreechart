package org.jfree.data.xy;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FSM_XYSeriesTest {

    @Test
    void testEmptyDataset() {
        // FSM: Initial state is Empty
        XYSeries series = new XYSeries("Series1");
        XYSeriesCollection dataset = new XYSeriesCollection(series); // still empty
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Empty Dataset Chart", "X-Axis", "Y-Axis", dataset);
        assertNotNull(chart);
    }

    @Test
    void testAddToEmptyDataset() {
        // FSM: Empty -> Non-empty (adding one point)
        XYSeries series = new XYSeries("Series1");
        series.add(1, 10); // transition to Non-empty
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Single Point Chart", "X-Axis", "Y-Axis", dataset);
        assertNotNull(chart);
    }

    @Test
    void testAddMultiplePoints() {
        // FSM: Non-empty stays Non-empty (adding multiple points)
        XYSeries series = new XYSeries("Series1");
        for (int i = 1; i <= 5; i++) {
            series.add(i, i * 10);
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Small Dataset Chart", "X-Axis", "Y-Axis", dataset);
        assertNotNull(chart);
    }

    @Test
    void testClearDataset() {
        // FSM: Non-empty -> Empty (clearing the series)
        XYSeries series = new XYSeries("Series1");
        series.add(1, 10);
        series.clear(); // back to Empty
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Cleared Dataset Chart", "X-Axis", "Y-Axis", dataset);
        assertNotNull(chart);
    }
}
