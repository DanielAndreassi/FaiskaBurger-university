
package com.faiskaburgers.faiskaburger.database.dal;

import java.util.List;

public interface IDAL <T>{

    boolean gravar(T entidade);

    boolean alterar(T entidade);

    boolean apagar(T entidade);

    T get(int id);

    List<T> get(String filtro);
}
