package org.example.game.rules;

import org.example.game.motion.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class BeatNode {
    
    private List<BeatNode> children;
    
    public void setChildren(List<BeatNode> children){
        children = children;
    }
    public List<BeatNode> getChildren(){
        return children;
    }
    
    public BeatNode Parent;
    
    public void setParent(BeatNode Parent){
        this.Parent = Parent;
    }
    public BeatNode getParent(){
        return Parent;
    }
    
    public Point Beated;
    public void setBeated(Point Beated){
        this.Beated = Beated;
    }
    public Point getBeated(){
        return Beated;
    }
    
    public Point Move;
    public void setMove(Point Move){
        this.Move = Move;
    }
    public Point getMove(){
        return Move;
    }
    
    public BeatNode() {
        children = new ArrayList<>();
    }
    
    public void addChild(BeatNode child) {
        children.add(child);
        child.Parent = this;
    }
    
    public List<List<Point>> SplitToBranches(){
        List<List<Point>> result = new ArrayList<List<Point>>();
        List<BeatNode> finalLeafs = new ArrayList<BeatNode>();
        FindFinalLeafs(this, finalLeafs);
        
        for(BeatNode leaf : finalLeafs){
            result.add(ReconstructBranch(leaf));
        }
        return result;
    }
    
    protected static void FindFinalLeafs(BeatNode node, List<BeatNode> finalleafs){
        for (var child : node.getChildren()) {
            if(child.children.size() == 0){
                finalleafs.add(child);
            }
            else
                FindFinalLeafs(child, finalleafs);
        }
    }

    protected List<Point> ReconstructBranch(BeatNode leaf){

        List<Point> result = new ArrayList<Point>();

        for (var iter = leaf; iter != null; iter = iter.Parent)
        {
            result.add(iter.Move);
        }
        result.reversed();
        return result;
    }
    public boolean BranchContaintsValue(Point beated)
    {
        for (var iter = this; iter != null && iter.Beated != null; iter = iter.Parent)
        {
            if (iter.Beated.equals(beated))
            {
                return true;
            }
        }
        return false;
    }

    public void Dispose()
    {
        for (var child : children)
            child.Dispose();
        children.clear();
        Beated = null;
    }
    public void FilterForKingKills()
    {
        boolean hasLongSeries = false;
        boolean hasShortSeries = false;

        for (var child : children)
        {
            if (child.children.size() == 0)
                hasShortSeries = true;

            if (child.children.size() > 0)
                hasLongSeries = true;
        }

        if (hasLongSeries && hasShortSeries)
        {
            children = children.stream().filter(n->n.children.size()>0).toList();

            for (var child : children)
                child.FilterForKingKills();
        }

    }
}
