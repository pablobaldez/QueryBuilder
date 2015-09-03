package querybuilder.sqlite.specifications;

import querybuilder.sqlite.criteria.Criteria;

import java.util.Collection;

/**
 * Especificação que aplica o operador OR
 * @author Pablo
 * @since 11/08/2015
 */
public class OrSpecification extends OperatorSpecification {


    public OrSpecification(Collection<Specification> specifications, Joiner joiner) {
        super(specifications, joiner);
    }

    public OrSpecification(Joiner joiner) {
        super(joiner);
    }

    @Override
    public Criteria applyOperation(Criteria left, Criteria right) {
        return left.or(right);
    }
}
