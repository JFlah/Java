/**
 * Created by Alyssa Biggins and Jack Flaherty on 12/7/14.
 * vertices = actors
 * edges = yes if 2 actors appeared in the same movie
 * Bacon App which uses the implementation of the Graph class
 */

import java.net.URL;
import java.util.*;
import java.io.*;


public class Bacon {

    private String center = "Kevin Bacon";

    //TODO use Version one to find shortest path from notes 31 b/c all weights are same
    //VERSION ONE
//    bfs(Graph g, Vertex v){
//        Queue q = new Queue();
//        v.visit();
//        v.visited = true; q.enqueue(v);
//        while(s is not empty){
//            Vertex w = q.dequeue();
//            for each vertex u which is adjacent to w
//                if(!u.visited){
//                    u.visit();
//                    u.visited = true;
//                    q.enqueue(u);
//                }
//            }
//    }
    //When u is visited, its distance from v is the distance from v to w + 1. The predecessor of u is w.

    private MyGraph BaconGraph = new MyGraph();

    public Bacon(String filename){
        createGraph(filename);
    }
    private void createGraph(String url) {
        try {
            Scanner s = new Scanner(new URL(url).openStream());
            while (s.hasNextLine()) {
                String[] pieces = s.nextLine().split("\\|");
                //if new actor or movie then create new vertices
                String actor = pieces[0];
                String movie = pieces[1];
                //System.out.println("Current actor: " + actor + " " + "current movie" + movie);
                //TODO if actor/movie = new then add?
                BaconGraph.addEdge(actor, movie, .5d); //TODO figure out what 1d is
                BaconGraph.addEdge(movie, actor, .5d);
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public List<MyGraph.Vertex> find(String name) {
        if (!BaconGraph.vertexMap.containsKey(name)) {
            System.out.println("Person not in database");
            return new LinkedList<MyGraph.Vertex>();
        }

        Queue<MyGraph.Vertex> workList = new LinkedList<MyGraph.Vertex>();
        List<MyGraph.Vertex> modified = new LinkedList<MyGraph.Vertex>();
        MyGraph.Vertex ourCenter = BaconGraph.getVertex(center);
        workList.add(ourCenter);
        ourCenter.visit();

        while (!workList.isEmpty()) {
            MyGraph.Vertex current = workList.poll();
            if (current.name.equals(name)) {
                List<MyGraph.Vertex> path = getResult(current);
                clean(modified);
                return path;
            }
            for (MyGraph.Edge edge : current.adjacencyList) {
                if (!edge.destination.visited) {
                    MyGraph.Vertex destination = edge.destination;
                    destination.visit();
                    destination.back = current;
                    workList.add(destination);
                    modified.add(destination);
                }
            }
        }
        System.out.println("They don't know each other");
        return new LinkedList<MyGraph.Vertex>();
    }

    /**
     * Prints the result and prepares for the next search
     * @param end
     * @return
     */
    public List<MyGraph.Vertex> getResult(MyGraph.Vertex end) {
        List<MyGraph.Vertex> path = new LinkedList<MyGraph.Vertex>();
        while (end.back != null) {
            path.add(0, end);
            end = end.back;
        }
        path.add(0, end);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(i).toString());
            if (i+1 < path.size())
                sb.append(" --> ");
        }
        System.out.println(sb.toString());
        System.out.println("Bacon Number is: " + path.size() / 2);
        return path;
    }

    public void circles(){
        List<MyGraph.Vertex> lst = new LinkedList<MyGraph.Vertex>();
        MyGraph.Vertex centVert = BaconGraph.getVertex(center);
        centVert.visit();
        lst.add(centVert);
        int i = 0;
        while(!lst.isEmpty()) {
            System.out.println("Circle " + i + " of size " + lst.size() + "with " + lst.get(0).toString());
            lst = oneCircle(lst);
            i++;
//            List<MyGraph.Vertex> circ = oneCircle(lst);
        }
        cleanAll();
    }

    public double avgdistance(){
        List<MyGraph.Vertex> lst = new LinkedList<MyGraph.Vertex>();
        MyGraph.Vertex centVert = BaconGraph.getVertex(center);
        List<MyGraph.Vertex> reachable = new LinkedList<MyGraph.Vertex>();
        centVert.visit();
        lst.add(centVert);
        int i = 0;
        double totalDistance = 0d;
        while(!lst.isEmpty()) {
            int stepDist = i*lst.size();
            totalDistance+=stepDist;
            reachable.addAll(lst);
            lst = oneCircle(lst);
            i++;
        }
        List<MyGraph.Vertex> unreachable = new LinkedList<MyGraph.Vertex>();
        for(MyGraph.Vertex vert : BaconGraph.vertexList){
            if(!vert.visited){
                unreachable.add(vert);
            }
        }

        double avg = totalDistance/reachable.size();
        System.out.println(avg + "\t" + center + " " + "(" + reachable.size() + ", " + unreachable.size() + ")");

        //finally clean
        cleanAll();
        return avg;
    }

    public void topCenter(int n) {
        List<MyGraph.Vertex> lst = new LinkedList<MyGraph.Vertex>();
        MyGraph.Vertex centVert = BaconGraph.getVertex(center);
        List<MyGraph.Vertex> reachable = new LinkedList<MyGraph.Vertex>();
        centVert.visit();
        lst.add(centVert);
        while(!lst.isEmpty()) {
            reachable.addAll(lst);
        }

        PriorityQueue<Double> pq = new PriorityQueue<Double>();
        for (MyGraph.Vertex vert : reachable) {
            vert.baconNum = avgdistance(vert.name);
            pq.add(vert.baconNum);
        }
        for (int i = 1; i <= n; i++) {
            System.out.println(i + ". " + "\t" +pq.poll());
        }

    }

    public double avgdistance(String name){
        List<MyGraph.Vertex> lst = new LinkedList<MyGraph.Vertex>();
        MyGraph.Vertex centVert = BaconGraph.getVertex(name);
        List<MyGraph.Vertex> reachable = new LinkedList<MyGraph.Vertex>();
        centVert.visit();
        lst.add(centVert);
        int i = 0;
        double totalDistance = 0d;
        while(!lst.isEmpty()) {
            int stepDist = i*lst.size();
            totalDistance+=stepDist;
            reachable.addAll(lst);
            lst = oneCircle(lst);
            i++;
        }
        List<MyGraph.Vertex> unreachable = new LinkedList<MyGraph.Vertex>();
        for(MyGraph.Vertex vert : BaconGraph.vertexList){
            if(!vert.visited){
                unreachable.add(vert);
            }
        }

        //TODO: make it print like it does in hw
        double avg = (double) reachable.size()/(double)(reachable.size() + unreachable.size());
        System.out.println(reachable.size() + " " + unreachable.size() + " " + avg);


        //finally clean
        cleanAll();
        return totalDistance/(reachable.size());
    }

    public List<MyGraph.Vertex> oneCircle(List<MyGraph.Vertex> lst){
        lst.add(BaconGraph.getVertex(center));
        List<MyGraph.Vertex> movies = oneDegree(lst);
//        System.out.println("ONE DEG " + oneDegree(movies));
        return oneDegree(movies);
    }

    /**
     * Clean by labeling all vertecies visited value false and backpointers to null, for next search
     */
    private void clean(List<MyGraph.Vertex> modified){
        for (MyGraph.Vertex vert : modified) {
            vert.visited = false;
            vert.back = null;
        }
    }

    private void cleanAll(){
        for (MyGraph.Vertex vert : BaconGraph.vertexList) {
            vert.visited = false;
            vert.back = null;
        }
    }

    public void recenter(String name) {
        if (!BaconGraph.vertexMap.containsKey(name)) {
            System.out.println("Name not found in database");
        }
        else {
            this.center = name;
        }
    }

    private List<MyGraph.Vertex> oneDegree(List<MyGraph.Vertex> list) {
        List<MyGraph.Vertex> oneDegree = new LinkedList<MyGraph.Vertex>();
        for (MyGraph.Vertex vert : list) {
            for (MyGraph.Edge edge : vert.adjacencyList) {
                if (!edge.destination.visited) {
                    MyGraph.Vertex destination = edge.destination;
                    destination.visit();
                    oneDegree.add(destination);
                }
            }
        }
        return oneDegree;
    }


    public static void main(String[] args) {
        if (args.length == 2) {
            //TODO: Change center
        }

        String filename = "http://cs.bc.edu/~donaldja/102/imdb.small.txt";
        Bacon baconTest = new Bacon(filename);
       // System.out.println(baconTest.BaconGraph.toString());
//        System.out.println(baconTest.avgdistance());
//        baconTest.circles();
//        baconTest.find(args[0]);
        //System.out.println(baconTest.createGraph(filename));

        baconTest.topCenter(5);
    }
}




