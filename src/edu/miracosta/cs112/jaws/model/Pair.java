package edu.miracosta.cs112.jaws.model;

/**
 * Generic pair class
 * Note: so much refactoring, so little time...
 * this is implemented because Product is abstract and so cannot be instantiated directly
 * so it is mostly to help meet the class structure requirements of the project
 * it's therefore probably unnecessary ultimately
 * @param <L>
 * @param <R>
 */

public class Pair<L,R> {
    private L l;
    private R r;
    public Pair(L l, R r){
        this.l = l;
        this.r = r;
    }
    public L getL(){ return l; }
    public R getR(){ return r; }
    public void setL(L l){ this.l = l; }
    public void setR(R r){ this.r = r; }
}
