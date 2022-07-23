import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {

    List<Station> route;
    List<Station> actualShortestRoute;
    StationIndex stationIndex;
    List<Station> connectionStation1to2;
    List<Station> connectionStation1to3;

    protected void setUp() throws Exception
    {
        route = new ArrayList<>();
        actualShortestRoute = new ArrayList<>();
        stationIndex = new StationIndex();

        Line line1 = new Line(1, "Первая");
        Line line2 = new Line(2, "Вторая");
        Line line3 = new Line(3, "Третья");

        Station station1 = new Station("А", line1);
        Station station2 = new Station("Б", line1);
        Station station3 = new Station("В", line1);
        Station station4 = new Station("Г", line1);

        Station station5 = new Station("Д", line2);
        Station station6 = new Station("Ж", line2);
        Station station7 = new Station("З", line2);

        Station station8 = new Station("К", line3);
        Station station9 = new Station("Л", line3);
        Station station10 = new Station("М", line3);


        line1.addStation(station1);
        line1.addStation(station2);
        line1.addStation(station3);
        line1.addStation(station4);

        line2.addStation(station5);
        line2.addStation(station6);
        line2.addStation(station7);

        line3.addStation(station8);
        line3.addStation(station9);
        line3.addStation(station10);

        route.add(station1);
        route.add(station2);
        route.add(station3);
        route.add(station4);
        route.add(station5);
        route.add(station6);
        route.add(station7);
        route.add(station8);
        route.add(station9);
        route.add(station10);

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);
        stationIndex.addStation(station1);
        stationIndex.addStation(station2);
        stationIndex.addStation(station3);
        stationIndex.addStation(station4);
        stationIndex.addStation(station5);
        stationIndex.addStation(station6);
        stationIndex.addStation(station7);
        stationIndex.addStation(station8);
        stationIndex.addStation(station9);
        stationIndex.addStation(station10);
        connectionStation1to2 = new ArrayList<>();
        connectionStation1to3 = new ArrayList<>();
        connectionStation1to2.add(station1);
        connectionStation1to2.add(station6);
        connectionStation1to3.add(station3);
        connectionStation1to3.add(station9);

        stationIndex.addConnection(connectionStation1to2);
        stationIndex.addConnection(connectionStation1to3);

    }
    public void testCalculateDuration(){

        double actual = RouteCalculator.calculateDuration(route);
        double expected = 24.5;
        assertEquals(expected,actual);
    }


    public void testGetRouteOnTheLine(){
        RouteCalculator aa = new RouteCalculator(stationIndex);
        List<Station> actual = aa.getShortestRoute(stationIndex.getStation("А"), stationIndex.getStation("Б"));
        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("А"));
        expected.add(stationIndex.getStation("Б"));

        double actualTime = RouteCalculator.calculateDuration(actual);
        double expectedTime = 2.5;

        assertEquals(expectedTime,actualTime);
        assertEquals(expected,actual);

    }

    public void testGetRouteTwoTheLine(){
        RouteCalculator aa = new RouteCalculator(stationIndex);
        List<Station> actual = aa.getShortestRoute(stationIndex.getStation("А"), stationIndex.getStation("М"));
        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("А"));
        expected.add(stationIndex.getStation("Б"));
        expected.add(stationIndex.getStation("В"));
        expected.add(stationIndex.getStation("Л"));
        expected.add(stationIndex.getStation("М"));

        double actualTime = RouteCalculator.calculateDuration(actual);
        double expectedTime = 11.0;

        assertEquals(expectedTime,actualTime);
        assertEquals(expected,actual);

    }

    public void testGetRouteThreeTheLine(){
        RouteCalculator aa = new RouteCalculator(stationIndex);
        List<Station> actual = aa.getShortestRoute(stationIndex.getStation("Д"), stationIndex.getStation("М"));
        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("Д"));
        expected.add(stationIndex.getStation("Ж"));
        expected.add(stationIndex.getStation("А"));
        expected.add(stationIndex.getStation("Б"));
        expected.add(stationIndex.getStation("В"));
        expected.add(stationIndex.getStation("Л"));
        expected.add(stationIndex.getStation("М"));

        double actualTime = RouteCalculator.calculateDuration(actual);
        double expectedTime = 17.0;

//        assertEquals(expectedTime,actualTime);
        assertEquals(expected,actual);

    }

}
