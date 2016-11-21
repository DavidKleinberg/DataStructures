//David Kleinberg
//dkleinb1@jhu.edu

import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

/**
    Six Degrees of Angelina Jolie.

    Some of the stuff I used in here is a little ahead of us. For
    example we have not formally covered maps yet. But I am sure
    you can nevertheless find your way around. :-)

    Do NOT USE MAPS in your code for BFS below! Trust us, you can
    make do just with the existing Graph interface and with Java's
    Queue interface (since BFS needs a queue internally).
*/
public final class Angelina {
    // Graph holding movies and actors as vertices, relationships
    // as edges. All are simply strings.
    private static Graph<String, String> graph =
        new SparseGraph<String, String>();

    // Vertices for the actor we're trying to connect to Angelina
    // Jolie and for Angelina herself.
    private static Vertex<String> actor;
    private static Vertex<String> jolie;

    // Shut up checkstyle.
    private Angelina() {}

    private static void readInput(String filename, String who)
        throws FileNotFoundException, IOException {
        // keep track of all vertices created so far by name
        Map<String, Vertex<String>> vertices = new HashMap<>();

        // how we read the input
        BufferedReader reader = new BufferedReader(new FileReader(
            new File(filename)));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] data = line.split("/");

            // find or create vertex for the movie
            Vertex<String> m = vertices.get(data[0]);
            if (m == null) {
                m = graph.insert(data[0]);
                vertices.put(data[0], m);
            }

            for (int i = 1; i < data.length; i++) {
                // find or create vertex for the actor
                Vertex<String> a = vertices.get(data[i]);
                if (a == null) {
                    a = graph.insert(data[i]);
                    vertices.put(data[i], a);
                }

                // double-check for special actors
                if (a.get().equals("Jolie, Angelina")) {
                    jolie = a;
                }
                if (a.get().equals(who)) {
                    actor = a;
                }

                // create two edges, from and to the movie
                graph.insert(m, a, "features");
                graph.insert(a, m, "acts in");
            }
        }
    }

    // Perform a breadth-first search (BFS) starting from Angelina Jolie
    // and stopping when (a) the graph is exhausted or (b) we found the
    // actor we're looking for. Then print the path from the actor back
    // to Angelina and exit the program. Since we're using BFS we can be
    // sure that the resulting path is among the shortest ones.

    private static void solveJolie() {
        //Make sure that any previous session is erased
        graph.clearLabels();

        LinkedList<Vertex> queue = new LinkedList<Vertex>();
        queue.add(jolie);
        while(queue.peekFirst() != null) {
            Vertex current = queue.pollFirst();
            graph.label(current, 'X');
            if (current.equals(actor)) {
                break;
            }
            for (Edge e : (Iterable<Edge>)graph.outgoing(current)) {
                if (graph.label(e) == null && graph.label(graph.to(e)) == null) {
                    graph.label(e, 'X');
                    Vertex child = graph.to(e);
                    queue.add(child);
                }
            }
        }
        retrace();
    }

    private static void retrace() {
        Vertex current = actor;
        int distance = 0;
        Vertex movie = null;
        while (current != null && !current.equals(jolie)) {
            System.out.println(current.get());
            movie = getSharedMovie(current);
            System.out.println(movie.get());
            current = getActor(movie);
            distance++;
        }
        System.out.println(jolie.get());
        System.out.println("Distance: " + distance);
}

    private static Vertex getSharedMovie(Vertex actor) {
        Vertex movie = null;
        for (Edge e : (Iterable<Edge>)graph.incoming(actor)) {
            if (graph.label(e) != null) {
                    movie = graph.from(e); 
                    break;
            }
        }
        return movie;
    }

    private static Vertex getActor(Vertex movie) {
        Vertex actor = null;
        for (Edge e : (Iterable<Edge>)graph.incoming(movie)) {
            if (graph.label(e) != null) {
                actor = graph.from(e); 
                break;
            }
        }
        return actor;
    }

    /**
        Main method.
        @param args Command line arguments.
        @throws FileNotFoundException If database file cannot be opened.
        @throws IOException If database file cannot be read properly.
    */
    public static void main(String[] args)
        throws FileNotFoundException, IOException {
        // read the input, initialize globals
        readInput(args[0], args[1]);

        // check that we could find both actors, quit if not
        if (actor == null) {
            System.out.printf("Error: Can't find %s in database.\n", args[1]);
            System.exit(1);
        }
        if (jolie == null) {
            System.out.printf("Error: Can't find Angelina in database.\n");
            System.exit(1);
        }

        // play "six degrees of Anglina Jolie" using breadth-first search
        solveJolie();
    }
}
