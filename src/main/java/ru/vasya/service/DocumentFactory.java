package ru.vasya.service;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vasya.model.document.Document;
import ru.vasya.model.staff.Person;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.lang.reflect.Field;
import java.util.*;

@Stateless
public class DocumentFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentFactory.class);

    @EJB
    DerbyService ds;

    //private ArrayList<String> persons = new ArrayList<String>(Arrays.asList("First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh"));
    private Random rand = new Random();
    private int counter = 0;
    private List<Person> persons;

    private static DocumentFactory instance;

    public DocumentFactory(){
        ds = new DerbyService();
        persons = new ArrayList<Person>();
        CollectionUtils.addAll(persons, ds.getAll(Person.class).toArray());
    }

    public static DocumentFactory getInstance(){
        if (instance==null) {
            instance = new DocumentFactory();
        }
        return instance;
    }

    public Document getDocument(Class c){
        Document result = null;
        int id =++counter;
        try {
            result = (Document) c.newInstance();
            result.setId(id);
            result.setDocName("Docname#" + id);
            result.setText("Русский текст#" + id);
            result.setAuthor(getRandomPerson());
            for (Field field : c.getDeclaredFields()) {
                field.setAccessible(true);
                if(String.class.equals(field.getType())){
                    field.set(result, "Random content");
                } else if(Date.class.equals(field.getType())){
                    field.set(result, getRandomFutureDate(1));
                } else if (Integer.class.equals(field.getType())){
                    field.set(result, getRandomInt(1000));
                }else if (Person.class.equals(field.getType())){
                    field.set(result, getRandomPerson());
                }else if (boolean.class.equals(field.getType())){
                    field.set(result, rand.nextBoolean());
                }
                field.setAccessible(false);
            }
        } catch (Exception e) {
            LOGGER.warn("Could not initialize new Document", e);
        }
        return result;
    }
    private Person getRandomPerson(){
        return persons.get(rand.nextInt(persons.size()));
    }

    private int getRandomInt(int max){
        return rand.nextInt(max);
    }

    //Method adds some "hours" to current time and returns. May have problems with hours>596)
    private Date getRandomFutureDate(int hours){
        return new Date(System.currentTimeMillis() + rand.nextInt(hours*3600000));
    }
}
