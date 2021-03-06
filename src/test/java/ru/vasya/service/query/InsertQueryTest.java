package ru.vasya.service.query;

import org.junit.Test;
import ru.vasya.service.query.parts.FieldToSelect;
import ru.vasya.service.query.parts.FieldsPart;
import ru.vasya.service.query.parts.LogicalOperation;
import ru.vasya.service.query.parts.Table;


public class InsertQueryTest {
    @Test
    public void testBuildQuery() throws Exception {
        InsertQuery insertQuery = InsertQuery.builder()
                .setTable(new Table("Users", "users"))
                .addValue(new FieldsPart(new FieldToSelect("name", "User"), "Vasya", LogicalOperation.EQUALS))
                .addValue(new FieldsPart(new FieldToSelect("surname", "Surname"), "Balalaikin", LogicalOperation.EQUALS))
                .addValue(new FieldsPart(new FieldToSelect("age", "Age"), 4 , LogicalOperation.EQUALS))
                .build();

        System.out.println(QueryToSqlConverter.convert(insertQuery));
    }
}