package querybuilder.sqlite.specifications;

import querybuilder.sqlite.criteria.Criteria;
import querybuilder.sqlite.from.From;

import java.util.Collection;

/**
 * Especificação que define os critérios da consulta e aplicas joins necessários para realizá-la.
 * @author Pablo
 * @since 11/08/2015
 */
public interface Specification {

    /**
     * Aplica o critério da consulta.
     * @return critério da consulta.
     */
    Criteria criteria();

    /**
     * Aplica os joins necessários para a aplicação da consulta.
     * @param from Cláusula from que precede o join que será executado. Poderá ser a raiz do from.
     * @return joins aplicados. Caso não seja necessário aplicar joins, o próprio from especificado
     * por parâmetro deverá ser retornado.
     */
    From join(From from);

    /**
     * Informa todas as tabelas que estarão presentes no método {@link #join(From)}
     * @return todas as tabelas que estarão presentes no método {@link #join(From)}
     */
    Collection<String> tables();

}
