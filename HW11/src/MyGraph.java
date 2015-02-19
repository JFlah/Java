/**
 * Created by Alyssa Biggins and Jack Flaherty on 12/7/14.
 * Implementation of Graph to use for Bacon game
 */

import java.util.*;
public class MyGraph {

    int nVertex;
    ArrayList<Vertex> vertexList;
    HashMap<String, Vertex> vertexMap;

    public MyGraph(){
        vertexMap = new HashMap<String, Vertex>();
        vertexList = new ArrayList<Vertex>();
    }

    class Vertex {
        String name;
        LinkedList<Edge> adjacencyList;
        double baconNum;
        boolean visited = false;
        public void visit() {
            visited = true;
        }
        Vertex back;

        public String toString(){
            return name;
        }
    }

    class Edge {
        Vertex destination;
        double weight;
    }

    public boolean hasVertex(String name){
        return vertexMap.containsKey(name);
    }


    public void addEdge(String from, String to, double wt) {
        // Are both vertices in hashmap?
        Vertex v1;
        Vertex v2;
        if (!vertexMap.containsKey(from)) {
            Vertex vert = new Vertex();
            vert.name = from;
            vert.adjacencyList = new LinkedList<Edge>();
            vertexList.add(vert);
            vertexMap.put(from,vert);
            v1 = vert;
        } else {
            v1 = vertexMap.get(from);
        }
        if (!vertexMap.containsKey(to)) {
            Vertex vert = new Vertex();
            vert.name = to;
            vert.adjacencyList = new LinkedList<Edge>();
            vertexList.add(vert);
            vertexMap.put(to,vert);
            v2 = vert;
        } else {
            v2 = vertexMap.get(to);
        }

        Edge newEdge = new Edge();
        newEdge.destination = v2;
        newEdge.weight = wt;
        v1.adjacencyList.add(newEdge);
    }

    public Vertex getVertex(String name){
        return vertexMap.containsKey(name) ? vertexMap.get(name) : vertexMap.get(name + " (I)");
    }

    public double getWeight(String from, String to) {
        // Are both vertices in hashmap?
        if (!vertexMap.containsKey(from)) {
            //TODO throw exception?
        }
        if (!vertexMap.containsKey(to)) {
            //TODO throw exception? perhaps?
        }

        Vertex v1 = vertexMap.get(from);
        Vertex v2 = vertexMap.get(to);

        Edge connect = getEdge(v1, v2);
        //catnch no edge
        if(connect == null){
            //TODO throw exception

        }
        return connect.weight;
    }

    public double pathLength(List<String> path) {
        return path.size()/2;
    }


    public Edge getEdge(Vertex v1, Vertex v2) {
        LinkedList<Edge> edges = v1.adjacencyList;
        for (Edge edge : edges) {
            if (edge.destination == v2) {
                return edge;
            }
        }
        return null;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(Vertex vert : vertexList){
            sb.append(vert.name);
            sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

}