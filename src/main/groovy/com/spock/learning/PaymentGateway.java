package com.spock.learning;

/**
 * @author dhwaneel
 */
public interface PaymentGateway {

 boolean makePayment(int amount);

 void notify(String message);

}
