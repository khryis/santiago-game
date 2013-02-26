package model;

public class PositionSegment extends Position {
    
    private int x2;
    private int y2;
    
    public PositionSegment() {
    
        super();
        this.x2 = 0;
        this.y2 = 0;
    }
    
    public PositionSegment(int x, int y, int x2, int y2) {
    
        super(x, y);
        this.x2 = x2;
        this.y2 = y2;
    }
    
    public PositionSegment(int x, int y, int x2, int y2, boolean occupe) {
    
        super(x, y, occupe);
        this.x2 = x2;
        this.y2 = y2;
    }
    
    public PositionSegment(int x, int y, boolean occupe) {
    
        super(x, y, occupe);
    }
    
    public int getX2() {
    
        return x2;
    }
    
    public void setX2(int x2) {
    
        this.x2 = x2;
    }
    
    public int getY2() {
    
        return y2;
    }
    
    public void setY2(int y2) {
    
        this.y2 = y2;
    }
    
    public static void determineAdjacentAPosition(Position position, PositionSegment segment1, PositionSegment segment2) {
    
        int x = position.getX(), y = position.getY(), x2 = position.getX(), y2 = position.getY();
        
        if ( isOdd(x) ) {
            if ( isOdd(y) ) {
                segment1 = new PositionSegment(x, y, x2 + 2, y2);
                segment2 = new PositionSegment(x, y, x2, y2 + 2);
            } else {
                segment1 = new PositionSegment(x, y - 1, x2, y2 + 1);
                segment2 = new PositionSegment(x, y + 1, x2 + 2, y2 + 1);
            }
        } else {
            if ( isOdd(y) ) {
                segment1 = new PositionSegment(x - 1, y, x2 + 1, y2);
                segment2 = new PositionSegment(x + 1, y, x2 + 1, y2 + 2);
            } else {
                segment1 = new PositionSegment(x + 1, y - 1, x2 + 1, y2 + 1);
                segment2 = new PositionSegment(x - 1, y + 1, x2 + 1, y2 + 1);
            }
        }
    }
    
    private static boolean isOdd(int x) {
    
        return (x % 2 == 0 ? true : false);
    }
    
    @Override
    public boolean equals(Object obj) {
    
        // TEST méthode equals à tester pour les segments Chris
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        PositionSegment other = (PositionSegment) obj;
        
        if ( (this.x == other.x && this.y == other.y && this.x2 == other.x2 && this.y2 == other.y2)
             || (this.x == other.x2 && this.y == other.y2 && this.x2 == other.x && this.y2 == other.y) )
            return true;
        
        return false;
    }
}
