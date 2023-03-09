import java.io.IOException;
import java.util.ArrayList;
import java.util.List; 
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

public class Proyecto {
	public static void main(String[] args) {

		//Usa los ids "302" y "3223" como prueba

		System.out.println("Introduzca los ID de los aereopuertos elegidos");
		System.out.println("-----------------------------------------------");
		System.out.println("Introduzca Aereopuerto de origen:");
		Scanner Input = new Scanner(System.in);
		String Origin = Input.nextLine();
		System.out.println("Introduzca Aereopuerto de destino:");
		Scanner Input2 = new Scanner(System.in);
		String Destination = Input2.nextLine();

		//verificacion de inputs validos
		if (Origin != null && Destination != null || Origin != "" && Destination != "") {

			Reader Reader = new Reader();

			try {
				ArrayList<Airport> Airports = Reader.readFileAirports();
				ArrayList<Route> Routes = Reader.readFileRoutes(Airports);

				System.setProperty("org.graphstream.ui", "swing");
				Graph graph = new MultiGraph("World");
				addNodesIntoGraph(Airports, graph);
				addEdgesIntoGraph(Routes, graph);
				graph.setAttribute("ui.stylesheet", style());
				graph.display();

				List<Node> shortestPath = dijkstra(Origin, Destination, graph);
				System.out.println("Por codigo de identificaci�n:");
				System.out.println("--------------------------------");
				System.out.println(shortestPath);

			} catch (IOException ex) {
				System.out.println(ex);
			}

			Input.close();
			Input2.close();
		} else {
			System.out.println("aereopuertos seleccionado invalidos");
		}
	}

	private static Object style() {
		return "node {" + "size: 2px;" + "fill-color: green;" + "z-index: 1 ;" + "text-color: black;"
				+ "text-alignment: at-right; text-style: bold; " + "text-size: 10px;" + "}"

				+ "edge {" + "size: 1px;" + "shape: line;" + "fill-color: black;" + "arrow-size: 2px, 2px;"
				+ "z-index:0;}";
	}

	//funcion de caminos cortos
	public static List<Node> dijkstra(String origin, String destination, Graph graph) {
		Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "lenght");
		dijkstra.init(graph);
		dijkstra.setSource(graph.getNode(origin));
		dijkstra.compute();

		List<Node> shortestPath = dijkstra.getPath(graph.getNode(destination)).getNodePath();

		Iterable<Node> pathNodes = dijkstra.getPathNodes(graph.getNode(destination));
		Iterable<Edge> pathEdges = dijkstra.getPathEdges(graph.getNode(destination));
		System.out.println("Estos son los aereopuestos en orden por donde deberias pasar");
		System.out.println("--------------------------------------------------------------");
		for (Node node : pathNodes) {
			node.setAttribute("ui.style",
					"fill-color: yellow; size: 6px; z-index: 2; text-background-mode: plain;text-background-color: white;");
			node.setAttribute("label", ((Airport) node.getAttribute("data")).getName());
			System.out.println(((Airport) node.getAttribute("data")).getName());
		}
		graph.getNode(origin).setAttribute("ui.style", "fill-color: red; size: 6px; z-index: 2;");
		graph.getNode(destination).setAttribute("ui.style", "fill-color: red; size: 6px; z-index: 2;");
		for (Edge edge : pathEdges) {
			edge.setAttribute("ui.style", "fill-color: red;size:2px;z-index:2;");
		}

		return shortestPath;
	}

	private static void addNodesIntoGraph(ArrayList<Airport> Airports, Graph graph) {
		for (Airport Airport : Airports) {
			if (Airport != null) {
				String id = Airport.getId();
				try {
					graph.addNode(id);
					Node node = graph.getNode(id);
					node.setAttribute("data", Airport);
					node.setAttribute("layout.frozen");
					node.setAttribute("ui.frozen");
					node.setAttribute("xy", Airport.getLongitud(), Airport.getLatitud());
					// node.setAttribute("label", Airport.getName());
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		}
	}

	private static void addEdgesIntoGraph(ArrayList<Route> Routes, Graph graph) {
		for (Route Route : Routes) {
			graph.addEdge(
					Route.getCode() + Route.getID() + Route.getSourceAirportIATA() + Route.getSourceAirportID()
							+ Route.getDestinationAirportIATA() + Route.getDestinationAirportID(),
					Route.getSourceAirport().getId(), Route.getDestinationAirport().getId())
					.setAttribute("lenght", Route.getWeight());

		}
	}
//	private static void searchRoute() {
//		System.out.println("Introduzca Aereopuerto de origen:");
//		Scanner Input = new Scanner(System.in);
//		String Origin = Input.nextLine();
//		System.out.println("Introduzca Aereopuerto de destino:");
//		Scanner Input2 = new Scanner(System.in);
//		String Destination = Input2.nextLine();
//		
//		
//		
//		Reader Reader = new Reader();
//		
//		try {
//			ArrayList<Airport> Airports = Reader.readFileAirports();
//			ArrayList<Route> Routes = Reader.readFileRoutes(Airports);
//			
//			String[] ids = nameToId(Origin, Destination, Airports);
//			
//			System.setProperty("org.graphstream.ui", "swing");
//			Graph graph = new MultiGraph("World");
//			addNodesIntoGraph(Airports, graph);
//			addEdgesIntoGraph(Routes, graph);
//			graph.setAttribute("ui.stylesheet", style());
//			graph.display();
//			
//			List<Node> result = dijkstra(ids[0], ids[1], graph);
//			
//			System.out.println(result);
//			
//		} catch (IOException ex) {
//			System.out.println(ex);
//		}
//		
//		Input.close();
//		Input2.close();
//		
//	}


//	@SuppressWarnings("null")
//	private static String[] nameToId(String origin, String destination, ArrayList<Airport> Airports) {
//		String[] ids = {" "," "};
//		int idsFound = 0;
//		while(idsFound != 2) {
//			for (Airport Airport : Airports) {
//				if (origin == Airport.getName()) {
//					String origin2 = Airport.getId();
//					ids[0] = origin2;
//					idsFound++;
//				}
//				if (destination == Airport.getName()) {
//					String origin3 = Airport.getId();
//					ids[1] = origin3;
//					idsFound++;
//				}
//			}
//			System.out.println("Algun aereopuerto que has introducido a sido erroneo");
//			break;
//		}
//		return ids;
//	}
}
