package bank;

/**
 * Interface Calculate Bill das eine abstrakte Methode deklariert
 */
public interface CalculateBill  {
      /**
       * Calculate Methode welche von Transfer und Payment definiert wird
       * @return gebe double Wert zurück
       */
      double calculate();
}
