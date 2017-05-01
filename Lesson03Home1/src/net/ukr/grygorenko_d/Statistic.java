package net.ukr.grygorenko_d;

import java.util.HashMap;
import java.util.Map;

public class Statistic {
    private Map<String,Integer> votesMap = new HashMap<>();
    public static Statistic stat = new Statistic();

    private Statistic() {
    }

    public static Statistic getInstance(){
        return stat;
    }

    public synchronized void addVote(String vote){
        if(votesMap.containsKey(vote)){
            Integer count = votesMap.get(vote);
            votesMap.put(vote,count+1);
        }else{
            votesMap.put(vote,1);
        }
    }
    public synchronized Integer getVote(String vote){
        if(votesMap.containsKey(vote)){
            return votesMap.get(vote);
        }else{
            return 0;
        }
    }
}
