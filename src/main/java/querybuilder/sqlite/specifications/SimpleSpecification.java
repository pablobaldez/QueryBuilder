package querybuilder.sqlite.specifications;

import querybuilder.sqlite.from.From;

/**
 * Especificação simples que não aplica joins.
 * @author Pablo
 * @since 11/08/2015
 */
public abstract class SimpleSpecification implements Specification{

    @Override
    public From join(From from) {
        return from;
    }
}
