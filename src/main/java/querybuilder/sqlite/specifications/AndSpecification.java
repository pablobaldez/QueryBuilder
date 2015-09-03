package querybuilder.sqlite.specifications;

import querybuilder.sqlite.criteria.Criteria;

import java.util.Collection;

/**
 * Especificação que aplica o operador AND
 * @author Pablo
 * @since 11/08/2015
 */
public class AndSpecification extends OperatorSpecification{


    public AndSpecification(Collection<Specification> specifications, Joiner joiner) {
        super(specifications, joiner);
    }

    public AndSpecification(Joiner joiner) {
        super(joiner);
    }

    @Override
    public Criteria applyOperation(Criteria left, Criteria right) {
        return left.and(right);
    }

}
