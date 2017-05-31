package orders;

import exceptions.OrderException;

/**
 * Created by reem on 31/05/17.
 */
public class OrderFactory {
    public static Order createOrder(String orderLine) throws OrderException{
        // TODO: 31/05/17 implement!!! like in filter factory

        return null;
    }

    public static Order createDefaultOrder(){
        return new AbsOrder();
    }
}
