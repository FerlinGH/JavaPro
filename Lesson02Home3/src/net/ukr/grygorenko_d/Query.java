package net.ukr.grygorenko_d;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "query")
public class Query {

    @XmlElement(name = "results")
    private Results results;

    public Query(Results results) {
        super();
        this.results = results;
    }

    public Query() {
        super();
    }

    public Results getResults() {
        return results;
    }
}
