package querybuilder.sqlite.specifications;

/**
 * Responsável por criar especificações.
 * @author Pablo
 * @since 11/08/2015
 */
public interface SpecificationBuilder {

    /**
     * Cria uma especificação que executará um conjunto de especificações através da cláusula And
     * @return especificação And
     */
    AndSpecification buildAnd();

    /**
     * Cria uma especificação que executará um conjunto de especificações através da cláusula Or
     * @return especificação Or
     */
    OrSpecification buildOr();

}
