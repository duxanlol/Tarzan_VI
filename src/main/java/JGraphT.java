import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.nio.*;
import org.jgrapht.nio.dot.*;

import java.io.*;
import java.net.*;
import java.util.*;

public final class JGraphT
{
    private JGraphT()
    {
    } // ensure non-instantiability.

    public static void graph(LinkedHashSet<Snapshot> his, ArrayList<Cycle> cycles){
        Graph<Snapshot, LabeledEdge> snapshotGraph = createSnapshotGraphWithCycles(his, cycles);
        System.out.println("-- renderHrefGraph output");
        renderSnapshotGraph(snapshotGraph);
        System.out.println();
    }

    private static Graph<Snapshot, LabeledEdge> createSnapshotGraphWithCycles(LinkedHashSet<Snapshot> his, ArrayList<Cycle> cycles){
        int mockId = -300;
        Graph<Snapshot, LabeledEdge> g = new DefaultDirectedGraph<>(LabeledEdge.class);
        for(Snapshot s: his){
            g.addVertex(s);
        }
        for(Snapshot c: his){
                int parent = c.getParentId();
                for(Snapshot p: his){
                    if (p.getNodeId() == parent){
                        g.addEdge(p,c, new LabeledEdge(c.lastMove));
                    }
                }

            for(Cycle cycle : cycles){
                if(cycle.childId == c.nodeId){
                    Snapshot mock = new Snapshot();
                    mock.setMock(true);
                    mock.setNodeId(mockId++);
                    mock.setMockText(cycle.parentId +"*");
                    g.addVertex(mock);
                    g.addEdge(c,mock, new LabeledEdge(cycle.lastMove));
                }
            }
        }
/*
                for(Cycle cycle : cycles){
                    if(cycle.childId == c.nodeId){
                        for(Snapshot p: his){
                            if (p.getNodeId() == cycle.parentId){
                                g.addEdge(c,p, new LabeledEdge(cycle.lastMove));
                            }
                        }
                    }
                }
*/
        return g;
    }
    private static Graph<Snapshot, LabeledEdge> createSnapshotGraph(LinkedHashSet<Snapshot> his, ArrayList<Cycle> cycles){
        Graph<Snapshot, LabeledEdge> g = new DefaultDirectedGraph<>(LabeledEdge.class);
        for(Snapshot s: his){
            g.addVertex(s);
        }
        for(Snapshot c: his){
            int parent = c.getParentId();
            for(Snapshot p: his){
                if (p.getNodeId() == parent){
                    g.addEdge(p,c, new LabeledEdge(c.lastMove));
                }
            }

        }

        return g;
    }

    private static void renderSnapshotGraph(Graph<Snapshot, LabeledEdge> snapshotGraph)
            throws ExportException
    {

        DOTExporter<Snapshot, LabeledEdge> exporter =
                new DOTExporter<>(v -> String.valueOf(v.getNodeId()));
        exporter.setVertexAttributeProvider((v) -> {
            Map<String, Attribute> map = new LinkedHashMap<>();
            map.put("label", DefaultAttribute.createAttribute(v.toString()));
            return map;
        });

        exporter.setEdgeAttributeProvider((e) -> {
            Map<String, Attribute> map = new LinkedHashMap<>();
            map.put("label", DefaultAttribute.createAttribute(e.getLabel()));
            return map;
        });

        Writer writer = new StringWriter();
        exporter.exportGraph(snapshotGraph, writer);
        System.out.println(writer.toString());
    }


}
