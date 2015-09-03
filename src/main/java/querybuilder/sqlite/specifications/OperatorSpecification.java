package querybuilder.sqlite.specifications;

import querybuilder.sqlite.criteria.Criteria;
import querybuilder.sqlite.from.From;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Especificação que aplica um conjunto de critérios utilizando operadores lógicos.
 * @author Pablo
 * @since 11/08/2015
 */
public abstract class OperatorSpecification extends SimpleSpecification{

    private final Joiner joiner;
    private LinkedList<Specification> specifications;

    public OperatorSpecification(Collection<Specification> specifications, Joiner joiner) {
        this.joiner = joiner;
        this.specifications = new LinkedList<>(specifications);
    }

    public OperatorSpecification(Joiner joiner) {
        this.joiner = joiner;
        this.specifications = new LinkedList<>();
    }

    /**
     * Adiciona uma série de especificações
     * @param specifications novas especificações que serão adicionadas
     */
    public void addAll(Collection<Specification> specifications){
        this.specifications.addAll(specifications);
    }

    /**
     * Adiciona uma nova especificação ao conjunto
     * @param specification Especificação que será adicionada ao conjunto
     */
    public void add(Specification specification){
        specifications.add(specification);
    }

    @Override
    public Criteria criteria() {
        if(specifications.isEmpty())
            throw new IndexOutOfBoundsException("You must define one or more specifications to " +
                    "create a criteria");

        Criteria op = specifications.removeFirst().criteria();
        for(Specification specification: specifications){
            op = applyOperation(op, specification.criteria());
        }

        return op;
    }

    @Override
    public Collection<String> tables() {
        Set<String> tables = new LinkedHashSet<>();

        for(Specification specification: specifications){
            tables.addAll(specification.tables());
        }
        return tables;
    }

    @Override
    public From join(From from) {
        return joiner.doJoin(from, tables());
    }

    /**
     * Aplica o operador nos dois critérios
     * @param left critério esquerdo
     * @param right critério direito
     * @return um critério que é composto por outros dois através de um operador.
     */
    public abstract Criteria applyOperation(Criteria left, Criteria right);
}
