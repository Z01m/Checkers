package org.example.game.rules;

import org.example.game.motion.Motion;
import org.example.game.motion.Point;
import org.example.game.rules.Extenstions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class motionValidator {
    
    public enum MotionValidEnum{
        VALID,
        INVALID,
        MUST_BEAT
    }
    List<List<Point>> kills;
    List<List<Point>> moves;
    
    public motionValidator(List<List<Point>> kills, List<List<Point>> moves) {
        this.kills = kills;
        this.moves = moves;
    }
    
    public MotionValidEnum validate(Motion motion) {
        boolean mustBeat = false;
        boolean canMove = false;
        
        if(kills.size() > 0 ) {
            if(kills.stream().anyMatch(k -> k.equals(motion.getMoves()))) {
                return MotionValidEnum.VALID;
            }
        }
        canMove = moves.stream().anyMatch(m -> m.equals(motion.getMoves()));
        if(canMove) {
            if (mustBeat){
                return MotionValidEnum.MUST_BEAT;
            }
            else 
                return MotionValidEnum.VALID;
        }
        return MotionValidEnum.INVALID;
    }
    
    public List<Point> FindValidPoints(Motion motion) {
        List<Point> validPoints = new ArrayList<Point>();
        var container = (kills.size()>0? kills : moves);
        
        if(motion.isEmpty())
            validPoints.addAll(SlisePointsAt(container,0));
        else {
            var killsorMove = container.stream().filter(k->Extenstions.StartsFrom(k,motion.getMoves())).toList();
        }
        return validPoints;
    }
    
    private static List<Point> SlisePointsAt(List<List<Point>> container, int index) {
        List<Point> res = new ArrayList<Point>();
        for(var list : container) {
            res.add(list.get(index));
        }
        return res;
    }
    
    public boolean noValidMoves() {
        return  kills.isEmpty() && moves.isEmpty();
    }
    
    public List<Motion> GetAllMotions(){
        var list = kills.size()>0? kills : moves;
        var res = list.stream().map(m -> new Motion(m)).collect(Collectors.toList());
        return res;
    }
    
    public int GetOnlyMotionCount(){
        return kills.size() > 0 ? 0 : moves.size();
    }
    
}
