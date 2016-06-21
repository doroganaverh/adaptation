package ru.vasya.report;


import ru.vasya.document.Document;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ReportManager {

    public static String getReport(List<Document> docs){

        String authorCash=null;
        StringBuilder sb = new StringBuilder();
        Collections.sort(docs);
        Iterator<Document> i = docs.iterator();

        while(i.hasNext()){
            Document d =i.next();
            if (!d.getAuthor().equals(authorCash)){
                authorCash = d.getAuthor();
                sb.append("\n");
                sb.append(d.getAuthor());
                sb.append("\n---");
                sb.append(d.toStringForReport());
            } else
                sb.append("\n---" + d.toStringForReport());

        }
        return sb.toString();
    }
}
