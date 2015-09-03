package querybuilder.sqlite.specifications;

import querybuilder.sqlite.criteria.Criteria;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Criador de especificações base da aplicação.
 * @author Pablo
 * @since 11/08/2015
 */
public abstract class BaseSpecBuilder implements SpecificationBuilder, Joiner{

    private Collection<Specification> specifications = new LinkedList<>();

    /**
     * Adiciona uma nova especificação para ser validada
     * @param criteria Critério executado pela especificação
     * @param tables Tabelas envolvidas no critério
     */
    public void add(final Criteria criteria, final String... tables){
        specifications.add( new SimpleSpecification() {
            @Override
            public Criteria criteria() {
                return criteria;
            }

            @Override
            public Collection<String> tables() {

                return tables != null ? Arrays.asList(tables) : Collections.EMPTY_LIST;
            }
        });
    }

    /**
     * Recupera as especificações criadas.
     * @return especificações criadas.
     */
    public Collection<Specification> get(){
        return specifications;
    }

    @Override
    public AndSpecification buildAnd() {
        AndSpecification spec = new AndSpecification(specifications, this);
        specifications.clear();
        return spec;
    }

    @Override
    public OrSpecification buildOr() {
        OrSpecification spec = new OrSpecification(specifications, this);
        specifications.clear();
        return spec;
    }
}
