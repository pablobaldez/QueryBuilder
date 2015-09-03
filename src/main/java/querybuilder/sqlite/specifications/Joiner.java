package querybuilder.sqlite.specifications;

import querybuilder.sqlite.from.From;

import java.util.Collection;

/**
 * Responsável por criar joins.
 * @author Pablo
 * @since 11/08/2015
 */
public interface Joiner {

    /**
     * executa o join
     * @param root From raiz
     * @param tables lista de tabelas envolvidas
     * @return Join de from com as tabelas envolvidas
     */
    From doJoin(From root, Collection<String> tables);

}
