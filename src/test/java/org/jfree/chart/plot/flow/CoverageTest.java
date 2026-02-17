package org.jfree.chart.plot.flow;

import org.jfree.data.flow.FlowDataset;
import org.jfree.data.flow.FlowKey;
import org.jfree.data.flow.NodeKey;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CoverageTest {

    // Minimal FlowDataset to let FlowPlot run
    private static class DummyDataset implements FlowDataset<String> {

        @Override
        public int getStageCount() {
            return 1;
        }

        @Override
        public List<String> getSources(int stage) {
            return Collections.singletonList("A");
        }

        @Override
        public List<String> getDestinations(int stage) {
            return Collections.singletonList("B");
        }

        @Override
        public Set<NodeKey<String>> getAllNodes() {
            return Collections.emptySet();
        }

        @Override
        public Object getNodeProperty(NodeKey<String> nodeKey, String propertyKey) {
            return null;
        }

        @Override
        public Number getFlow(int stage, String source, String destination) {
            return 1;
        }

        @Override
        public Set<FlowKey<String>> getAllFlows() {
            return Collections.emptySet();
        }

        @Override
        public Object getFlowProperty(FlowKey<String> flowKey, String propertyKey) {
            return null;
        }

        @Override
        public void addChangeListener(org.jfree.data.general.DatasetChangeListener listener) {}
        @Override
        public void removeChangeListener(org.jfree.data.general.DatasetChangeListener listener) {}
    }

    @Test
    public void testFlowPlotCoverage() throws CloneNotSupportedException {
        DummyDataset dataset = new DummyDataset();
        FlowPlot<String> plot = new FlowPlot<>(dataset);

        // Basic getters / setters
        plot.setNodeWidth(5.0);
        plot.setNodeMargin(0.02);
        plot.setFlowMargin(0.03);
        plot.setDefaultNodeColor(Color.RED);
        plot.setNodeLabelOffsetX(1.0);
        plot.setNodeLabelOffsetY(1.0);
        plot.setNodeLabelAlignment(org.jfree.chart.api.VerticalAlignment.TOP);

        assertEquals(5.0, plot.getNodeWidth());
        assertEquals(0.02, plot.getNodeMargin());
        assertEquals(0.03, plot.getFlowMargin());
        assertEquals(Color.RED, plot.getDefaultNodeColor());
        assertEquals(1.0, plot.getNodeLabelOffsetX());
        assertEquals(1.0, plot.getNodeLabelOffsetY());
        assertEquals(org.jfree.chart.api.VerticalAlignment.TOP, plot.getNodeLabelAlignment());

        // Test lookupNodeColor
        NodeKey<String> nodeKey = new NodeKey<>(0, "A");
        Color color = plot.lookupNodeColor(nodeKey);
        assertNotNull(color);

        // draw method
        Graphics2D g2 = (Graphics2D) new java.awt.image.BufferedImage(1,1,java.awt.image.BufferedImage.TYPE_INT_ARGB).getGraphics();
        Rectangle2D area = new Rectangle2D.Double(0,0,100,100);
        Point2D anchor = new Point2D.Double(0,0);
        plot.draw(g2, area, anchor, null, null);

        // equals / hashCode / clone
        FlowPlot<String> clone = (FlowPlot<String>) plot.clone();
        assertEquals(plot, clone);
        assertEquals(plot.hashCode(), clone.hashCode());
        assertNotSame(plot, clone);
    }
}
