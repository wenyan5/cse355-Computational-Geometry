public class Predicate {

    public static boolean TriTriIntersect(Vertex a, Vertex b, Vertex c, Vertex d, Vertex e, Vertex f)
    {
        if(IntersectProp(a,b,d,e) || IntersectProp(a,b,d,f) || IntersectProp(a,b,e,f) ||
                IntersectProp(a,c,d,e) || IntersectProp(a,c,d,f) || IntersectProp(a,c,e,f)||
                IntersectProp(a,c,d,e) || IntersectProp(a,c,d,f) || IntersectProp(a,c,e,f) ||
                Left(a,b,d) && Left(a,b,e) && Left(a,b,f) && Left(b,c,d) && Left(b,c,e) && Left(b,c,f) && Left(c,a,d) && Left(c,a,e) && Left(c,a,f) ||
                !Left(a,b,d) && !Left(a,b,e) && !Left(a,b,f) && !Left(b,c,d) && !Left(b,c,e) && !Left(b,c,f) && !Left(c,a,d) && !Left(c,a,e) && !Left(c,a,f) ||
                Left(d,e,a) && Left(d,e,b) && Left(d,e,c) && Left(e,f,a) && Left(e,f,b) && Left(e,f,c) && Left(f,d,a) && Left(f,d,b) && Left(f,d,c) ||
                !Left(d,e,a) && !Left(d,e,b) && !Left(d,e,c) && !Left(e,f,a) && !Left(e,f,b) && !Left(e,f,c) && !Left(f,d,a) && !Left(f,d,b) && !Left(f,d,c))
            return true;
        return false; //default return value
    }
    

    //Here is the predicates from the book, adjusted to java
    public static boolean Left(Vertex a, Vertex b, Vertex c)
    {
        return Area2( a, b, c ) > 0;
    }
    public static boolean LeftOn(Vertex a, Vertex b, Vertex c)
    {
        return Area2( a, b, c ) >= 0;
    }
    public static boolean Collinear(Vertex a, Vertex b, Vertex c)
    {
        return Area2( a, b, c ) == 0;
    }
    public static boolean IntersectProp(Vertex a, Vertex b, Vertex c, Vertex d)
    {
        if (Collinear(a,b,c) || Collinear(a,b,d) || Collinear (c, d, a) || Collinear(c,d,b))
            return false;
        return Xor(Left(a,b,c), Left(a,b,d)) && Xor(Left(c,d,a), Left(c,d,b));
    }
    public static boolean Between(Vertex a, Vertex b, Vertex c)
    {
        if ( ! Collinear ( a, b, c ) )
            return false;
        if ( a.x != b.x )
            return ((a.x <= c.x) && (c.x <= b.x )) || ((a.x >= c.x) && (c.x >= b.x));
        return ((a.y <= c.y) && (c.y <= b.y)) || ((a.y >= c.y) && (c.y >= b.y));
    }
    public static boolean Intersect(Vertex a, Vertex b, Vertex c, Vertex d)
    {
        if (IntersectProp( a, b, c, d ) )
            return true;
        else if ( Between ( a, b, c) || Between (a, b, d) || Between (c, d, a) || Between (c, d, b))
            return true;
        return false;
    }
    public static int Area2(Vertex a, Vertex b, Vertex c)
    {
        return ((b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y));
    }
    public static boolean Xor( boolean x, boolean y )
    {
        return x != y;
    }
}
