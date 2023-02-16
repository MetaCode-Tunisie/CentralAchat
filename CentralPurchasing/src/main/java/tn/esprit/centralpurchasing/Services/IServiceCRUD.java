package tn.esprit.centralpurchasing.Services;

import java.util.List;

public interface IServiceCRUD  <object>{

    object Create(object T);
    List<object> Read();
    object Update(int ID,object T);
    String  Delete(int ID );
    object getOne(Integer ID);

}