package net.eni.gestion.pedagogie.commun.composant.connexion;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;

public class Connexion {

  private static final ThreadLocal<TransactionManager> threadTransactionManager =
	         new ThreadLocal<TransactionManager>() {
	             @Override protected TransactionManager initialValue() {
						try {
							return new TransactionManager(DataSource.getInstance().getConnectionSource());
						} catch (SQLException e) {}
						return null;
	         }
	     };
  
  
  public static ConnectionSource getConnexion(){
	  return threadTransactionManager.get().getConnectionSource();
  }
  
  public static TransactionManager getTransactionManager(){
	  return threadTransactionManager.get();
  }

  
}
