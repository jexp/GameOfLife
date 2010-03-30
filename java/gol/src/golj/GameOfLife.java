

package golj;

import java.awt.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class GameOfLife {
    private final Set<Point> alive = new HashSet<Point>();

    public GameOfLife(Collection<Point> alive) {
        this.alive.addAll(alive);
    }

    private Set<Point> neighbours(Point p) {
        final Set<Point> result = new HashSet<Point>();
        for (int x = -1; x <= 1; x++)
            for (int y = -1; y <= 1; y++) result.add(new Point(p.x + x, p.y + y));
        return result;
    }

    private Set<Point> aliveNeighbours(Point p) {
        final Set<Point> result = new HashSet<Point>();
        for (Point neighbour : neighbours(p)) {
            if (alive.contains(neighbour)) result.add(neighbour);
        }
        return result;
    }

    private Set<Point> deadNeighbours(Point p) {
        final Set<Point> result = new HashSet<Point>();
        for (Point neighbour : neighbours(p)) {
            if (!alive.contains(neighbour)) result.add(neighbour);
        }
        return result;
    }


    public GameOfLife next() {
        final Set<Point> stayingAlive = new HashSet<Point>();
        for (Point cell : alive) {
            final int livingNeighbourCount = aliveNeighbours(cell).size();
            if (livingNeighbourCount == 2 || livingNeighbourCount == 3) {
                stayingAlive.add(cell);
            }
        }
        final Set<Point> wakingFromDead = new HashSet<Point>();
        for (Point cell : alive) {
            final int livingNeighbourCount = deadNeighbours(cell).size();
            if (livingNeighbourCount == 2 || livingNeighbourCount == 3) {
                stayingAlive.add(cell);
            }
        }
        final HashSet<Point> result = new HashSet<Point>(stayingAlive);
        result.addAll(wakingFromDead);
        return new GameOfLife(result);
    }
}
