package us.stevenrussell.spgql.types;

import graphql.language.StringValue;
import graphql.schema.*;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class LocalDateScalar extends GraphQLScalarType {

    private static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    LocalDateScalar() {
        super("MDYDate", "String representing date in MM-dd-yyyy format", new Coercing<LocalDate, String>() {
            @Override
            public String serialize(Object o) throws CoercingSerializeException {
                if (o instanceof LocalDate) {
                    try {
                        String serialized = ((LocalDate) o).format(PATTERN);
                        return serialized;
                    } catch (DateTimeException e) {
                        throw new CoercingSerializeException("Unable to serialize " + o);
                    }

                }
                throw new CoercingSerializeException("Unable to serialize " + o);
            }

            @Override
            public LocalDate parseValue(Object o) throws CoercingParseValueException {
                if (o instanceof String) {
                    String localDateStr = o.toString();
                    try {
                        LocalDate date = LocalDate.parse(localDateStr, PATTERN);
                        return date;
                    } catch (DateTimeParseException e) {
                        throw new CoercingParseValueException("Could not parse " + o);
                    }
                }
                throw new CoercingParseValueException("Could not parse " + o);
            }

            @Override
            public LocalDate parseLiteral(Object o) throws CoercingParseLiteralException {
                if (o instanceof StringValue) {
                    String localDateStr = ((StringValue) o).getValue();
                    try {
                        LocalDate date = LocalDate.parse(localDateStr, PATTERN);
                        return date;
                    } catch (DateTimeParseException e) {
                        throw new CoercingParseLiteralException("Could not parse " + o);
                    }
                }
                throw new CoercingParseLiteralException("Could not parse " + o);
            }
        });
    }


}
