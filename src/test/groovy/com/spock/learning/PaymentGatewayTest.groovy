package com.spock.learning

import com.spock.learning.PaymentGateway
import spock.lang.Specification;

/**
 * @author dhwaneel
 */
public class PaymentGatewayTest extends Specification {

  def "Testing with Mock" (){
    given:
    def paymentGateway= Mock(PaymentGateway)

    when:
    def result= paymentGateway.makePayment(12)

    then:
    result == false

  }

  def "Testing with stubs" (){
    given:
    def paymentGateway= Mock(PaymentGateway)

    paymentGateway.makePayment(20) >> true

    when:
    def result = paymentGateway.makePayment(20)

    then:
    result == true

  }

  def "Testing with stubs - any argument" (){
    given:
    def paymentGateway= Mock(PaymentGateway)

    paymentGateway.makePayment(_) >> true

    when:
    def result = paymentGateway.makePayment(20)

    then:
    result == true

  }

  def "Testing with stubs - any argument - multiple responses" (){
    given:
    def paymentGateway= Mock(PaymentGateway)

    paymentGateway.makePayment(_) >>> [true, false]

    when:
    def result = paymentGateway.makePayment(20)
    def result1 = paymentGateway.makePayment(30)

    then:
    result == true
    result1 == false

  }

  def "Testing void methods called with args" (){
    given:
    def paymentGateway= Mock(PaymentGateway)

    when:
    paymentGateway.notify('message')

    then:
    1 * paymentGateway.notify('message')

  }

  def "Testing void methods called with 'any' args" (){
    given:
    def paymentGateway= Mock(PaymentGateway)

    when:
    paymentGateway.notify('message')

    then:
    //Called with any parameter
    1 * paymentGateway.notify(_)
  }

  def "Testing void methods not called with a specific args" (){
    given:
    def paymentGateway= Mock(PaymentGateway)

    when:
    paymentGateway.notify('message')

    then:
    //Called with not a specific param
    1 * paymentGateway.notify(!"wow")


  }
}
